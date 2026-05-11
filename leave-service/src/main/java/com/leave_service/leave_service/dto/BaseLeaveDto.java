package com.leave_service.leave_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseLeaveDto {
    private Long employeeId;

    private LocalDate fromDate;
    private LocalDate toDate;

    private String leaveType;   // SICK / CASUAL / PAID
    private String status;      // PENDING / APPROVED / REJECTED

    private Long approvedBy;    // managerId

    private String reason;
}
