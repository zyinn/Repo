package com.finruntech.frt.fits.pledge.dispatcher;

import com.finruntech.frt.fits.pledge.commons.util.RestTemplateUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * Created by zws 
 */
@Component
public class BpmDispatcher extends AbstractDispatcher {
    public String send(String msg) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(getBpmMessageService() + msg, String.class);
    }
    
    /**
     * 流程开始
     * @param msg
     * @return
     */
    public String startProcess(String msg) {
        RestTemplate restTemplate = RestTemplateUtil.getRestTemplate();
        return restTemplate.postForObject(getBpmMessageService()+"startProcess", msg, String.class);
    }
    
    /**
     * 完成流程
     * @param msg
     * @return
     */
    public String completeProcess(String msg) {
        RestTemplate restTemplate = RestTemplateUtil.getRestTemplate();
        return restTemplate.postForObject(getBpmMessageService()+"completeProcess", msg, String.class);
    }
    
    /**
     * 历史查询
     * @param msg
     * @return
     */
    public String hisQuery(String msg) {
        RestTemplate restTemplate = RestTemplateUtil.getRestTemplate();
        return restTemplate.postForObject(getBpmMessageService()+"hisQuery", msg, String.class);
    }
    
}
