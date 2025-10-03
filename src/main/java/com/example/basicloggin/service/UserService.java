package com.example.basicloggin.service;

import com.example.basicloggin.dto.ResponseUserCreateDto;
import com.example.basicloggin.dto.ResponseUsererLogin;
import com.example.basicloggin.dto.UserCreateDto;
import com.example.basicloggin.exception.CorreoDuplicadoException;
import com.example.basicloggin.model.UserModel;
import com.example.basicloggin.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.val;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtDecoder jwtDecoder;

    private final GoogleService googleService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtDecoder jwtDecoder,GoogleService googleService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtDecoder = jwtDecoder;
        this.googleService = googleService;
    }

    @Transactional
    public ResponseUserCreateDto addUser(UserCreateDto userCreateDto) {

        if (existsUserByEmail(userCreateDto.getEmail())) {
            throw new CorreoDuplicadoException("El email ya existe");
        }

        val user  = new UserModel();

        user.setEmail(userCreateDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userCreateDto.getPassword()));
        user.setName(userCreateDto.getName());
        user.setAge(userCreateDto.getEdad());
        user.setGender(userCreateDto.getGender());
        user.setUserRol(userCreateDto.getUserRol());

        val savedUser = userRepository.save(user);

        return new ResponseUserCreateDto(
                savedUser.getEmail(),
                savedUser.getName(),
                savedUser.getAge(),
                savedUser.getGender(),
                savedUser.getUserRol()
        );
    }

    public ResponseUsererLogin ingresarUser(String email, String password) {
        UserModel userModel = userRepository.findByEmail(email);
        String jwt = "por implementar";
        if(userModel != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            if(bCryptPasswordEncoder.matches(password,userModel.getPassword())) {

                return new ResponseUsererLogin(
                        userModel.getEmail(),
                        jwt
                );
            }
        }
        return null;
    }

    public ResponseUserCreateDto addUserGoogle(String idToken) {
        Jwt jwt = jwtDecoder.decode(idToken);
        val user = new UserModel();
        user.setEmail(jwt.getClaims().get("email").toString());
        user.setName(jwt.getClaims().get("name").toString());
        user.setEmail(jwt.getClaims().get("email").toString());
        user.setGender(googleService.getGender(idToken));
        user.setAge(googleService.getAge(idToken));
        UserModel savedUser = userRepository.save(user);

        return new ResponseUserCreateDto(
                savedUser.getEmail(),
                savedUser.getName(),
                savedUser.getAge(),
                savedUser.getGender(),
                savedUser.getUserRol()
        );
    }

    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
