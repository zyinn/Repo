package com.finruntech.frt.fits.pledge.repository;

import com.finruntech.frt.fits.pledge.model.FrtFitsSystemStatusEntity;
import org.springframework.stereotype.Repository;

/**
 * 系统Mapper
 * Created by lenovo on 2017/12/7.
 */
@Repository("systemStatusMapper")
public interface SystemStatusMapper<T> {
    /**
     * 查询系统时间
     * @param t T
     * @return FrtFitsSystemStatusEntity
     */
    FrtFitsSystemStatusEntity selectSystemStatus(T t);

    /**
     * 查询SystemConfig信息
     * @param t T
     * @return T
     */
    T selectSystemConfig(T t);
}
