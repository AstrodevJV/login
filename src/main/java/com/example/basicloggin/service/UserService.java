package com.example.basicloggin.service;

import com.example.basicloggin.dto.ResponseUserCreateDto;
import com.example.basicloggin.dto.UserCreateDto;
import com.example.basicloggin.exception.CorreoDuplicadoException;
import com.example.basicloggin.model.UserModel;
import com.example.basicloggin.repository.UserRepository;
import lombok.val;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseUserCreateDto addUser(UserCreateDto userCreateDto) {

        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            throw new CorreoDuplicadoException("El email ya existe");
        }

        val user  = new UserModel();

        user.setEmail(userCreateDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userCreateDto.getPassword()));
        user.setName(userCreateDto.getName());
        user.setAge(user.getAge());
        user.setUserRol(userCreateDto.getUserRol());

        val savedUser = userRepository.save(user);

        return new ResponseUserCreateDto(
                savedUser.getEmail(),
                savedUser.getName(),
                savedUser.getAge(),
                savedUser.getUserRol()
        );
    }
}
