package com.auth.authentication.controller;

import com.auth.authentication.dto.LoginDto;
import com.auth.authentication.dto.UserDto;
import com.auth.authentication.jwt.JwtService;
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
    private JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto) {
        log.info("Signup Request");
        UserDto userDto1 = userService.signupUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }
    @GetMapping("/ip")
    public ResponseEntity<String> ip(@RequestHeader("Authorization") String authHeader) {
        log.info("Inside ip method");
//        String s = userService.addValue();
        String token = authHeader.substring(7);
         String name = jwtService.getClaimsFromToken(token).getSubject();

        return new ResponseEntity<>(name+"  Love my employee", HttpStatus.OK);
    }
    @GetMapping("/up")
    public ResponseEntity<String> up(@RequestHeader("Authorization") String authHeader) {
        log.info("Inside up method");
        String token = authHeader.substring(7);
        String name = jwtService.getClaimsFromToken(token).getSubject();
        return new ResponseEntity<>(name+" Love my manager", HttpStatus.OK);
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
