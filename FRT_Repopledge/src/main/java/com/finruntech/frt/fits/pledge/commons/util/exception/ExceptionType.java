package com.finruntech.frt.fits.pledge.commons.util.exception;

/**
 * Created by yinan.zhang on 2017/5/31.
 * 异常类型接口。一般在实际项目中由enum实现。可以用于区分不同的异常。
 */
public interface ExceptionType {
    /**
     * @return 异常代码
     */
    String getExceptionCode();

    /**
     * @return 异常的中文类别名称，比如：“数据库异常”
     */
    String getExceptionInfoCN();
}
