package com.finruntech.frt.fits.pledge.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 指令Mapper
 * Created by weihubin on 2017/12/27.
 */
@Repository("fitsRepoPldgOrdMapper")
public interface FitsRepoPldgOrdMapper<T> {
    /**
     * 新增指令
     * @param t T
     * @return int
     */
    int insertRepoPldgOrd(T t);

    /**
     * 指令下委托记录数
     * @param instNum
     * @return int
     */
    int ordCountByInstNum(String instNum);

    /**
     * 根据委托编号删除委托信息
     * @param ordNum
     * @return int
     */
    int delRepoPldgOrdByOrdNum(String ordNum);

    T findRepoPldgOrdByOrdNum(String ordNum);

    List<T> findRepoPldgOrd(T t);

    /**
     * 修改指令状态
     * @param t
     * @return
     */
    int updateInvstStatus(T t);

    /**
     * 交易录入，委托查询
     * @param t
     * @return
     */
    List<T> tradeEntryQryOrder(T t);


    /**
     * 交易匹配委托单
     * @param t
     * @return
     */
    List<T> dealMatchOrder(T t);

    /**
     * 修改委托单状态等信息
     * @param t
     * @return
     */
    int updateRepoPldgOrd(T t);

    T tradeEnterDetailQry(String ordNum);

}
