package com.splitwise.app.splitwise.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ExpenseDto {

    private String name;

    private BigDecimal price;

    private long addedBy;

    private long paidBy;

    private long groupId;

    private Set<UserExpenseDto> users;
}
