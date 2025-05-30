package org.example.controller;

import org.example.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/")
    public ResponseEntity<?> admin(){
        return new ResponseEntity<>(new ResponseMessage("in admin controller"), HttpStatus.OK);
    }
}