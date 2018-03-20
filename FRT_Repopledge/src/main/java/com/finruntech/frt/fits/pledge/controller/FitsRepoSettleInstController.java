package com.finruntech.frt.fits.pledge.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finruntech.frt.fits.pledge.commons.Constants;
import com.finruntech.frt.fits.pledge.commons.enums.SprocType;
import com.finruntech.frt.fits.pledge.commons.util.Utils;
import com.finruntech.frt.fits.pledge.service.FitsRepoSettleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2018/1/10.
 */
@RestController
@CrossOrigin
public class FitsRepoSettleInstController {
    @Autowired
    private FitsRepoSettleService fitsRepoSettleService;
    /**
     * 结算指令->查询
     * @param msg
     * @return
     */
    @RequestMapping(value = Constants.SETTLE_INST_QRY, method = RequestMethod.POST)
    public ResponseEntity<?> exeInstQuery(@RequestBody String msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        jsonObject.put("sType",  SprocType.DAYTIME.getCode());
        PageInfo<?> pageInfo = fitsRepoSettleService.settleInstQry(jsonObject);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }

    /**
     * 回购到期结算指令确认查询
     * @param msg
     * @return
     */
    @RequestMapping(value = Constants.MATURE_SETTLE_INST_QRY, method = RequestMethod.POST)
    public ResponseEntity<?> matureSettleInstQry(@RequestBody String msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        jsonObject.put("sType", SprocType.DAYEND.getCode());
        PageInfo<?> pageInfo = fitsRepoSettleService.settleInstQry(jsonObject);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }

    /**
     * 结算确认
     * @param msg
     * @return
     */
    @RequestMapping(value = Constants.SETTLE_INST_COMFIRM, method = RequestMethod.POST)
    public ResponseEntity<?> settleInstComfirm(@RequestBody String msg) {
        Map<String, Object> rtnMap = new HashMap<>();
        try {
            fitsRepoSettleService.settleInstComfirm(msg);
            return ResponseEntity.ok().body(1);
        } catch (Exception e) {
            rtnMap.put("errMsg", e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(rtnMap);
    }


    /**
     * 到期结算金额调整
     * @param msg
     * @return
     */
    @RequestMapping(value = Constants.MATURE_AMOUNT_MODIFY, method = RequestMethod.POST)
    public ResponseEntity<?> matureAmountModify(@RequestBody String msg) {
        fitsRepoSettleService.matureAmountModify(msg);
        return ResponseEntity.ok().body(1);
    }


    /**
     * 银行间回购查询
     * @param msg
     * @return
     */
    @RequestMapping(value = Constants.NIB_REPOPLDG_QUERY, method = RequestMethod.POST)
    public ResponseEntity<?> nibRepoPldgQuery(@RequestBody String msg){
        PageInfo<?> pageInfo = fitsRepoSettleService.nibRepoPldgQuery(msg);
        return ResponseEntity.ok().body(Utils.templateOut(pageInfo));
    }
}
