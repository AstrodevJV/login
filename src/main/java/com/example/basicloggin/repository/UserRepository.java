package com.example.basicloggin.repository;

import com.example.basicloggin.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    boolean existsByEmail(String email);

    UserModel findByEmail(String email);
}
