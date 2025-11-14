package com.keeper.sys_materiais.repository;

import com.keeper.sys_materiais.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    List<User> findAll();
    User save(User user);
    Optional<User> findByUsername(String username);
}
