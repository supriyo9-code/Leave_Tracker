package com.leave_service.leave_service.service;

import com.leave_service.leave_service.dto.BaseLeaveDto;
import com.leave_service.leave_service.dto.LeaveDto;
import com.leave_service.leave_service.dto.LeaveStatusDto;
import com.leave_service.leave_service.entity.Leave;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveService {
    public Leave applyLeave(@Valid LeaveDto leaveDto);
    public LeaveStatusDto changeStatus(@Valid LeaveStatusDto leaveStatusDto, long id);
    public BaseLeaveDto getLeave(Long id);
    public List<BaseLeaveDto> getLeaveManager(Long managerId);
    public List<BaseLeaveDto> getLeaveEmployee(Long employeeId);

}
