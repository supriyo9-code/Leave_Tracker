package com.leave_balance.leave_balance.service.Impl;

import com.leave_balance.leave_balance.dto.LeaveBalanceDto;
import com.leave_balance.leave_balance.entity.LeaveBalance;
import com.leave_balance.leave_balance.repository.LeaveBalanceRepository;
import com.leave_balance.leave_balance.service.LeaveBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LeaveBalanceServiceImpl implements LeaveBalanceService {
    private LeaveBalanceRepository leaveBalanceRepository;

    public LeaveBalanceServiceImpl(LeaveBalanceRepository leaveBalanceRepository) {
        this.leaveBalanceRepository = leaveBalanceRepository;
    }

    @Override
    public LeaveBalanceDto getLeaveBalanceByEmployeeId(Long empId) {
        LeaveBalance leaveBalance = leaveBalanceRepository.getLeaveBalanceByEmployeeId(empId).orElseThrow(()->new RuntimeException());
        LeaveBalanceDto leaveBalanceDto = new LeaveBalanceDto();
        leaveBalanceDto.setEmployeeId(leaveBalance.getEmployeeId());
        leaveBalanceDto.setCasual(leaveBalance.getCasual());
        leaveBalanceDto.setPaid(leaveBalance.getPaid());
        leaveBalanceDto.setSick(leaveBalance.getSick());
        return leaveBalanceDto;
    }

    @Override
    public LeaveBalanceDto addLeaveBalance(Long empId) {
        Optional<LeaveBalance> leaveBalance1= leaveBalanceRepository.getLeaveBalanceByEmployeeId(empId);
        if(!leaveBalance1.isPresent()){
            LeaveBalance leaveBalance = new LeaveBalance();
            leaveBalance.setEmployeeId(empId);
            leaveBalanceRepository.save(leaveBalance);
            LeaveBalanceDto leaveBalanceDto = new LeaveBalanceDto();
            leaveBalanceDto.setEmployeeId(empId);
            leaveBalanceDto.setCasual(leaveBalance.getCasual());
            leaveBalanceDto.setPaid(leaveBalance.getPaid());
            leaveBalanceDto.setSick(leaveBalance.getSick());
            return leaveBalanceDto;
        }
        else{
            log.error("Employee already exists with id:{}", empId);
            throw new RuntimeException("Employee already exists with id");
        }

    }
}
