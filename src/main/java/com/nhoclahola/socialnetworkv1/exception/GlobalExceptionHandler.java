package com.nhoclahola.socialnetworkv1.exception;

import com.nhoclahola.socialnetworkv1.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<?>> handlingAppException(AppException exception)
    {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setResponseCode(errorCode.getResponseCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(apiResponse);
    }

    // Spring validation exception
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> MethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
        String errors = exception.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + " - " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setResponseCode(errorCode.getResponseCode());
        apiResponse.setMessage(errorCode.getMessage() + ": " + errors);

        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(apiResponse);
    }

    // For wrong json request
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handlingAppException(HttpMessageNotReadableException exception)
    {
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setResponseCode(errorCode.getResponseCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(apiResponse);
    }
}
