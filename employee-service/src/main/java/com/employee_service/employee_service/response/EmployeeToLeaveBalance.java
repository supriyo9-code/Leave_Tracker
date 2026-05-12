package com.employee_service.employee_service.response;

import com.leave_balance.leave_balance.dto.LeaveBalanceDto;
import com.leave_balance.leave_balance.entity.LeaveBalance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeToLeaveBalance {
    private String name;
    private String email;
    private String phone;
    private Long managerId;
    private String department;
    private String role;
    private LeaveBalanceDto leaveBalanceDto;
}
