package com.sayhi.user.repository;

import java.util.Optional;

import com.sayhi.user.model.M_user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<M_user,Long> {
    Optional<M_user> findByEmail(String email);
}
