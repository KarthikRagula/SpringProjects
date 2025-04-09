package org.example.service;

import org.example.entity.UserEntity;

public interface UserService {

    void registerUser(UserEntity user, boolean makeAdmin);

    UserEntity getUserByEmail(String email);
}
