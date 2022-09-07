package com.scs.common.exception;

import com.scs.common.core.domain.JsonResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 全局异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult<Boolean> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return JsonResult.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public JsonResult<Boolean> handleRuntimeException(RuntimeException e) {
        return JsonResult.error(e.getMessage());
    }
}
