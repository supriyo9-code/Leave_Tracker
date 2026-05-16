package com.auth.authentication.controller;

import com.auth.authentication.dto.UserDto;
import com.auth.authentication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class MasterController {
    private UserService userService;

    public MasterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto) {
       UserDto userDto1 = userService.signupUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }
    @GetMapping("/ip")
    public ResponseEntity<String> ip() {
        return new ResponseEntity<>("I Love Bihari", HttpStatus.OK);
    }

}
