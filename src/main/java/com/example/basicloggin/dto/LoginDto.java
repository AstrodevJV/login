package com.example.basicloggin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LoginDto {

    @NotBlank(message = "Necesita correo para iniciar sesion")
    private String email;

    @NotBlank(message = "Necesita contraseña")
    private String password;
}
