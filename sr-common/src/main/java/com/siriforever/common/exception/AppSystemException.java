package com.siriforever.common.exception;

public class AppSystemException extends BaseException {
    // 系统错误码（如数据库连接失败、未知异常等）
    public AppSystemException(String errorCode, String message) {
        super(errorCode, "System", message);
    }
}