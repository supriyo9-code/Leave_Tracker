package com.employee_service.employee_service.service;

import com.employee_service.employee_service.dto.EmployeeDto;
import com.employee_service.employee_service.entity.Employee;
import com.employee_service.employee_service.payload.LeavePayLoad;
import com.employee_service.employee_service.repository.EmployeeRepository;
import com.employee_service.employee_service.response.EmployeeToLeaveBalance;
import com.leave_balance.leave_balance.dto.LeaveBalanceDto;
import com.leave_service.leave_service.dto.LeaveDto;
import com.leave_service.leave_service.entity.Leave;
import com.leave_service.leave_service.repository.LeaveRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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


    @CircuitBreaker(name = "getEmployee",fallbackMethod = "fallbackGetEmployee")
    @Cacheable(value = "employeeToLeaveBalance",key = "#id")
    @Retry(name = "getEmployee")
    @Override
    public EmployeeToLeaveBalance getEmployeeById(Long id) {
        log.trace("Inside getEmployeeById{}");
        System.out.println("From DB");
//        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException());

//        try
//        {
            LeaveBalanceDto leaveBalanceDto = restTemplate.getForEntity("http://localhost:8082/api/v1/leave-balance/get/{employeeId}",LeaveBalanceDto.class,employee.getId()).getBody();
            EmployeeToLeaveBalance employeeToLeaveBalance = new EmployeeToLeaveBalance();
            employeeToLeaveBalance.setLeaveBalanceDto(leaveBalanceDto);
            employeeToLeaveBalance.setName(employee.getName());
            employeeToLeaveBalance.setEmail(employee.getEmail());
            employeeToLeaveBalance.setPhone(employee.getPhone());
            employeeToLeaveBalance.setManagerId(employee.getManagerId());
            employeeToLeaveBalance.setDepartment(employee.getDepartment());
            employeeToLeaveBalance.setRole(employee.getRole());
            return employeeToLeaveBalance;
//        }
//        catch (Exception ex)
//        {
//            throw new RuntimeException(ex);
//        }


    }
    public EmployeeToLeaveBalance fallbackGetEmployee(Long id,Exception ex) {
        log.error(ex.getMessage());
        return new EmployeeToLeaveBalance();
    }


    @Override
    @Transactional
    public EmployeeToLeaveBalance addEmployee(EmployeeDto employeeDto) {
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

            LeaveBalanceDto leaveBalanceDto = restTemplate.postForEntity("http://localhost:8082/api/v1/leave-balance/add/{id}",null, LeaveBalanceDto.class,employee.getId()).getBody();

            EmployeeToLeaveBalance employeeToLeaveBalance = new EmployeeToLeaveBalance();
            employeeToLeaveBalance.setLeaveBalanceDto(leaveBalanceDto);
            employeeToLeaveBalance.setName(employeeDto.getName());
            employeeToLeaveBalance.setEmail(employeeDto.getEmail());
            employeeToLeaveBalance.setPhone(employeeDto.getPhone());
            employeeToLeaveBalance.setManagerId(employeeDto.getManagerId());
            employeeToLeaveBalance.setRole(employeeDto.getRole());
            employeeToLeaveBalance.setDepartment(employeeDto.getDepartment());



            log.info("addEmployee:{}",employee);
            return employeeToLeaveBalance;
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

    @Transactional
    @Override
    public void deleteEmployee(Long id) {
        log.trace("Inside deleteEmployee{}");
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException());
        employeeRepository.delete(employee);
        restTemplate.delete("http://localhost:8082/api/v1/leave-balance/remove/{employeeId}",employee.getId());
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
