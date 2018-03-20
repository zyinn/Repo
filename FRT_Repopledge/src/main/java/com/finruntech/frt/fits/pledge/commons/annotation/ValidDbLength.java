package com.finruntech.frt.fits.pledge.commons.annotation;

import com.finruntech.frt.fits.pledge.commons.util.DbLengthValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 校验字段长度（包括中文、全角字符）
 * Created by weihubin on 2018/2/6.
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DbLengthValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Documented
public @interface ValidDbLength {
    String message() default "{ValidDbLength}";
    int length() default 0;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
