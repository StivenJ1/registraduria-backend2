package com.example.registraduriabackseguridad.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MinticException extends RuntimeException {
    private int statusCode;
    private String message;
    private Date transactionDate;

    public MinticException(String message, int statusCode,Date transactionDate) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.transactionDate = transactionDate;
    }
}
