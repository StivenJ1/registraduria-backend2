package com.example.registraduriabackseguridad.exceptions.advice;

import com.example.registraduriabackseguridad.dtos.response.ErrorDto;
import com.example.registraduriabackseguridad.exceptions.MinticException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class MinticExceptionHandler {
    @ExceptionHandler(value = MinticException.class)
    protected ResponseEntity<Object> handleException(MinticException ex, WebRequest request) {
        HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();
        ErrorDto error = ErrorDto.builder()
                .message(ex.getMessage())
                .statusCode(ex.getStatusCode())
                .transactionDate(ex.getTransactionDate())
                .route(httpServletRequest.getRequestURI() + "?" + httpServletRequest.getQueryString())
                .method(httpServletRequest.getMethod())
                .build();
        ResponseEntity<Object> response = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(
            value = {
                    MethodArgumentNotValidException.class
            }
    )
    protected ResponseEntity<Object> handleException(MethodArgumentNotValidException ex, WebRequest request) {
        HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();
        List<String> errors = new ArrayList<>();
        //foreach que recorre los errores y se debe ir construyendo el error
        for(final FieldError error: ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ":" + error.getDefaultMessage());
        }
        //foreach que recorre los errores y se debe ir construyendo el erro global
        for(final ObjectError error: ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ":" + error.getDefaultMessage());
        }
        ErrorDto error = ErrorDto.builder()
                .message(String.join("\n",errors)) //tranforma el mensaje de error
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .transactionDate(new Date())
                .route(httpServletRequest.getRequestURI() + "?" + httpServletRequest.getQueryString())
                .method(httpServletRequest.getMethod())
                .build();
        ResponseEntity<Object> response = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        return response;
    }
}
