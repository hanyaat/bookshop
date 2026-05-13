package com.example.bookshop.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bookshop.model.User;
import com.example.bookshop.repository.UserRepository;

@Service
public class UserService {
     
    private final UserRepository repo;

    public UserService(UserRepository repo){
        this.repo=repo;
    }

    public User save(User user){
        return repo.save(user);
    }

    public Optional<User> findById(Long id){
        return repo.findById(id);
    }

    public Optional<User> findByEmail(String email){
        return repo.findByEmail(email);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
