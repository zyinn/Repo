package com.finruntech.frt.fits.pledge.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 指令单
 * Created by yinan.zhang on 2017/12/27.
 */
@Repository("fitsRepoPldgInstMapper")
public interface FitsRepoPldgInstMapper<T> {
    /**
     * 新增投资指令单
     * @param t T
     * @return int
     */
    int saveFitsPledgeInst(T t);

    /**
     * 修改投资指令单
     * @param t T
     * @return int
     */
    int updateFitsPledgeInst(T t);

    /**
     * 根据Map查询指令单
     * @param map Map
     * @return list T
     */
    List<T> queryFitsPledgeInst(Map<String, String> map);

    /**
     * 指令执行-查询（已执行、未执行）
     * @param t T
     * @return list
     */
    List<T> exeInstQuery(T t);

    /**
     * 修改指令单状态
     * @param t T
     * @return int
     */
    int updateFitsPledgeInstStatus(T t);

    /**
     * 根据单号查询指令单信息
     * @param fFormNum
     * @return T
     */
    T qryInstByFormNum(String fFormNum);

    /**
     * 根据审批单号查询指令单信息
     * @param aprvFormNum
     * @return T
     */
    T qryInstByAprvFormNum(String aprvFormNum);

    /**
     * 根据日期查询指令单
     * @param currentDate 日期
     * @return list T
     */
    List<T> clrQueryResultAprvFormNum(String currentDate);

    /**
     * 根据指令单号修改指令单信息
     * @param formNum 单号
     * @return int
     */
    int updateFitsPledgeInstByFormNum(String formNum);
}
