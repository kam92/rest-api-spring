package com.camera.schoolsystem.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@ControllerAdvice
@Slf4j
class ExceptionHandlingControllerAdvice {

    //Esse método pega cada validação que falhou no POST e devolve na mensagem o que o usuário precisa corrigir
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : constraintViolations) {
            errors.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
        }

        ErrorMessage errorMessage = new ErrorMessage("Erro ao validar campos", HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorMessage errorMessage = new ErrorMessage("Email informado está em uso.", HttpStatus.CONFLICT, null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorMessage errorMessage = new ErrorMessage("Requisição mal formatada.", HttpStatus.BAD_REQUEST, null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler({NoSuchElementException.class, NotFoundException.class})
    public ResponseEntity<ErrorMessage> handleNoHandlerFoundException(NoSuchElementException ex) {
        ErrorMessage errorResponse = new ErrorMessage("Não encontrado.", HttpStatus.NOT_FOUND, null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler({SQLException.class, TransactionSystemException.class})
    public ResponseEntity<ErrorMessage> handleTransactionSystemException(TransactionSystemException ex) {
        ErrorMessage errorMessage = new ErrorMessage("Erro de banco de dados.", HttpStatus.INTERNAL_SERVER_ERROR, null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(ValidationException ex) {
        ErrorMessage errorMessage = new ErrorMessage("Erro ao validar campo.", HttpStatus.BAD_REQUEST, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorMessage> handleServiceException(ServiceException se) {
        log.error("Ocorreu uma ServiceEXCEPTION!: " + se);
        ErrorMessage errorMessage = new ErrorMessage("Erro interno.", HttpStatus.INTERNAL_SERVER_ERROR, null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> handleNullPointerException(NullPointerException ne) {
        log.error("Ocorreu um NullPointerEXCEPTION!: " + ne);
        ErrorMessage errorMessage = new ErrorMessage("Erro interno.", HttpStatus.INTERNAL_SERVER_ERROR, null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        log.error("Ocorreu uma EXCEPTION!: " + e);
        ErrorMessage errorMessage = new ErrorMessage("Erro interno.", HttpStatus.INTERNAL_SERVER_ERROR, null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}