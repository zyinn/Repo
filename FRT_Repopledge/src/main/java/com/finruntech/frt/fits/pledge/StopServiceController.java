package com.finruntech.frt.fits.pledge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ConfigurableWebApplicationContext;

/**
 * stop service stoprepopledge
 * Created by yinan.zhang on 2018/01/22.
 */
@RestController
public class StopServiceController {

    @Autowired
    private ApplicationContext ctx;

    /**
     * stop this server
     */
    @GetMapping("stoprepopledge")
    public void main() {
        if ((ctx instanceof ConfigurableWebApplicationContext)) {
            ((ConfigurableWebApplicationContext)ctx).close();
        }
    }


}
