package com.zrc.springboottutorial.validation;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class ValidatorConfiguration {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        /**设置validator模式为快速失败返回*
         *  普通模式是验证所有的字段
         *  快读失败返回模式是遇到失败就直接返回，不再继续验证
         */
        // postProcessor.setValidator(validator());
        return postProcessor;
    }

    @Bean
    public Validator validator(){
        //设置validator模式为快速失败返回  经实验 Model 的校验直接使用该方法，Method的校验是上面的那个方法
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                //.addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }
}