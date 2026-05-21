package com.auth.authentication.service;

import com.auth.authentication.dto.LoginDto;
import com.auth.authentication.dto.UserDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    public UserDto signupUser(UserDto userDto);
//    public String addValue();
    public String login(LoginDto loginDto);
}
