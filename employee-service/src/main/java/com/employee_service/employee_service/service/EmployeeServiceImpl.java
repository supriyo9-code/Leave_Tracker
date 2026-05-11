package com.employee_service.employee_service.service;

import com.employee_service.employee_service.dto.EmployeeDto;
import com.employee_service.employee_service.entity.Employee;
import com.employee_service.employee_service.payload.LeavePayLoad;
import com.employee_service.employee_service.repository.EmployeeRepository;
import com.leave_service.leave_service.dto.LeaveDto;
import com.leave_service.leave_service.entity.Leave;
import com.leave_service.leave_service.repository.LeaveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private RestTemplate restTemplate;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RestTemplate restTemplate) {
        this.employeeRepository = employeeRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        log.trace("Inside getEmployeeById{}");
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException());
        employeeDto.setName(employee.getName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setManagerId(employee.getManagerId());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setRole(employee.getRole());
        log.info("getEmployeeById:{}",employeeDto);
        return employeeDto;
    }

    @Override
    public void addEmployee(EmployeeDto employeeDto) {
        log.trace("Inside addEmployee{}");
        Employee employee1 = employeeRepository.findByEmail(employeeDto.getEmail());

        if (employee1!=null) {
            log.error("Employee already exists with email:{}",employeeDto.getEmail());
            throw new RuntimeException();
        }
        else
        {
            Employee employee = new Employee();
            employee.setName(employeeDto.getName());
            employee.setEmail(employeeDto.getEmail());
            employee.setPhone(employeeDto.getPhone());
            employee.setManagerId(employeeDto.getManagerId());
            employee.setRole(employeeDto.getRole());
            employee.setDepartment(employeeDto.getDepartment());
            employeeRepository.save(employee);
            log.info("addEmployee:{}",employee);
        }

    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        log.trace("Inside updateEmployee{}");
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException());
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        employee.setRole(employeeDto.getRole());
        employee.setManagerId(employeeDto.getManagerId());
        employee.setDepartment(employeeDto.getDepartment());
        employeeRepository.save(employee);
        log.info("updateEmployee:{}",employee);
        return employeeDto;
    }

    @Override
    public void deleteEmployee(Long id) {
        log.trace("Inside deleteEmployee{}");
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException());
        employeeRepository.delete(employee);
        log.info("deleteEmployee:{}",employee);
    }

    @Override
    public Leave applyLeave(Long id, LeavePayLoad leavePayLoad) {
        log.trace("Inside applyLeave{}");
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException());
        LeaveDto leaveDto = new LeaveDto();
        leaveDto.setEmployeeId(employee.getId());
        leaveDto.setLeaveType(leavePayLoad.getLeaveType());
        leaveDto.setReason(leavePayLoad.getReason());
        leaveDto.setApprovedBy(employee.getManagerId());
        leaveDto.setToDate(leavePayLoad.getToDate());
        leaveDto.setFromDate(leavePayLoad.getFromDate());

        Leave leave= restTemplate.postForEntity("http://localhost:8081/api/v1/leave/apply", leaveDto, Leave.class).getBody();
        log.info("applyLeave:{}",leaveDto);
        return leave;
    }
}
