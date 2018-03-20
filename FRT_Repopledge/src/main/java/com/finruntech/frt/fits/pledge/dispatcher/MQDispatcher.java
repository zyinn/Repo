package com.finruntech.frt.fits.pledge.dispatcher;

import com.finruntech.frt.fits.pledge.commons.util.RestTemplateUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
/**
 * Created by yinan.zhang on 2018/1/5.
 */
@Component
public class MQDispatcher extends AbstractDispatcher {
    String repoPldgInst="mqmsg/pledge/repoPldgInst";//指令审批
    String repoPldgTrans="mqmsg/pledge/repoPldgTrans";//成交信息
    String repoPldgSettleInst="mqmsg/pledge/repoPldgSettleInst";//结算指令

    public String send(String msg) {
        RestTemplate restTemplate =  RestTemplateUtil.getRestTemplate() ;
        return  restTemplate.postForObject(getMQMessageService().resolve(repoPldgInst), msg, String.class);
    }

    public String sendTrans(String msg) {
        RestTemplate restTemplate =  RestTemplateUtil.getRestTemplate() ;
        return  restTemplate.postForObject(getMQMessageService().resolve(repoPldgTrans), msg, String.class);
    }

    public String sendSettleInst(String msg) {
        RestTemplate restTemplate =  RestTemplateUtil.getRestTemplate() ;
        return  restTemplate.postForObject(getMQMessageService().resolve(repoPldgSettleInst), msg, String.class);
    }

}
