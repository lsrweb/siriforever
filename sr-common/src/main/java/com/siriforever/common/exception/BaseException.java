package com.siriforever.common.exception;

public abstract class BaseException extends RuntimeException {
    private final String errorCode;
    private final String errorType;

    public BaseException(String errorCode, String errorType, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    // 获取错误码和类型（供子类继承）
    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorType() {
        return errorType;
    }
}