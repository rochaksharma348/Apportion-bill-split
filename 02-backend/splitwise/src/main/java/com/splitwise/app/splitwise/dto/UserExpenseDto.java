package com.splitwise.app.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UserExpenseDto {

    private Long id;

    private String userFullName;

    private BigDecimal amount;

}
