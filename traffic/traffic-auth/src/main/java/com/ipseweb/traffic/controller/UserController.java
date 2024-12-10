package com.ipseweb.traffic.controller;

import com.ipseweb.traffic.dto.JoinUserRequest;
import com.ipseweb.traffic.dto.LoginRequest;
import com.ipseweb.traffic.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @PostMapping("/join")
    public ResponseEntity joinUser(@RequestBody JoinUserRequest joinUserRequest) {
        return userService.joinUser(joinUserRequest);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
