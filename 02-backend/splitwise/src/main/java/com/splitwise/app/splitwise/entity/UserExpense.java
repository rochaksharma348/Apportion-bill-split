package com.splitwise.app.splitwise.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "user_expense")
public class UserExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @Column(name = "expense_amount")
    private BigDecimal expenseAmount;

    @Column(name = "status")
    private String status;


    public UserExpense(User user, Expense expense) {
        this.user = user;
        this.expense = expense;
    }

    public UserExpense(User user, Expense expense, BigDecimal expenseAmount) {
        this.user = user;
        this.expense = expense;
        this.expenseAmount = expenseAmount;
        this.status = "unsettled";
    }

    public UserExpense() {

    }
}
