package com.example.basicloggin.controller;


import com.example.basicloggin.dto.ResponseUserCreateDto;
import com.example.basicloggin.dto.UserCreateDto;
import com.example.basicloggin.dto.UserCreateGoogleDto;
import com.example.basicloggin.model.UserModel;
import com.example.basicloggin.service.UserService;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("/google")
    public ResponseEntity<ResponseUserCreateDto> google(@Valid @RequestBody UserCreateGoogleDto createUserAccountDto) {
        String idToken = createUserAccountDto.getMap().get("id_token");
        ResponseUserCreateDto userCreateDto = userService.addUserGoogle(idToken);
        return new ResponseEntity<>(userCreateDto, HttpStatus.CREATED);
    }

    @GetMapping("/hola")
    public ResponseEntity<String> hola() {
        return new ResponseEntity<>("hola", HttpStatus.OK);
    }

    @GetMapping("/emailDuplicate")
    public ResponseEntity<Boolean> emailDuplicate(@RequestParam("email") String email) {
        return new ResponseEntity<>(userService.existsUserByEmail(email), HttpStatus.IM_USED);
    }
}
