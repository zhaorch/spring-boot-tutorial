package com.zrc.springboottutorial.controller;

import com.zrc.springboottutorial.error.BussinessException;
import com.zrc.springboottutorial.error.EmBusinessError;
import com.zrc.springboottutorial.response.CommonReturnType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class BaseController {
    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";


    //定义exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    //@ResponseStatus(HttpStatus.OK)
    //@ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof BussinessException){
            //把其他异常强转为bussinessException
            BussinessException bussinessException = (BussinessException)ex;

            responseData.put("errCode", bussinessException.getErrCode());
            responseData.put("errMsg", bussinessException.getErrMsg());
        }else if(ex instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) ex;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            String message = "";
            for (ConstraintViolation<?> item : violations) {
                /**打印验证不通过的信息*/
                message+=item.getMessage()+";";
            }
            responseData.put("errCode", EmBusinessError.VALIDATOR_ERROR.getErrCode());
            responseData.put("errMsg", message);
        }
        else {
            responseData.put("errCode", EmBusinessError.UNKNOW_ERROR.getErrCode());
            responseData.put("errMsg", ex.getMessage());
        }
        return CommonReturnType.create(responseData, "fail");
    }

    //判断是否是ajax请求
    public static boolean isAjax(HttpServletRequest httpRequest){
        return (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
    }
}
