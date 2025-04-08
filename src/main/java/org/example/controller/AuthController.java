package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.entity.UserEntity;
import org.example.response.ResponseMessage;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>(new ResponseMessage("User already exists, Please Login"), HttpStatus.OK);
        }
        userService.registerUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}