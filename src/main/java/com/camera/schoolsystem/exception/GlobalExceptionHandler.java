package com.camera.schoolsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    ExceptionTO malformedRequest = new ExceptionTO("MALFORMED REQUEST");
    ExceptionTO notFound = new ExceptionTO("NOT FOUND");
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionTO> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionTO> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionTO> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(malformedRequest);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionTO> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(malformedRequest);
    }
}
