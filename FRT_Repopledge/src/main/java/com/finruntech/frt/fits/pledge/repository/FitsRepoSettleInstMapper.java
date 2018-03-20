package com.finruntech.frt.fits.pledge.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 结算Mapper
 * Created by lenovo on 2018/1/9.
 */
@Repository("fitsRepoSettleInstMapper")
public interface FitsRepoSettleInstMapper<T> {
    /**
     * 新增结算
     * @param t T 实体类
     * @return int
     */
    int insertRepoSettleInst(T t);

    /**
     * 结算查询
     * @param t T 实体类
     * @return int
     */
    List<T> settleInstQry(T t);
    /**
     * 根据主键查询结算信息
     * @param t T 实体类
     * @return T
     */
    T qrySettleInstByPk(T t);

    /**
     * 修改结算
     * @param t T 实体类
     * @return int
     */
    int updateSettleInst(T t);

    /**
     * 查询银行间结算
     * @param t T 实体类
     * @return list
     */
    List<T> nibRepoPldgQuery(T t);
}
