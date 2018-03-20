package com.finruntech.frt.fits.pledge.commons.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解用于审批取消/指令撤单
 * Created by yinan.zhang on 2018/1/9.
 */
@Inherited
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface FitsApproval {

    String flag() default "";
}
