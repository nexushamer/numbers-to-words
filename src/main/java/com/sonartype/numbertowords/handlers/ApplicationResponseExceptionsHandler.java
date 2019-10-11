package com.sonartype.numbertowords.handlers;

import com.sonartype.numbertowords.exceptions.InvalidNumberException;
import com.sonartype.numbertowords.models.MessageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationResponseExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidNumberException.class})
    protected ResponseEntity<Object> handlerPersistenceError(InvalidNumberException ex, WebRequest request) {
        return handleExceptionInternal(ex, new MessageResponse(ex.getDescription()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
