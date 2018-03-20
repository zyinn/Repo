package com.finruntech.frt.fits.pledge.commons.util.exception;

/**
 * 异常包装类
 * 在包装时装入原始异常对象, 以便于需要输出调用堆栈
 * Created by yinan.zhang on 2017/5/31.
 */
public class FitsException extends RuntimeException {
    private ExceptionType exceptionType;

    /**
     * 使用 特定 异常类型, 包装原始异常对象
     */
    public FitsException(ExceptionType exceptionType, Exception e) {
        super(e);
        this.exceptionType = exceptionType;
    }

     /**
     * 使用特定异常类型, 和消息字符串包装出异常对象
     * @param exceptionType
     * @param message
     */
    public FitsException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }

    @Override
    public String toString() {
        return "RuntimeException [exceptionType=" + exceptionType.getExceptionCode() + "]";
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }
}
