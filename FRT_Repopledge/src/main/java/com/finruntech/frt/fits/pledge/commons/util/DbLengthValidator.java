package com.finruntech.frt.fits.pledge.commons.util;

import com.finruntech.frt.fits.pledge.commons.annotation.ValidDbLength;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by weihubin on 2018/2/6.
 *  数据库字段长度(包括中文、全角字符)校验
 */
public class DbLengthValidator implements ConstraintValidator<ValidDbLength, String> {

    private int  length;

    @Override
    public void initialize(ValidDbLength validDbLength) {
        this.length = validDbLength.length();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isBlank(value)) {
            return true;
        }
        int targetLength = this.length;
        int oriLength = value.length();
        int replacelength = value.replaceAll("[\\u4e00-\\u9fa5]", "") //替换中文
                .replaceAll("[\\uFE30-\\uFFA0]", "").length();//替换全角字符
        //中文 全角 存储数据库占3个字符
        return ((oriLength - replacelength) * 3 + replacelength)<=targetLength ? true : false ;
    }
}
