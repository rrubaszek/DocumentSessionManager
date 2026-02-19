package com.example.DocumentSessionManager.shared.exception;

import com.example.DocumentSessionManager.document.domain.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DocumentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleDocumentNotFound(DocumentNotFoundException ex) {
        return new ErrorResponse(
                "DOCUMENT_NOT_FOUND",
                ex.getMessage()
        );
    }

    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleElementNotFound(ElementNotFoundException ex) {
        return new ErrorResponse(
                "ELEMENT_NOT_FOUND",
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        return new ErrorResponse(
                "VALIDATION_ERROR",
                "Invalid request body"
        );
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleOptimisticLocking(ObjectOptimisticLockingFailureException ex) {
        return new ErrorResponse(
                "VERSION_CONFLICT",
                "Document was modified by another user"
        );
    }
}
