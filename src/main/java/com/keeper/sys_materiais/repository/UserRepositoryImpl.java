package com.keeper.sys_materiais.repository;

import com.keeper.sys_materiais.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private List<User> users = new ArrayList<>();

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User save(User user) {
        // Verificar se é um update ou insert
        Optional<User> existente = findByUsername(user.getUsername());
        if (existente.isPresent()) {
            // Atualizar usuário existente
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(user.getUsername())) {
                    users.set(i, user);
                    break;
                }
            }
        } else {
            // Adicionar novo usuário
            users.add(user);
        }
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
