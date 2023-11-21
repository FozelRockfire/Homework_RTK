package com.rtk.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementExceptionException(NoSuchElementException ex) {
        log.error("Ошибка: " + ex.getMessage(), ex);
        return new ResponseEntity<>("Ошибка: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleRuntimeException(Exception ex) {
        log.error("Ошибка: " + ex.getMessage(), ex);
        return new ResponseEntity<>("Ошибка: " + ex.getMessage(), HttpStatus.CONFLICT);
    }
}