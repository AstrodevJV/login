package com.example.basicloggin.model;


import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity(name = "usuarios")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nombre")
    private String name;

    @Column(name = "edad")
    private Integer age;

    @Column(name = "genero")
    private String gender;

    @Enumerated(EnumType.STRING)
    private UserRol userRol;
}
