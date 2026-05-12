package com.leave_balance.leave_balance.repository;

import com.leave_balance.leave_balance.entity.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {

    public Optional<LeaveBalance> getLeaveBalanceByEmployeeId(Long empId);


}
