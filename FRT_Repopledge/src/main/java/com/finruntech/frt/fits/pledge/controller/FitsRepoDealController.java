package com.finruntech.frt.fits.pledge.controller;

import com.finruntech.frt.fits.pledge.commons.Constants;
import com.finruntech.frt.fits.pledge.commons.util.Utils;
import com.finruntech.frt.fits.pledge.converter.FitsPledgeInstConverter;
import com.finruntech.frt.fits.pledge.model.dto.FitsRepoPldgQueryInstDto;
import com.finruntech.frt.fits.pledge.service.FitsRepoDealService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 成交
 * Created by weihubin on 2018/1/8.
 */
@RestController
@CrossOrigin
public class FitsRepoDealController {

    @Autowired
    private FitsRepoDealService fitsRepoDealService;
    @Autowired
    private FitsPledgeInstConverter pledgeInstConverter;


    /**
     * 交易录入提交
     * @param msg
     * @return
     */
    @RequestMapping(value = Constants.TRADE_ENTRY_SUBMIT, method = RequestMethod.POST)
    public ResponseEntity<?> tradeEntryAubmit(@RequestBody String msg) {
        fitsRepoDealService.tradeEntryAubmit(msg);
        return ResponseEntity.ok().body(1);
    }

    /**
     * 接收银行间成交回报处理
     * @param msg
     * @return
     */
    @RequestMapping(value = Constants.REPO_PLDG_MATCH_TRANS, method = RequestMethod.POST)
    public ResponseEntity<?> repopldgMatchTrans(@RequestBody String msg) {
        fitsRepoDealService.repopldgMatchTrans(msg);
        return ResponseEntity.ok().body(1);
    }


    @RequestMapping(value = Constants.FIND_REPO_DEAL, method = RequestMethod.POST)
    public ResponseEntity<?> findRepoDeal(@RequestBody String msg) {
        FitsRepoPldgQueryInstDto dto = pledgeInstConverter.repoDealConverter(msg);
        PageInfo<?> pageInfo = fitsRepoDealService.findRepoDealService(dto);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }


    /**
     * 交易匹配->成交列表
     * @param msg
     * @return
     */
    @RequestMapping(value = Constants.MATCH_DEAL_LIST, method = RequestMethod.POST)
    public ResponseEntity<?> matchDealList(@RequestBody String msg) {
        PageInfo<?> pageInfo = fitsRepoDealService.matchDealList(msg);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }


    /**
     * 交易匹配->委托列表
     * @param msg
     * @return
     */
    @RequestMapping(value = Constants.MATCH_ORDER_LIST, method = RequestMethod.POST)
    public ResponseEntity<?> matchOrderList(@RequestBody String msg) {
        PageInfo<?> pageInfo = fitsRepoDealService.matchOrderList(msg);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }


}
