package com.employee_service.employee_service.service;

import com.employee_service.employee_service.dto.EmployeeDto;
import com.employee_service.employee_service.entity.Employee;
import com.employee_service.employee_service.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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
}
