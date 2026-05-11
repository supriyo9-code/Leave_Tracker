package com.employee_service.employee_service.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeavePayLoad {
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fromDate;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate toDate;
    @NotBlank(message = "Please enter leave type")
    private String leaveType;   // SICK / CASUAL / PAID
    @NotBlank(message = "Please enter reason for leave")
    private String reason;
}
