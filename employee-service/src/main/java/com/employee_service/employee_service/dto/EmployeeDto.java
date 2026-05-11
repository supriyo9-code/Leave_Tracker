package com.employee_service.employee_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @NotBlank(message = "Employee name can not be blank")
    private String name;
    @NotBlank(message = "Employee email can not be blank")
    @Email(message = "Invalid email. Try again....")
    private String email;
    private String phone;

    @NotNull
    private Long managerId;   // self reference (manager)
    @NotBlank(message = "Department name can not be blank")
    private String department;

    @NotBlank(message = "Role name can not be blank")
    private String role;
}
