package com.leave_service.leave_service.service;

import com.leave_service.leave_service.dto.BaseLeaveDto;
import com.leave_service.leave_service.dto.LeaveDto;
import com.leave_service.leave_service.dto.LeaveStatusDto;
import com.leave_service.leave_service.entity.Leave;
import com.leave_service.leave_service.repository.LeaveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class LeaveServiceImpl implements LeaveService {
    private LeaveRepository leaveRepository;

    public LeaveServiceImpl(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    @Override
    public Leave applyLeave(LeaveDto leaveDto) {

        log.trace("Enter applyLeave");
        Leave l=leaveRepository.findByEmployeeIdAndFromDate(leaveDto.getEmployeeId(),leaveDto.getFromDate());
        log.warn("l: {}",l);

        if(Objects.isNull(l)){
            Leave leave = new Leave();
            leave.setEmployeeId(leaveDto.getEmployeeId());
            leave.setLeaveType(leaveDto.getLeaveType());
            leave.setReason(leaveDto.getReason());
            leave.setApprovedBy(leaveDto.getApprovedBy());
            leave.setFromDate(leaveDto.getFromDate());
            leave.setToDate(leaveDto.getToDate());
//        leave.setStatus(leaveDto.getStatus());
            leaveRepository.save(leave);
            return leave;
        }else{
            log.error("Leave application already exists with employeeId: {} & date: {}",leaveDto.getEmployeeId(),leaveDto.getFromDate());
            throw new RuntimeException("Leave application already exists");
        }


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

    @Override
    public BaseLeaveDto getLeave(Long id) {
        Leave leave=leaveRepository.findById(id).orElseThrow(() -> new RuntimeException("Leave application not found with id:"+id));
        BaseLeaveDto baseLeaveDto=new BaseLeaveDto();
        baseLeaveDto.setLeaveType(leave.getLeaveType());
        baseLeaveDto.setReason(leave.getReason());
        baseLeaveDto.setApprovedBy(leave.getApprovedBy());
        baseLeaveDto.setFromDate(leave.getFromDate());
        baseLeaveDto.setToDate(leave.getToDate());
        baseLeaveDto.setStatus(leave.getStatus());
        baseLeaveDto.setEmployeeId(leave.getEmployeeId());


        return baseLeaveDto;
    }

    @Override
    public List<BaseLeaveDto> getLeaveManager(Long managerId) {
        List<Leave> l=leaveRepository.findByApprovedBy(managerId);

        List<BaseLeaveDto> baseLeaveDtos=l.stream().map(e->{
            BaseLeaveDto baseLeaveDto=new BaseLeaveDto();
            baseLeaveDto.setLeaveType(e.getLeaveType());
            baseLeaveDto.setReason(e.getReason());
            baseLeaveDto.setApprovedBy(e.getApprovedBy());
            baseLeaveDto.setFromDate(e.getFromDate());
            baseLeaveDto.setToDate(e.getToDate());
            baseLeaveDto.setStatus(e.getStatus());
            baseLeaveDto.setEmployeeId(e.getEmployeeId());
            return baseLeaveDto;
        }).toList();
        return baseLeaveDtos;
    }

    @Override
    public List<BaseLeaveDto> getLeaveEmployee(Long employeeId) {
        List<Leave> l=leaveRepository.findByEmployeeId(employeeId);
        List<BaseLeaveDto> base =l.stream().map(e->{
            BaseLeaveDto baseLeaveDto=new BaseLeaveDto();
            baseLeaveDto.setLeaveType(e.getLeaveType());
            baseLeaveDto.setReason(e.getReason());
            baseLeaveDto.setApprovedBy(e.getApprovedBy());
            baseLeaveDto.setFromDate(e.getFromDate());
            baseLeaveDto.setToDate(e.getToDate());
            baseLeaveDto.setStatus(e.getStatus());
            baseLeaveDto.setEmployeeId(e.getEmployeeId());
            return baseLeaveDto;
        }).toList();
        return base;
    }
}
