package com.leave_service.leave_service.repository;

import com.leave_service.leave_service.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {
    public Leave findByIdAndStatus(Long id, String status);

}
