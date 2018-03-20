package com.finruntech.frt.fits.pledge.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.finruntech.frt.fits.pledge.commons.enums.OrderFStatus;
import com.finruntech.frt.fits.pledge.commons.util.Utils;
import com.finruntech.frt.fits.pledge.model.*;
import com.finruntech.frt.fits.pledge.model.dto.*;
import com.finruntech.frt.fits.pledge.service.FitsRepoDealService;
import com.finruntech.frt.fits.pledge.service.SystemStatusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yinan.zhang on 2017/12/27.
 */
@Component
public class FitsPledgeInstConverter<T> {
    @Autowired
    private SystemStatusService systemStatusService;
    /**
     * msg to T
     *
     * @param msg msgJson
     * @return T
     */
    public T pledgeInstConverter(String msg) {
        return JSON.parseObject(msg, (Class<T>) FitsRepoPldgInstEntity.class);
    }

    public List<FitsRepoPldgMgtEntity> repoPldgMgtEntityConverter(JSONObject object) {
        JSONArray pldgMgtList1 = object.getJSONArray("pldgMgtList");
        return pldgMgtList1.toJavaList(FitsRepoPldgMgtEntity.class);
    }

    /**
     * msg to FitsRepoPldgBondMgtDto
     *
     * @param msg msgJson
     * @return FitsRepoPldgBondMgtDto
     */
    public FitsRepoPldgBondMgtDto fitsRepoPldgBondMgtDtoConverter(String msg) {
        return JSON.parseObject(msg, FitsRepoPldgBondMgtDto.class);
    }

    /**
     * FitsRepoPldgInstDto to FitsRepoPldgInstEntity
     *
     * @param dto FitsRepoPldgInstDto
     * @return FitsRepoPldgInstEntity
     */
    public T pledgeInstDtoConverter(FitsRepoPldgInstDto dto) {
        FitsRepoPldgInstEntity entity = new FitsRepoPldgInstEntity();
        BeanUtils.copyProperties(dto, entity);
        String date = Utils.getCurrentTimeMMSS();
        String currDate = systemStatusService.currDate();
        String currDateParse = Utils.parseTenToEightStr(currDate);
        String submitDate=currDateParse+date;
        entity.setFSubmitDate(submitDate);
        entity.setAuditCreateTime(submitDate);
        entity.setFBegDate(submitDate);
        entity.setFEndDate(submitDate);
        entity.setAuditCreatedBy(dto.getUserId());
        entity.setFInitiator(dto.getUserId());
        entity.setFTrdParValue(dto.getFTrdParValue()!=null? dto.getFTrdParValue().multiply(new BigDecimal(10000)) : null);
        entity.setFAmount(dto.getFAmount().multiply(new BigDecimal(10000)));
        entity.setFPledgeRatio(dto.getFPledgeRatio()!=null? dto.getFPledgeRatio().divide(new BigDecimal(100)):null);
        entity.setFRepoRate(dto.getFRepoRate().divide(new BigDecimal(100)));
        return (T) entity;
    }

    public String getCurrentTime(){
        String date = Utils.getCurrentTimeMMSS();
        String currDate = systemStatusService.currDate();
        String currDateParse = Utils.parseTenToEightStr(currDate);
        String currentTime=currDateParse+date;
        return currentTime;
    }
    public T pledgeInstEntityDtoConverter(FitsRepoPldgInstDto dto) {
        FitsRepoPldgInstEntity entity = new FitsRepoPldgInstEntity();
        BeanUtils.copyProperties(dto, entity);
        String date = Utils.getCurrentTimeMMSS();
        String currDate = systemStatusService.currDate();
        String currDateParse = Utils.parseTenToEightStr(currDate);
        String submitDate=currDateParse+date;
        entity.setFSubmitDate(submitDate);
        entity.setAuditModifiedTime(submitDate);
        entity.setAuditModifiedBy(dto.getUserId());
        entity.setFAmount(dto.getFAmount().multiply(new BigDecimal(10000)));
        entity.setFPledgeRatio(dto.getFPledgeRatio()!=null? dto.getFPledgeRatio().divide(new BigDecimal(100)):null);
        entity.setFRepoRate(dto.getFRepoRate().divide(new BigDecimal(100)));
        return (T) entity;
    }

    public T pledgeInstEntityConverter(FitsRepoPldgInstEntity dto,String userId) {
        FitsRepoPldgInstEntity entity = new FitsRepoPldgInstEntity();
        BeanUtils.copyProperties(dto, entity);
        String date = Utils.getCurrentTimeMMSS();
        String currDate = systemStatusService.currDate();
        String currDateParse = Utils.parseTenToEightStr(currDate);
        String submitDate=currDateParse+date;
        entity.setFSubmitDate(submitDate);
        entity.setAuditModifiedTime(submitDate);
        entity.setAuditModifiedBy(userId);
        entity.setFAmount(dto.getFAmount().multiply(new BigDecimal(10000)));
        entity.setFPledgeRatio(dto.getFPledgeRatio()!=null? dto.getFPledgeRatio().divide(new BigDecimal(100)):null);
        entity.setFRepoRate(dto.getFRepoRate().divide(new BigDecimal(100)));
        return (T) entity;
    }
    /**
     * FitsAccPortfolioSecuEntity to FitsRepoPldgInstEntity
     *
     * @param entity     FitsRepoPldgInstEntity
     * @param secuEntity FitsAccPortfolioSecuEntity
     * @return FitsAccPortfolioSecuEntity
     */
    public T pledgeInstConverterBySecuEntity(FitsRepoPldgInstEntity entity, FitsAccPortfolioSecuEntity secuEntity) {
        entity.setFPortfAcctSecu(secuEntity.getPsAcctNum());
        entity.setFPortfAcctCash(secuEntity.getPsCashNum());
        entity.setFInstStatus(OrderFStatus.NEW.getCode());
        //总应计利息
        entity.setFTrdTotolAi(entity.getFMatureAmount().subtract(entity.getFTrdSettleAmount()));
        return (T) entity;
    }
    public List<FitsRepoPldgMgtEntity> repoPldgMgtConverter(List<FitsRepoPldgMgtEntity> pldgMgtList,FitsPledgeInstResultDto resultDto){
        List<FitsRepoPldgMgtEntity> list = new ArrayList<>();
        pldgMgtList.forEach(entity->{
            FitsRepoPldgMgtEntity entity1 = new FitsRepoPldgMgtEntity();
            BeanUtils.copyProperties(entity,entity1);
            entity1.setSubJectType("INST");
            entity1.setPHairCut(entity.getPHairCut());
            entity1.setPDiscountRatio(new BigDecimal(100).subtract(entity1.getPHairCut()));
            entity1.setSubJectNum(resultDto.getFFormNum());
            entity1.setPRepoDirection(resultDto.getFTradeDirection());
            entity1.setPHostCash(resultDto.getFCustAcctCash());
            entity1.setPHostSecu(resultDto.getFCustAcctSecu());
            //2018-02-08 weihubin 质押券的投资组合资金账号、组合证券账号改由前台根据可入质的债券获取投资组合账号信息，
            // 而不是取交易本身的投资组合账号信息
            entity1.setPPortfolioCash(entity.getPPortfolioCash());
            entity1.setPPortfolioSecu(entity.getPPortfolioSecu());
            entity1.setPSettleDate1st(resultDto.getFSettleDate1st());
            entity1.setPSettleDate2nd(resultDto.getFSettleDate2nd());
            list.add(entity1);
        });
       return list;
    }
    public FitsRepoPldgQueryInstDto repoPldgQueryInstDtoConverter(FitsRepoPldgInstEntity entity){
        FitsRepoPldgQueryInstDto dto = new FitsRepoPldgQueryInstDto();
        dto.setStartDate(entity.getFTradeDate());
        dto.setEndDate(entity.getFTradeDate());
        dto.setFPortfAcctSecu(entity.getFPortfAcctCash());
        dto.setPageSize(10);
        dto.setPageNum(0);
        dto.setOrderColumn("fFormNum");
        dto.setOrderBy("DESC");
        return dto;
    }
    public T pledgeInstEntityConverterBySecuEntity(FitsRepoPldgInstEntity entity, FitsAccPortfolioSecuEntity secuEntity) {
        entity.setFPortfAcctSecu(secuEntity.getPsAcctNum());
        entity.setFPortfAcctCash(secuEntity.getPsCashNum());
//        entity.setFCustAcctCash(secuEntity.getPsCashNum());
//        entity.setFCustAcctSecu(secuEntity.getPsAcctNum());
        //总应计利息
        entity.setFTrdTotolAi(entity.getFMatureAmount().subtract(entity.getFTrdSettleAmount()));
        return (T) entity;
    }
    public List<FitsRepoPldgMgtEntity> getPldgMgtList(FitsRepoPldgInstEntity instEntity, List<FitsRepoPldgMgtEntity> list){
        List<FitsRepoPldgMgtEntity> repoPldgMgtEntityList = new ArrayList<>();
        list.forEach(entity->{
            entity.setPHostCash(instEntity.getFCustAcctCash());
            entity.setPHostSecu(instEntity.getFCustAcctSecu());
            entity.setPRepoDirection(instEntity.getFTradeDirection());
            entity.setPPortfolioCash(entity.getPPortfolioCash());
            entity.setPPortfolioSecu(entity.getPPortfolioSecu());
            entity.setPSettleDate1st(instEntity.getFSettleDate1st());
            entity.setPSettleDate2nd(instEntity.getFSettleDate2nd());
            repoPldgMgtEntityList.add(entity);
        });
        return repoPldgMgtEntityList;
    }
    public FitsRepoAttrEntity fitsRepoAttrConverter(FitsRepoAttrDto dto) {
        FitsRepoAttrEntity entity = new FitsRepoAttrEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

   public FitsRepoPldgQueryInstDto repoDealConverter(String msg){
       return JSON.parseObject(msg,FitsRepoPldgQueryInstDto.class);
   }

    public FitsRepoPldgBondMgtEntityDto converterFitsRepoPldgBondMgtEntityDto(FitsRepoPldgInstEntity instEntity, FitsRepoPldgMgtEntity entity) {
        FitsRepoPldgBondMgtEntityDto MgtEntity = new FitsRepoPldgBondMgtEntityDto();
        MgtEntity.setBmBondCode(entity.getPBondCode());
        MgtEntity.setBmBondAvailAble(entity.getPCount());
        MgtEntity.setBmPortfolioSecu(entity.getPPortfolioSecu());
        MgtEntity.setBmPortfolioCash(entity.getPPortfolioCash());
        MgtEntity.setBmHostCash(instEntity.getFCustAcctCash());
        MgtEntity.setBmHostSecu(instEntity.getFCustAcctSecu());
        MgtEntity.setBmBondPledge(entity.getPCount());
        return MgtEntity;
    }
    public FitsRepoPldgMgtDto converterFitsRepoPldgMgtDto( FitsPledgeInstResultDto resultDto) {
        FitsRepoPldgMgtDto mgtDto = new FitsRepoPldgMgtDto();
        mgtDto.setSubJectNum(resultDto.getFFormNum());
        mgtDto.setPRepoCode(resultDto.getFRepoCode());
        mgtDto.setSubJectType("INST");
        return mgtDto;
    }

    public FitsRepoPldgInstEntity converterFitsRepoPldgInstEntity( FitsPledgeInstResultDto resultDto) {
        FitsRepoPldgInstEntity instEntity = new FitsRepoPldgInstEntity();
        BeanUtils.copyProperties(resultDto,instEntity);
        return instEntity;
    }

}
