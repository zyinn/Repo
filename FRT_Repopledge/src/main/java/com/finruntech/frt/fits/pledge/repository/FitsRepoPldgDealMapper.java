package com.finruntech.frt.fits.pledge.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 成交Mapper
 * Created by yinan.zhang on 2017/12/27.
 */
@Repository("fitsRepoPldgDealMapper")
public interface FitsRepoPldgDealMapper<T> {

    /**
     * 成交查询
     * @param map
     * @return list
     */
    List<T> findDealEntity(Map<String, String> map);

    /**
     * 新增成交
     * @param t T
     * @return int
     */
    int insertRepoPldgDeal(T t);

    /**
     * 修改成交
     * @param t T
     * @return int
     */
    int updateRepoPldgDeal(T t);


}
