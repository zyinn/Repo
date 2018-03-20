package com.finruntech.frt.fits.pledge.dispatcher;

import com.finruntech.frt.fits.pledge.commons.util.RestTemplateUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class FitsServiceDispatcher extends AbstractDispatcher {

    public void clacFundFlashAndBondFlash(String msg) {
        RestTemplate restTemplate = RestTemplateUtil.getRestTemplate();
        restTemplate.postForObject(getComputeSrvMessageService().resolve("clacFundFlashAndBondFlash") , msg, String.class);
    }
}
