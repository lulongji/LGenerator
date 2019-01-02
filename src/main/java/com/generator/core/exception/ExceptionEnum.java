package com.generator.core.exception;

/**
 * @Description:
 * @Author: lulongji
 * @Date: Created in 10:09 2018/9/29
 */
public enum ExceptionEnum {
    UNKONW_ERROR("-1", "未知错误"),
    SUCCESS("200", "success"),;

    private String code;

    private String info;

    ExceptionEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {

        return code;
    }

    public String getInfo() {
        return info;
    }
}