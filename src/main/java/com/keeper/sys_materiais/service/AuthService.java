package com.keeper.sys_materiais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.keeper.sys_materiais.model.User;
import com.keeper.sys_materiais.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    @Qualifier("userRepositoryImpl")
    private UserRepository userRepo;

    public User login(String username, String password) {
        return userRepo.findByUsername(username)
                .filter(user -> password.equals(user.getPassword())) // Simplificado sem BCrypt por enquanto
                .orElse(null);
    }

    public User register(String username, String password, String role) {
        User user = new User(username, password, role); // Senha em texto simples por enquanto
        return userRepo.save(user);
    }
}

