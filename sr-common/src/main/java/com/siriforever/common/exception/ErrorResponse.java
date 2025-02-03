package com.siriforever.common.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code; // 错误码（如 "BUSINESS_ERROR"）
    private String message; // 错误描述
    private String type;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(String code, String message, String type) {
        this.code = code;
        this.message = message;
        this.type = type;
        this.timestamp = LocalDateTime.now(); // 自动生成时间戳
    }
}