package com.mortality.api.exception;

import jakarta.persistence.NoResultException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Map<Class<? extends RuntimeException>, HttpStatus> EXCEPTION_STATUS_MAP = new HashMap<>();

    static {
        EXCEPTION_STATUS_MAP.put(NoResultException.class, HttpStatus.NOT_FOUND);
        EXCEPTION_STATUS_MAP.put(RuntimeException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        EXCEPTION_STATUS_MAP.put(DataIntegrityViolationException.class, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        return handleException(ex, ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(NoResultException ex) {
        return handleException(ex, ErrorCode.RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException ex) {
        return handleException(ex, ErrorCode.DUPLICATED_VALUE);
    }

    private ResponseEntity<Object> handleException(Exception ex, ErrorCode errorCode) {
        String message = errorCode.getMessage() + ": " + ex.getMessage();
        ErrorResponse apiError = new ErrorResponse(errorCode.getMessage(), message);
        return new ResponseEntity<>(apiError, getHttpStatus(errorCode));
    }

    private HttpStatus getHttpStatus(ErrorCode errorCode) {
        return EXCEPTION_STATUS_MAP.getOrDefault(errorCode.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
