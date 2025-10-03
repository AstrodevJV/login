package com.example.basicloggin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserCreateGoogleDto {

    @NotBlank(message = "Necesarioo")
    private Map<String, String> map = new HashMap<>();
}
