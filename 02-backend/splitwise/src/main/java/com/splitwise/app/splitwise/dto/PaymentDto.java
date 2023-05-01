package com.splitwise.app.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long payerId;
    private Long payeeId;
    private Long groupId;
    private String payerName;
    private String payeeName;
    private BigDecimal amount;
    private String type;
    private LocalDateTime dateCreated;
}
