package com.example.bookshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshop.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    

    //メールアドレスでユーザーを検索
    Optional<User> findByEmail(String email);
}
