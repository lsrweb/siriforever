package com.siriforever.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(AppRuntimeException.class)
        public ResponseEntity<ErrorResponse> handleRuntimeException(AppRuntimeException ex) {
                log.warn("业务异常: {}", ex.getMessage());
                ErrorResponse response = new ErrorResponse(
                                ex.getErrorCode(),
                                ex.getMessage(),
                                ex.getErrorType());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        @ExceptionHandler(AppSystemException.class)
        public ResponseEntity<ErrorResponse> handleSystemException(AppSystemException ex) {
                log.error("系统异常: ", ex);
                ErrorResponse response = new ErrorResponse(
                                ex.getErrorCode(),
                                "系统繁忙，请稍后重试",
                                ex.getErrorType());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
                String errorMsg = ex.getBindingResult().getFieldErrors().stream()
                                .map(FieldError::getDefaultMessage)
                                .collect(Collectors.joining(", "));
                ErrorResponse response = new ErrorResponse(
                                "VALIDATION_ERROR",
                                errorMsg,
                                "Runtime");
                return ResponseEntity.badRequest().body(response);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
                log.error("未处理异常: ", ex);
                ErrorResponse response = new ErrorResponse(
                                "UNKNOWN_ERROR",
                                "系统发生未知错误",
                                "System");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
}