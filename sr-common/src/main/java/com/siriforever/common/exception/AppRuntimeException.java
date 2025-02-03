package com.siriforever.common.exception;

public class AppRuntimeException extends BaseException {
    // 业务错误码（如参数校验失败、权限不足等）
    public AppRuntimeException(String errorCode, String message,
            String errorType) {
        super(errorCode, "Runtime", message);
    }
}