package com.finruntech.frt.fits.pledge.commons.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/6/9.
 */
public class RestTemplateUtil {

    /**
     * RestTemplate 解决中文乱码
     * @return
     */
    public static RestTemplate getRestTemplate() {
        StringHttpMessageConverter stringHttpMessageConverter = new
                StringHttpMessageConverter(Charset.forName("UTF-8"));
        List<HttpMessageConverter<?>> messageConverters =  new ArrayList<>();
        messageConverters.add(stringHttpMessageConverter);
        return new RestTemplate(messageConverters);
    }

    /**
     * 设置headers ContentType
     * @param msg json
     * @return HttpEntity
     */
    public static HttpEntity setHttpHeaders(String msg) {
        HttpHeaders headers =new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(msg, headers);
    }
}
