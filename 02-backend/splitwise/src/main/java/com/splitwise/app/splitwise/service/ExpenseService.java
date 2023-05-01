package com.splitwise.app.splitwise.service;

import com.splitwise.app.splitwise.dto.ExpenseDetailDto;
import com.splitwise.app.splitwise.dto.ExpenseDto;
import com.splitwise.app.splitwise.entity.Expense;

import java.util.List;

public interface ExpenseService {
    public Expense CreateExpense(ExpenseDto expenseDto);
    public List<ExpenseDetailDto> getGroupExpenses(long groupId);
    public ExpenseDetailDto getExpenseById(Long expenseId);
    public void deleteById(Long expenseId);
}
