package com.finruntech.frt.fits.pledge;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.TimeZone;

/**
 * start FRT_Repopledge service main
 * @author yinan.zhang
 * @since 2017/12/25 15:22
 */
@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "com.finruntech.frt.fits.pledge.repository")
public class RepoApplication extends WebMvcConfigurerAdapter implements CommandLineRunner ,EmbeddedServletContainerCustomizer {
    private Logger logger = LoggerFactory.getLogger(RepoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RepoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("repurchase服务启动完成!");
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        final TimeZone zone = TimeZone.getTimeZone("GMT+8"); //获取中国时区
        TimeZone.setDefault(zone); //设置时区
    }
}
