package com.auth.authentication.service.impl;

import com.auth.authentication.dto.LoginDto;
import com.auth.authentication.dto.UserDto;
import com.auth.authentication.entity.UserEntity;
import com.auth.authentication.jwt.JwtService;
import com.auth.authentication.repository.UserRepository;
import com.auth.authentication.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl  implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;


//    @Value(value = "${protyay.val}")
////    private String test;

    @Override
    public UserDto signupUser(UserDto userDto) {
        log.info("Signup Request: inside service");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setRole(userDto.getRole());
        userEntity.setDepartment(userDto.getDepartment());

        userRepository.save(userEntity);
        log.info("Signup Response: saved in database");
        return userDto ;
    }

//    @Override
//    public String addValue() {
//        log.info("Inside addValue method");
//
//
//        return test;
//    }

    @Override
    public String login(LoginDto loginDto) {
        UserEntity userEntity = userRepository.findByEmail(loginDto.getEmail());
        String token = jwtService.generateToken(userEntity);
        return token;
    }
}
