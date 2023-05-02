package com.mine.cni.exception;

import com.mine.cni.enums.ExceptionEnums;
import lombok.Getter;

/**
 * @author CaoY
 * @date 2023-05-02 1:59
 * @description 自定义的异常类，系统异常（大异常，后面的异常都继承它）
 */
@Getter
public class SystemException extends RuntimeException {

    private String code;
    private String message;

    private static SystemException defaultInstance;

    public static SystemException instance() {
        if (defaultInstance == null) {
            synchronized (SystemException.class) {
                if (defaultInstance == null) {
                    defaultInstance = new SystemException(ExceptionEnums.SYSTEM_EXCEPTION.getCode(),
                            ExceptionEnums.SYSTEM_EXCEPTION.getMessage());
                }
            }
        }
        return defaultInstance;
    }

    public static SystemException instance(String message) {
        return new SystemException(ExceptionEnums.SYSTEM_EXCEPTION.getCode(), message);
    }

    // 这里的Code建议从枚举里面取或者单独定义一个枚举
    public static SystemException instance(String code, String message) {
        return new SystemException(code, message);
    }

    private SystemException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
