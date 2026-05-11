package com.leave_service.leave_service.service;

import com.leave_service.leave_service.dto.LeaveDto;
import com.leave_service.leave_service.dto.LeaveStatusDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface LeaveService {
    public void applyLeave(@Valid LeaveDto leaveDto);
    public LeaveStatusDto changeStatus(@Valid LeaveStatusDto leaveStatusDto, long id);

}
