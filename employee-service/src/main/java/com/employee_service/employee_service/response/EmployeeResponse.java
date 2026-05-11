package com.employee_service.employee_service.response;

import com.leave_service.leave_service.entity.Leave;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private String name;
    private String email;
    private String phone;
    private Long managerId;
    private String department;
    private String role;
    private Leave leave;
}
