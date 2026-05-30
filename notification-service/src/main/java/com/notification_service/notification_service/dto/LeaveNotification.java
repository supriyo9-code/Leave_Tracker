package com.notification_service.notification_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveNotification {

    private Long id;

    private Long employeeId;

    private LocalDate fromDate;
    private LocalDate toDate;

    private String leaveType;   // SICK / CASUAL / PAID
    private String status;

    private Long approvedBy;    // managerId

    private String reason;
}
