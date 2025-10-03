package com.example.basicloggin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "El correo ya se encuentra registrado")
public class CorreoDuplicadoException extends RuntimeException {
    public CorreoDuplicadoException(String message) {
        super(message);
    }
}
