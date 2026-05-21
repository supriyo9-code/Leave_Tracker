package com.auth.authentication.controller;

import com.auth.authentication.dto.LoginDto;
import com.auth.authentication.dto.UserDto;
import com.auth.authentication.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1")
public class MasterController {
    private UserService userService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto) {
        log.info("Signup Request");
        UserDto userDto1 = userService.signupUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }
    @GetMapping("/ip")
    public ResponseEntity<String> ip() {
        log.info("Inside ip method");
//        String s = userService.addValue();
        return new ResponseEntity<>(" I Love my employee", HttpStatus.OK);
    }
    @GetMapping("/up")
    public ResponseEntity<String> up() {
        log.info("Inside up method");
        return new ResponseEntity<>("I Love my manager", HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String token = userService.login(loginDto);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
