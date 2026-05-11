package com.leave_service.leave_service.repository;

import com.leave_service.leave_service.entity.Leave;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {
    public Leave findByIdAndStatus(Long id, String status);

    List<Leave> findByEmployeeId(Long employeeId);

    List<Leave> findByApprovedBy(Long managerId);

    Leave findByEmployeeIdAndFromDate(@NotNull Long employeeId, @NotNull LocalDate fromDate);
}
