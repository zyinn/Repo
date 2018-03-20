//package com.finruntech.frt.fits.pledge;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import javax.sql.DataSource;
//
///**
// * Created by yinan.zhang on 2017/12/28.
// * 用于测试环境的数据库链接配置。设置了profile为test，既在测试环境时使用的profle（见AbstractBabQuoteTest）。
// */
//@Component
//@Profile("local")
//public class TestDataBaseConfiguration {
//
//    @Bean(name = "FITS")
//    public DataSource h2DataSource() {
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        EmbeddedDatabase database = builder.setType(EmbeddedDatabaseType.H2)
//                .addScript("classpath:schema.sql") ///启动时初始化建表语句
//                .build();
//        return database;
//    }
//
//}