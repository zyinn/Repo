package com.finruntech.frt.fits.pledge.controller;

import com.finruntech.frt.fits.pledge.commons.Constants;
import com.finruntech.frt.fits.pledge.commons.util.Utils;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgTransEntryDto;
import com.finruntech.frt.fits.pledge.service.FitsRepoPldgExeEntrustService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weihubin on 2018/1/4.
 * 指令执行、指令委托
 */
@RestController
@CrossOrigin
public class FitsRepoExeEntrustController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FitsRepoPldgExeEntrustService fitsRepoPldgExeEntrustService;

    /**
     * 指令执行->查询
     * @param msg
     * @return list
     */
    @RequestMapping(value = Constants.EXE_INST_QUERY, method = RequestMethod.POST)
    public ResponseEntity<?> exeInstQuery(@RequestBody String msg) {
        PageInfo<?> pageInfo = fitsRepoPldgExeEntrustService.exeInstQuery(msg);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }

    /**
     * 指令执行操作
     * @param msg
     * @return int
     */
    @RequestMapping(value = Constants.EXE_INVST_OPER, method = RequestMethod.POST)
    public ResponseEntity<?> exeInvstOper(@RequestBody String msg) {
        Map<String, Object> rtnMap = new HashMap<>();
        try {
            fitsRepoPldgExeEntrustService.exeInvstOper(msg);
            return ResponseEntity.ok().body(1);
        } catch (Exception e) {
            logger.error("exeInvstOper orror" , e);
            rtnMap.put("errMsg", e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(rtnMap);
    }


    /**
     * 交易委托查询
     * @param msg
     * @return list
     */
    @RequestMapping(value = Constants.ENTRUST_INVST_QUERY, method = RequestMethod.POST)
    public ResponseEntity<?> entrustInvstQuery(@RequestBody String msg) {
        PageInfo<?> pageInfo = fitsRepoPldgExeEntrustService.entrustInvstQuery(msg);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }


    /**
     * 根据根据指令编号查询对应委托记录
     * @param msg
     * @return list
     */
    @RequestMapping(value = Constants.QRY_ORDER_LIST_BY_INVST_NUM, method = RequestMethod.POST)
    public ResponseEntity<?> qryOrderlistByInvstNum(@RequestBody String msg) {
        PageInfo<?> pageInfo = fitsRepoPldgExeEntrustService.qryOrderlist(msg);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }


    /**
     * 委托初始化委托信息
     * @param msg
     * @return msg
     */
    @RequestMapping(value = Constants.ADD_ORDER_INIT_INFO, method = RequestMethod.POST)
    public ResponseEntity<?> addOrderInitInfo(@RequestBody String msg) {
        return ResponseEntity.ok().body(fitsRepoPldgExeEntrustService.addOrderInitInfo(msg));
    }


    /**
     * 委托操作-> 新增委托、撤销委托
     * @param msg
     * @return int
     */
    @RequestMapping(value = Constants.ORDER_OPER + "/{oper}", method = RequestMethod.POST)
    public ResponseEntity<?> orderOper(@RequestBody String msg, @PathVariable("oper") String oper) {
        Map<String, Object> rtnMap = new HashMap<>();
        try {
            fitsRepoPldgExeEntrustService.dealRepoPldgOrderOper(oper, msg);
            return ResponseEntity.ok().body(1);
        } catch (Exception e) {
            logger.error("orderOper orror" , e);
            rtnMap.put("errMsg", e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(rtnMap);
    }


    /**
     * 打印-委托单
     * @param msg
     * @return msg
     */
    @RequestMapping(value = Constants.PRINT_ORDER, method = RequestMethod.POST)
    public ResponseEntity<?> orderPrint(@RequestBody String msg) {
        return ResponseEntity.ok().body(fitsRepoPldgExeEntrustService.orderPrint(msg));
    }


    /**
     * 交易录入，委托查询
     * @param msg
     * @return list
     */
    @RequestMapping(value = Constants.TRADE_ENTRYQRY_ORDER, method = RequestMethod.POST)
    public ResponseEntity<?> tradeEntryQryOrder(@RequestBody String msg) {
        PageInfo<?> pageInfo = fitsRepoPldgExeEntrustService.tradeEntryQryOrder(msg);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }

    /**
     * 交易录入根据委托编号查询委托信息
     * @param msg
     * @return FitsRepoPldgTransEntryDto
     */
    @RequestMapping(value = Constants.TRADE_ENTERDETAIL_QRY, method = RequestMethod.POST)
    public ResponseEntity<?> tradeEnterDetailQry(@RequestBody String msg){
        FitsRepoPldgTransEntryDto frpteDto=fitsRepoPldgExeEntrustService.tradeEnterDetailQry(msg);
        return ResponseEntity.ok().body(frpteDto);
    }

}
