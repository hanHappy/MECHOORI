package com.mechoori.web.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mechoori.web.api.entity.Users;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmailAndPassword(String email, String password);
    Optional<Users> findByEmail(String email);
    boolean existsUsersByEmail(String email);
}