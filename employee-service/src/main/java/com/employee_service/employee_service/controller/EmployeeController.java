package com.employee_service.employee_service.controller;

import com.employee_service.employee_service.dto.EmployeeDto;
import com.employee_service.employee_service.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    //Get employee by id
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id){
        log.trace("Inside getEmployeeController: id:{}",id);
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);

        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        log.trace("Inside addEmployeeController: employeeDto:{}",employeeDto);
        employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto, @PathVariable Long id){
        log.trace("Inside updateEmployeeController: employeeDto:{}",employeeDto);
        EmployeeDto employeeDto1 = employeeService.updateEmployee(employeeDto,id);
        return new ResponseEntity<>(employeeDto1, HttpStatus.OK);
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<EmployeeDto> removeEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
