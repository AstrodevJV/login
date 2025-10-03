package com.example.basicloggin.dto;

import com.example.basicloggin.model.UserRol;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class UserCreateDto {

    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Tiene que ser un correo valido")
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private String name;

    @JsonProperty("edad")
    private Integer edad;

    @JsonProperty("rol")
    private UserRol userRol;
}
