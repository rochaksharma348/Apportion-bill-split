package com.splitwise.app.splitwise.controller;

import com.splitwise.app.splitwise.dto.ExpenseDetailDto;
import com.splitwise.app.splitwise.dto.ExpenseDto;
import com.splitwise.app.splitwise.entity.Expense;
import com.splitwise.app.splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/create-expense")
    private void createExpense(@RequestBody ExpenseDto expenseDto) {
        Expense expense = expenseService.CreateExpense(expenseDto);
    }

    @GetMapping("/group-expenses/{id}")
    public List<ExpenseDetailDto> getGroupExpenses(@PathVariable("id") Long groupId) {

        return this.expenseService.getGroupExpenses(groupId);
    }

    @GetMapping("/get-expense/{id}")
    public ExpenseDetailDto getExpenseById(@PathVariable("id") Long id) {
        return expenseService.getExpenseById(id);
    }

    @GetMapping("/delete-expenses/{id}")
    public void deleteExpenseById(@PathVariable("id") Long id) {
        this.expenseService.deleteById(id);
    }
}
