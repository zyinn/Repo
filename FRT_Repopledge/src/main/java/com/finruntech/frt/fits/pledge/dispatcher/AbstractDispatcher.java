package com.finruntech.frt.fits.pledge.dispatcher;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by iunmzi on 17-5-4.
 */
public class AbstractDispatcher {
    private static Logger logger = Logger.getLogger(AbstractDispatcher.class);

    @Value("${fits.bpm.message.server}")
    private String fitsBpmMessageServer;

    @Value("${fits.mq.message.server}")
    private String fitsMQMessageServer;

    @Value("${fits.riskMGT.message.server}")
    private String fitsRiskMGTMessageServer;

    @Value("${fits.redis.message.server}")
    private String fitsRedisMessageServer;

    @Value("${fits.service.message.server}")
    private String fitsServiceMessageServer;

    /**
     * 获取Bpm-URI
     * @return
     */
    protected URI getBpmMessageService() {
        return getRUI(fitsBpmMessageServer);
    }

    protected URI getMQMessageService() {
        return getRUI(fitsMQMessageServer);
    }

    protected URI getRedisMessageService() {
        return getRUI(fitsRedisMessageServer);
    }
    /**
     * 获取service-URI
     * @return
     */
    protected URI getComputeSrvMessageService() {
        return getRUI(fitsServiceMessageServer);
    }

    /**
     * 获取RiskMgt-URI
     * @return
     */
    protected URI getRiskMGTMessageService() {
        return getRUI(fitsRiskMGTMessageServer);
    }

    protected URI getRUI(String fitsMsgServer){
        try {
            return new URI(fitsMsgServer);
        } catch (URISyntaxException e) {
            logger.error("URI is error:"+e.getMessage());
            return null;
        }
    }
}
