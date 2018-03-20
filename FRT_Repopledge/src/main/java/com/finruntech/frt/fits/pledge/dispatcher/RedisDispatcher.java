package com.finruntech.frt.fits.pledge.dispatcher;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * Created by zws
 */
@Component
public class RedisDispatcher extends AbstractDispatcher {
    private String patternTopic;
    public String send(String msg) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(getRedisMessageService().resolve(patternTopic), msg, String.class);
    }

    public void setPatternTopic(String patternTopic) {
        this.patternTopic = patternTopic;
    }
}
