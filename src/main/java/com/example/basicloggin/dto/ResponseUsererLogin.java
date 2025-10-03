package com.example.basicloggin.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ResponseUsererLogin {

    @NotBlank(message = "no pude estar vacio")
    private String email;

    @NotBlank(message = "no puede estar vacio")
    private String jwt;
}
