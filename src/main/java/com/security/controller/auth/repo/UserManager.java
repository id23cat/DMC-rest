package com.security.controller.auth.repo;

import com.security.controller.auth.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserManager extends CrudRepository<UserModel, Integer> {
    UserModel findByUsername(String username);
}