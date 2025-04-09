package org.example.serviceImpl;

import jakarta.transaction.Transactional;
import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserEntity user, boolean makeAdmin) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveUser(user);
        if(makeAdmin){
            userRepository.assignRole(user.getEmail(), "ROLE_ADMIN");
        }
        else {
            userRepository.assignRole(user.getEmail(), "ROLE_USER");
        }
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
