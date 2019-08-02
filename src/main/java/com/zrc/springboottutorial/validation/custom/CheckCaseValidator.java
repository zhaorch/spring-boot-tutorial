package com.zrc.springboottutorial.validation.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {
    private CaseMode caseMode;
    public void initialize(CheckCase checkCase) {
        this.caseMode = checkCase.value();
    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }

//        constraintValidatorContext.disableDefaultConstraintViolation();
//        ConstraintValidatorContext.ConstraintViolationBuilder builder = constraintValidatorContext.buildConstraintViolationWithTemplate("名称必须大小写自定义ZRC");
//        builder.addConstraintViolation();

        if (caseMode == CaseMode.UPPER) {
            return s.equals(s.toUpperCase());
        } else {
            return s.equals(s.toLowerCase());
        }
    }
}
