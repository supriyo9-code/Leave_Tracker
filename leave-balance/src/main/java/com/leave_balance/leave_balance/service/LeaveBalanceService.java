package com.leave_balance.leave_balance.service;

import com.leave_balance.leave_balance.dto.LeaveBalanceDto;
import com.leave_balance.leave_balance.entity.LeaveBalance;
import org.springframework.web.bind.annotation.RequestBody;

public interface LeaveBalanceService {
    public LeaveBalanceDto getLeaveBalanceByEmployeeId(Long empId);
    public LeaveBalanceDto addLeaveBalance(Long empId);
    public LeaveBalanceDto updateLeaveBalance(LeaveBalanceDto leaveBalanceDto);
    public void deleteLeaveBalance(Long empId);
}
