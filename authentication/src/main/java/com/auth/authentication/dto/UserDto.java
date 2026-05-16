package com.auth.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private Long phone;
    private String department;
}
