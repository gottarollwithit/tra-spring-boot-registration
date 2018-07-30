package com.gottarollwithit.traspringbootregistration.helper;


import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchValidator implements ConstraintValidator<Match, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final Match constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        BeanWrapperImpl wrapper = new BeanWrapperImpl(value);

        final Object firstObj = wrapper.getPropertyValue(firstFieldName);
        final Object secondObj = wrapper.getPropertyValue(secondFieldName);

        return (firstObj == null && secondObj == null) || firstObj != null && firstObj.equals(secondObj);
    }
}