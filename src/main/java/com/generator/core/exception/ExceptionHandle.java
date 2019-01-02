package com.generator.core.exception;


import com.yuntongxun.base.bean.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: lulongji
 * @Date: Created in 10:04 2018/9/29
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandle.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result catchFromService(Exception e) {
        if (e instanceof BootException) {
            BootException bootException = (BootException) e;
            return Result.error("500", bootException.getMessage());
        }
        LOGGER.error("【系统异常】{}", e);
        return resultError(ExceptionEnum.UNKONW_ERROR);
    }


    /**
     * @param exceptionEnum
     * @return
     */
    public Result resultError(ExceptionEnum exceptionEnum) {
        return Result.error(exceptionEnum.getCode(), exceptionEnum.getInfo());
    }
}