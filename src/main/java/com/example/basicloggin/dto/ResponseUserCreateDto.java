package com.example.basicloggin.dto;

import com.example.basicloggin.model.UserRol;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseUserCreateDto {

    @NotBlank(message = "Necesita correo")
    private String mail;

    @NotBlank(message = "Nececsita nombre")
    private String name;

    @NotBlank(message = "Necesita edad")
    private Integer age;

    @NotBlank(message = "Necesita genero")
    private String gender;

    @NotBlank(message = "Necesita genero")
    private UserRol role;

}
