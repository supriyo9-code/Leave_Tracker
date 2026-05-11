package com.leave_service.leave_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "leave_records")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private LocalDate fromDate;
    private LocalDate toDate;

    private String leaveType;   // SICK / CASUAL / PAID
    private String status = "Pending";      // PENDING / APPROVED / REJECTED

    private Long approvedBy;    // managerId

    private String reason;
}
