package com.employee_service.employee_service.service;


import com.employee_service.employee_service.dto.EmployeeDto;
import com.employee_service.employee_service.entity.Employee;

public interface EmployeeService {
    public EmployeeDto getEmployeeById(Long id);
    public void addEmployee(EmployeeDto employeeDto);
    public EmployeeDto updateEmployee(EmployeeDto employeeDto,Long id);
    public void deleteEmployee(Long id);
}
