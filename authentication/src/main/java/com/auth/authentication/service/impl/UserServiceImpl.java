package com.auth.authentication.service.impl;

import com.auth.authentication.dto.UserDto;
import com.auth.authentication.entity.UserEntity;
import com.auth.authentication.repository.UserRepository;
import com.auth.authentication.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto signupUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setRole(userDto.getRole());
        userEntity.setDepartment(userDto.getDepartment());
        userRepository.save(userEntity);
        return userDto ;
    }
}
