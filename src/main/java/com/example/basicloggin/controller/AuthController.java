package com.example.basicloggin.controller;


import com.example.basicloggin.dto.ResponseUserCreateDto;
import com.example.basicloggin.dto.UserCreateDto;
import com.example.basicloggin.model.UserModel;
import com.example.basicloggin.service.UserService;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseUserCreateDto> singUp(@Valid @RequestBody UserCreateDto createUserAccountDto) {
        val user = userService.addUser(createUserAccountDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
