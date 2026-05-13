package com.employee_service.employee_service.service;


import com.employee_service.employee_service.dto.EmployeeDto;
import com.employee_service.employee_service.entity.Employee;
import com.employee_service.employee_service.payload.LeavePayLoad;
import com.employee_service.employee_service.response.EmployeeToLeaveBalance;
import com.leave_service.leave_service.entity.Leave;

public interface EmployeeService {
    public EmployeeToLeaveBalance getEmployeeById(Long id);
    public EmployeeToLeaveBalance addEmployee(EmployeeDto employeeDto);
    public EmployeeDto updateEmployee(EmployeeDto employeeDto,Long id);

    public void deleteEmployee(Long id);
    public Leave applyLeave(Long id, LeavePayLoad leavePayLoad);
}
