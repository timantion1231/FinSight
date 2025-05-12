package com.finsight.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Обработка исключений, связанных с отсутствием ресурса
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Обработка исключений, связанных с некорректными данными
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Обработка исключений, связанных с доступом к данным
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex, WebRequest request) {
        return new ResponseEntity<>("Ошибка доступа к данным: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Обработка всех остальных исключений
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Произошла ошибка: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<String> handleDuplicateEntityException(DuplicateEntityException ex, WebRequest request){
        return new ResponseEntity<>("Ошибка регистарции: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(DuplicateEntityException ex, WebRequest request){
        return new ResponseEntity<>("Ошибка регистарции: " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(DuplicateEntityException ex, WebRequest request){
        return new ResponseEntity<>("Ошибка регистарции: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}