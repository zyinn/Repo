package com.finruntech.frt.fits.pledge.dispatcher;

import com.finruntech.frt.fits.pledge.commons.util.RestTemplateUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lenovo on 2017/5/16.
 */
@Component
public class RiskMGTDispatcher extends AbstractDispatcher {
    public String send(String msg) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(getRiskMGTMessageService() + msg, String.class);
    }
    
    public String sendPost(String metohPath,String msg) {
        RestTemplate restTemplate = RestTemplateUtil.getRestTemplate();
        return restTemplate.postForObject(getRiskMGTMessageService()+metohPath, msg, String.class);
    }
}
