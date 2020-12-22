package com.hgt.handler;

import com.hgt.pojo.Result;
import com.hgt.pojo.ResultCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;

/*全局异常处理
 * @RestControllerAdvice 针对所有Controller的集中预处理，更好的使业务代码与异常处理分开*/
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    /*说明:
     * 1、统一处理所有异常和自定义处理某个异常并不冲突
     * 2、自定义处理某个异常只会处理当前异常，其它异常会走统一异常处理
     * 3、如果发生多个异常，则只处理最先发生的异常*/


    /*这里将所有异常都统一处理，异常信息封装到Result
     * @ExceptionHandler 统一处理某一类异常，此处理了所有异常，自定义异常除外*/
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        String exception = getStackTrace(e);
        log.error(exception);
        return Result.fail(exception);
    }

    /*自定义缺少参数异常的处理
     * 有了自定义的异常情况,缺少参数时将会在此单独处理，其它异常还走上面的方法处理*/
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result missParamHandler() {
        return Result.fail(ResultCode.PARAM_MISSING);
    }

    /*获取异常的详细信息*/
    public static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
