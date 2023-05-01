package com.splitwise.app.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class ExpenseDetailDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private LocalDateTime dateCreated;

    private String addedBy;

    private String paidBy;

    private long groupId;

    private Set<UserExpenseDto> users;
}
