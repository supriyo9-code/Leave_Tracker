package com.leave_service.leave_service.service;

import com.leave_service.leave_service.dto.LeaveDto;
import com.leave_service.leave_service.dto.LeaveStatusDto;
import com.leave_service.leave_service.entity.Leave;
import com.leave_service.leave_service.repository.LeaveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class LeaveServiceImpl implements LeaveService {
    private LeaveRepository leaveRepository;

    public LeaveServiceImpl(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    @Override
    public void applyLeave(LeaveDto leaveDto) {
        Leave leave = new Leave();
        leave.setEmployeeId(leaveDto.getEmployeeId());
        leave.setLeaveType(leaveDto.getLeaveType());
        leave.setReason(leaveDto.getReason());
        leave.setApprovedBy(leaveDto.getApprovedBy());
        leave.setFromDate(leaveDto.getFromDate());
        leave.setToDate(leaveDto.getToDate());
//        leave.setStatus(leaveDto.getStatus());
        leaveRepository.save(leave);
    }



    @Override
    public LeaveStatusDto changeStatus(LeaveStatusDto leaveStatusDto, long id) {
        Leave leave = leaveRepository.findByIdAndStatus(id, "Pending");
        if (Objects.nonNull(leave)) {

            if (leaveStatusDto.getStatus()!= null) {
                leave.setStatus(leaveStatusDto.getStatus());
            }
            leaveRepository.save(leave);
        }
        else
        {
            log.error("Leave application not found with id:{}",id);
            throw new RuntimeException("Leave application not found with id:"+id);
        }


        return leaveStatusDto;
    }
}
