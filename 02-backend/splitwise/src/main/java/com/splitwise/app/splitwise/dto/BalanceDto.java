package com.splitwise.app.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {
    private Long payerId;
    private Long payeeId;
    private String payerName;
    private String payeeName;
    private BigDecimal amount;
}
