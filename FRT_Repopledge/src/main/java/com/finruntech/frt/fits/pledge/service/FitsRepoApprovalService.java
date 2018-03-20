package com.finruntech.frt.fits.pledge.service;

import com.alibaba.fastjson.JSON;
import com.finruntech.frt.fits.pledge.commons.enums.InstStatus;
import com.finruntech.frt.fits.pledge.commons.enums.OrderFStatus;
import com.finruntech.frt.fits.pledge.commons.enums.StatusType;
import com.finruntech.frt.fits.pledge.commons.util.Utils;
import com.finruntech.frt.fits.pledge.dispatcher.BpmDispatcher;
import com.finruntech.frt.fits.pledge.dispatcher.FitsServiceDispatcher;
import com.finruntech.frt.fits.pledge.dispatcher.RedisDispatcher;
import com.finruntech.frt.fits.pledge.model.FitsBpmAprvFormEntity;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgInstEntity;
import com.finruntech.frt.fits.pledge.model.FitsRepoPldgMgtEntity;
import com.finruntech.frt.fits.pledge.model.dto.FitsPledgeInstResultDto;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoApprovalDto;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgMgtDto;
import com.finruntech.frt.fits.pledge.model.dto.FundCashAndBondFlashDto;
import com.finruntech.frt.fits.pledge.model.dto.approval.BpmRedisDto;
import com.finruntech.frt.fits.pledge.model.dto.approval.ProcessParamDto;
import com.finruntech.frt.fits.pledge.model.dto.approval.ReturnJsonDto;
import com.finruntech.frt.fits.pledge.repository.FitsRepoApprovalMapper;
import com.finruntech.frt.fits.pledge.repository.FitsRepoBpmAprvFormMapper;
import com.finruntech.frt.fits.pledge.repository.FitsRepoPldgInstMapper;
import com.finruntech.frt.fits.pledge.repository.FitsRepoPldgMgtMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhangws on 2018/1/3.
 */
@Service
public class FitsRepoApprovalService<T> {
   @Autowired
   private FitsRepoApprovalMapper fitsRepoApprovalMapper;
   @Autowired
   private FitsRepoBpmAprvFormMapper fitsRepoBpmAprvFormMapper;
   @Autowired
   private FitsRepoPldgInstMapper fitsRepoPldgInstMapper;
   @Autowired
   private FitsRepoPldgInstService fitsRepoPldgInstService;

   @Autowired
   private BpmDispatcher bpmDispatcher;

   @Autowired
   private RedisDispatcher redisDispatcher;

   @Autowired
   private FitsServiceDispatcher fitsServiceDispatcher;

   @Autowired
   private FitsRepoPldgMgtMapper fitsRepoPldgMgtMapper;

   public void repoStartProcess(ProcessParamDto ppDto) {
      String result=bpmDispatcher.startProcess(JSON.toJSONString(ppDto));
      ReturnJsonDto returnJsonDto=JSON.parseObject(result,ReturnJsonDto.class);

      if("200".equals(returnJsonDto.getStatus())){
         //更新指令表状态
         int i=updateInstStatus(OrderFStatus.PENDING.getCode(),ppDto.getFormId());
         //更新审批表状态
         FitsBpmAprvFormEntity e = new FitsBpmAprvFormEntity(ppDto.getFormId(), Utils.getStrDateAndTime(), Utils.getStrDateAndTime(),
                 Utils.getStrDateAndTime(), "repo", "repo", OrderFStatus.PENDING.getCode(),
                 returnJsonDto.getResult().getProcessId() , returnJsonDto.getResult().getProcessStatus(),ppDto.getFDisplayattr());
         int j=fitsRepoBpmAprvFormMapper.saveFitsBpmAprvFormEntity(e);

         //发送消息到Redis
         if(i==1&&j==1){
            //禁用债券数量和金额
            disableFundAndBondFlash(ppDto,"+");

            BpmRedisDto rj=new BpmRedisDto(ppDto.getFormId(),StatusType.Sucess.getCode());
            redisDispatcher.send(JSON.toJSONString(rj));
         }else{
            BpmRedisDto rj=new BpmRedisDto(ppDto.getFormId(),StatusType.Fail.getCode());
            redisDispatcher.send(JSON.toJSONString(rj));
         }
      }
   }

   /**
    * 禁用债券数量和金额
    * @param ppDto
    */
   private void disableFundAndBondFlash(ProcessParamDto ppDto,String exp) {
      FitsPledgeInstResultDto pledgeDto= (FitsPledgeInstResultDto) fitsRepoPldgInstMapper.qryInstByAprvFormNum(ppDto.getFormId());

      if(pledgeDto!=null){
         FitsRepoPldgMgtDto dto=new FitsRepoPldgMgtDto(pledgeDto.getFFormNum(),"INST",pledgeDto.getFRepoCode());

         List<FitsRepoPldgMgtEntity> pldgmgt= fitsRepoPldgMgtMapper.findPldgMgtEntity(dto);

         String bmBondCode=""; //质押式多个债券代码以逗号隔开
         String bmBndTrdinam=""; //质押式多个债券代码对应多个债券数量，以逗号隔开
         String portSecu="";
         for (int i = 0; i < pldgmgt.size(); i++) {
            bmBondCode=bmBondCode+pldgmgt.get(i).getPBondCode()+",";
            //判断数量是否相减
            bmBndTrdinam="-".equals(exp)?"-"+bmBndTrdinam+pldgmgt.get(i).getPCount()+","
                    :bmBndTrdinam+pldgmgt.get(i).getPCount()+",";
            portSecu=portSecu+pldgmgt.get(i).getPPortfolioSecu()+",";
         }
         if(!"".equals(bmBondCode))bmBondCode=bmBondCode.substring(0,bmBondCode.lastIndexOf(","));
         if(!"".equals(bmBndTrdinam))bmBndTrdinam=bmBndTrdinam.substring(0,bmBndTrdinam.lastIndexOf(","));
         if(!"".equals(portSecu))portSecu=portSecu.substring(0,portSecu.lastIndexOf(","));

         //判断资金是否相减
         BigDecimal settleAmount="-".equals(exp)?pledgeDto.getFTrdSettleAmount().multiply(new BigDecimal(-1))
                 :pledgeDto.getFTrdSettleAmount();

         FundCashAndBondFlashDto flashDto=new FundCashAndBondFlashDto(portSecu,pledgeDto.getFCustAcctSecu(),
                 pledgeDto.getFPortfAcctCash(),pledgeDto.getFCustAcctCash(),"CNY",bmBondCode,settleAmount,bmBndTrdinam,"4",pledgeDto.getFTradeDirection());

         fitsServiceDispatcher.clacFundFlashAndBondFlash(JSON.toJSONString(flashDto));
      }
   }

   public PageInfo<?> repoApprovalQry(FitsRepoApprovalDto entity) {
      Map<String, Object> map = new HashMap<>();
      map.put("startDate", entity.getStartDate());
      map.put("endDate", entity.getEndDate());
      map.put("userId", entity.getUserId());
      map.put("overUserId", entity.getOverUserId());
      map.put("fStatus", entity.getFStatus());
      map.put("userName",entity.getUserName());
      String[] strArr=entity.getFStatus().split(",");
      List fStatusList =new ArrayList<>();
      for (int i = 0; i <strArr.length ; i++) {
         fStatusList.add(strArr[i]);
      }
      map.put("fStatusList",fStatusList);

      StringBuilder sb = new StringBuilder();
      sb.append(entity.getOrderColumn() + " " + entity.getOrderBy());
      PageHelper.startPage(entity.getPageNum(), entity.getPageSize(), sb.toString());
      return new PageInfo<>(fitsRepoApprovalMapper.queryFitsRepoApprovalList(map));
   }

   public int repoApproval(ProcessParamDto entity) {

      //验证指令
      String instStatus=queryInstStatus(entity.getFormId());
      if("".equals(instStatus)||!(InstStatus.PENDING.getCode().equals(instStatus)||InstStatus.APPROVALING.getCode().equals(instStatus)))return 2;//找不到指令记录或指令记录已被处理

      String fStatus=OrderFStatus.APPROVED.getCode();
      if("0".equals(entity.getFlag()))fStatus=OrderFStatus.APPROVALROLLBACK.getCode();
      if("1".equals(entity.getFlag()))fStatus=OrderFStatus.APPROVED.getCode();
      if("2".equals(entity.getFlag()))fStatus=OrderFStatus.APPROVALREFUSED.getCode();

      String result=bpmDispatcher.completeProcess(JSON.toJSONString(entity));
      ReturnJsonDto returnJsonDto=JSON.parseObject(result,ReturnJsonDto.class);

      if("200".equals(returnJsonDto.getStatus())){
         if("1".equals(returnJsonDto.getResult().getProcessStatus())&&OrderFStatus.APPROVED.getCode().equals(fStatus))fStatus=OrderFStatus.APPROVALING.getCode();

         //更新指令表状态
         updateInstStatus(fStatus,entity.getFormId());

         FitsBpmAprvFormEntity e=new FitsBpmAprvFormEntity(entity.getFormId(), fStatus);

         //归还质押券
         if("2".equals(entity.getFlag())||"0".equals(entity.getFlag())){
            //禁用债券数量和金额
            disableFundAndBondFlash(entity,"-");

            //fitsRepoPldgInstService.updateBandMgtAvailableByAprFormNum(entity.getFormId());
         }

         return fitsRepoBpmAprvFormMapper.updateFitsBpmAprvFormEntity(e);
      }
      return 0;
   }

   public int repoRestartProcess(ProcessParamDto ppDto) {
      String result=bpmDispatcher.completeProcess(JSON.toJSONString(ppDto));
      ReturnJsonDto returnJsonDto=JSON.parseObject(result,ReturnJsonDto.class);

      if("200".equals(returnJsonDto.getStatus())){
         //更新指令表状态
         updateInstStatus(OrderFStatus.PENDING.getCode(),ppDto.getFormId());

         FitsBpmAprvFormEntity e=new FitsBpmAprvFormEntity(ppDto.getFormId(), OrderFStatus.PENDING.getCode());

         //禁用债券数量和金额
         disableFundAndBondFlash(ppDto,"+");

         return fitsRepoBpmAprvFormMapper.updateFitsBpmAprvFormEntity(e);
      }
      return 0;
   }

   public int repoCancelProcess(ProcessParamDto ppDto) {

      //验证指令
      String instStatus=queryInstStatus(ppDto.getFormId());
      if("".equals(instStatus)|| !InstStatus.PENDING.getCode().equals(instStatus))return 2;//找不到指令记录或指令记录已被处理

      String result=bpmDispatcher.completeProcess(JSON.toJSONString(ppDto));
      ReturnJsonDto returnJsonDto=JSON.parseObject(result,ReturnJsonDto.class);

      if("200".equals(returnJsonDto.getStatus())){
         //更新指令表状态
         updateInstStatus(OrderFStatus.NEW.getCode(),ppDto.getFormId());
         //删掉之前审批表数据
         FitsBpmAprvFormEntity e=new FitsBpmAprvFormEntity(ppDto.getFormId(), OrderFStatus.NEW.getCode());
         //禁用债券数量和金额
         disableFundAndBondFlash(ppDto,"-");
         return fitsRepoBpmAprvFormMapper.delFitsBpmAprvFormEntity(e);
      }
      return 0;
   }

   private int updateInstStatus(String status,String formId){
      FitsRepoPldgInstEntity ee=new FitsRepoPldgInstEntity();
      ee.setAprvFormNum(formId);
      ee.setFInstStatus(status);
      return fitsRepoPldgInstMapper.updateFitsPledgeInstStatus(ee);
   }

   private String queryInstStatus(String formId){
      FitsPledgeInstResultDto instResultDto= (FitsPledgeInstResultDto) fitsRepoPldgInstMapper.qryInstByAprvFormNum(formId);
      if(instResultDto.getFInstStatus()!=null&&!"".equals(instResultDto.getFInstStatus()))return instResultDto.getFInstStatus();
      return "";
   }
}
