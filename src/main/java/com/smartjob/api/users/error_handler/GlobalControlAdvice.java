package com.smartjob.api.users.error_handler;

import com.smartjob.api.users.error_handler.exceptions.EmailAlreadyExistsException;
import com.smartjob.api.users.error_handler.exceptions.InvalidPasswordException;
import com.smartjob.api.users.error_handler.exceptions.ResourceNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.smartjob.api.users.adapters.in.rest.constants.Messages.ERROR;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalControlAdvice extends ResponseEntityExceptionHandler {

    //Manejo personalizado de excepción para recursos no encontrados
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("status", ERROR);
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    protected ResponseEntity<Object> handleNotFoundException(EmailAlreadyExistsException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("status", ERROR);
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    protected ResponseEntity<Object> handleNotFoundException(InvalidPasswordException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("status", ERROR);
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //Manejo de excepcion para las validaciones en los argumentos que llegan por request
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", ERROR);


        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        body.put("message", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    //Maneja excepciones generales
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", ERROR);
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
