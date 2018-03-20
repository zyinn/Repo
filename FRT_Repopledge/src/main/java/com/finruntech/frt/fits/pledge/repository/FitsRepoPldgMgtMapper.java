package com.finruntech.frt.fits.pledge.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 质押式回购质押
 * Created by yinan.zhang on 2018/1/10.
 */
@Repository("fitsRepoPldgMgtMapper")
public interface FitsRepoPldgMgtMapper<T> {
    /**
     *查询质押式回购质押信息
     * @param t T
     * @return list T
     */
    List<T> findRepoPldgMgtEntity(T t);
    /**
     *查询质押式回购质押信息
     * @param t T
     * @return list T
     */
    List<T> findRepoPldgSMgt(T t);
    /**
     *查询质押式回购质押信息
     * @param t T
     * @return list T
     */
    List<T> findPldgMgtEntity(T t);
    /**
     *添加质押式回购质押信息
     * @param list T
     * @return int
     */
    int saveRepoPldgMgtEntity(List<T> list);
    /**
     *删除质押式回购质押信息
     * @param t T
     * @return
     */
    int deleteRepoPldgMgtEntity(T t);

}
