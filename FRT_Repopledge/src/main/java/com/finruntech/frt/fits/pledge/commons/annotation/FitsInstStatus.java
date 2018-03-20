package com.finruntech.frt.fits.pledge.commons.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解指令单状态
 * Created by yinan.zhang on 2018/1/9.
 */
@Documented
@Inherited
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FitsInstStatus {
    public enum InstStatus{NEW{String fitsResult(){ return "1"; }},
        PENDING{String fitsResult(){ return "2"; }},
        APPROVALING{String fitsResult(){ return "3"; }},
        APPROVED{String fitsResult(){ return "4"; }},
        APPROVALREFUSED{String fitsResult(){ return "5"; }},
        NOAPPROVAL{String fitsResult(){ return "6"; }},
        APPROVALROLLBACK{String fitsResult(){ return "7"; }},
        EXECUTION{String fitsResult(){ return "8"; }},
        INVALID{String fitsResult(){ return "9"; }}};

    public InstStatus fInstStatus() default InstStatus.NEW;
}
