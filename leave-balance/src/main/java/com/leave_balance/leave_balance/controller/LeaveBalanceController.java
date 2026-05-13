package com.leave_balance.leave_balance.controller;

import com.leave_balance.leave_balance.dto.LeaveBalanceDto;
import com.leave_balance.leave_balance.service.LeaveBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/leave-balance")
public class LeaveBalanceController {
    private LeaveBalanceService leaveBalanceService;

    public LeaveBalanceController(LeaveBalanceService leaveBalanceService) {
        this.leaveBalanceService = leaveBalanceService;
    }

    @PostMapping("/add/{empId}")
    public ResponseEntity<LeaveBalanceDto> addLeaveBalance(@PathVariable Long empId) {

        LeaveBalanceDto leaveBalanceDto =leaveBalanceService.addLeaveBalance(empId);
        return  new ResponseEntity<>(leaveBalanceDto, HttpStatus.OK);
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<LeaveBalanceDto> getLeaveBalanceByEmployeeId(@PathVariable Long employeeId) {
       LeaveBalanceDto leaveBalanceDto = leaveBalanceService.getLeaveBalanceByEmployeeId(employeeId);
        return new ResponseEntity<>(leaveBalanceDto,HttpStatus.FOUND);

    }
    @PutMapping("/update")
    public ResponseEntity<LeaveBalanceDto> updateLeaveBalance( @RequestBody LeaveBalanceDto leaveBalanceDto) {
        LeaveBalanceDto leaveBalanceDto1 =leaveBalanceService.updateLeaveBalance(leaveBalanceDto);
        return  new ResponseEntity<>(leaveBalanceDto1,HttpStatus.OK);
    }

    @DeleteMapping("/remove/{empId}")
    public ResponseEntity<?> removeLeaveBalance(@PathVariable Long empId) {
       leaveBalanceService.deleteLeaveBalance(empId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
