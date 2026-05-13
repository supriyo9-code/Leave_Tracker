package com.employee_service.employee_service.controller;

import com.employee_service.employee_service.dto.EmployeeDto;
import com.employee_service.employee_service.payload.LeavePayLoad;
import com.employee_service.employee_service.response.EmployeeResponse;
import com.employee_service.employee_service.response.EmployeeToLeaveBalance;
import com.employee_service.employee_service.service.EmployeeService;
import com.leave_service.leave_service.entity.Leave;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/employee-leave")
public class EmployeeToLeaveController {
    private EmployeeService employeeService;

    public EmployeeToLeaveController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/apply/leave/{id}")
    public ResponseEntity<EmployeeResponse> applyLeave(@Valid @RequestBody LeavePayLoad leavePayLoad, @PathVariable Long id) {

        log.trace("Enter applyLeave");
        EmployeeResponse employeeResponse = new EmployeeResponse();
        EmployeeToLeaveBalance dto = employeeService.getEmployeeById(id);
        employeeResponse.setName(dto.getName());
        employeeResponse.setDepartment(dto.getDepartment());
        employeeResponse.setManagerId(dto.getManagerId());
        employeeResponse.setPhone(dto.getPhone());
        employeeResponse.setRole(dto.getRole());
        employeeResponse.setEmail(dto.getEmail());

        Leave leave = employeeService.applyLeave(id, leavePayLoad);
        log.info("applyLeave:{}", leave);
        employeeResponse.setLeave(leave);


        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }
}
