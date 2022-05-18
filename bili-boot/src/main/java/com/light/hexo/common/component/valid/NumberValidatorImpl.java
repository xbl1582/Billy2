package com.light.hexo.common.component.valid;

import org.apache.commons.lang3.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: NumberValidatorImpl
 * @ProjectName hexo-boot
 * @Description: 数字检查
 */
public class NumberValidatorImpl implements ConstraintValidator<NumberValidator, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isNumeric(value);
    }
}
