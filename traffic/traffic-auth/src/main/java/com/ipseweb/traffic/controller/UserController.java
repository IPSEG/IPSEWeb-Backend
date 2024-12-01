package com.ipseweb.traffic.controller;

import com.ipseweb.traffic.dto.JoinUserRequest;
import com.ipseweb.traffic.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
