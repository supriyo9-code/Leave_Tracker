package com.leave_balance.leave_balance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveBalanceDto {
    private Long employeeId;
    private Double casual;
    private Double paid;
    private Double sick;
}
