package com.leave_service.leave_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDto {
    @NotNull
    private Long employeeId;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fromDate;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate toDate;
    @NotBlank(message = "Please enter leave type")
    private String leaveType;   // SICK / CASUAL / PAID

//         // PENDING / APPROVED / REJECTED


    private Long approvedBy;    // managerId

    @NotBlank(message = "Please enter reason for leave")
    private String reason;
}
