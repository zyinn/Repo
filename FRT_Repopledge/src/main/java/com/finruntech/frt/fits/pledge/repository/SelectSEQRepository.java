package com.finruntech.frt.fits.pledge.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 获取序列值
 */
@Repository
public class SelectSEQRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 获取fits序列值
     * @return
     */
    public String getFitsSeq(String seqName){
        String sqlString = "SELECT\n" +
                seqName+".NEXTVAL AS \"num\"\n" +
                "FROM\n" +
                "    DUAL";
        Map<String,Object> numMap  = jdbcTemplate.queryForMap(sqlString);
        return numMap.get("num").toString();
    }



}
