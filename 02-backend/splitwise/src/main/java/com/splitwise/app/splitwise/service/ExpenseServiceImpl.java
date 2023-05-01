package com.splitwise.app.splitwise.service;

import com.splitwise.app.splitwise.dao.ExpenseRepository;
import com.splitwise.app.splitwise.dao.GroupRepository;
import com.splitwise.app.splitwise.dao.NotificationRepository;
import com.splitwise.app.splitwise.dao.UserRepository;
import com.splitwise.app.splitwise.dto.ExpenseDetailDto;
import com.splitwise.app.splitwise.dto.ExpenseDto;
import com.splitwise.app.splitwise.dto.UserExpenseDto;
import com.splitwise.app.splitwise.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional
    public Expense CreateExpense(ExpenseDto expenseDto) {
        User addedBy = userRepository.getById(expenseDto.getAddedBy());
        User paidBy = userRepository.getById(expenseDto.getPaidBy());
        Group group = groupRepository.getById(expenseDto.getGroupId());

        Expense expense = new Expense(expenseDto.getName(), expenseDto.getPrice());

        group.addExpense(expense);
        addedBy.addAddedExpenses(expense);
        paidBy.addPaidExpenses(expense);

        for (UserExpenseDto userExpenseDto : expenseDto.getUsers()) {
            long id = userExpenseDto.getId();
            BigDecimal amount = userExpenseDto.getAmount();
            User tempUser = userRepository.getById(id);
            expense.addUser(tempUser, amount);

            String nfDesc = expense.getAddedBy().getFullName() + " Added an expense: \"" + expense.getName() + "\" to group: \""
                    + expense.getGroup().getName() + "\"";
            Notification nf = new Notification(nfDesc);

            tempUser.addNotification(nf);

//            notificationRepository.save(nf);
        }

        expenseRepository.save(expense);

        return expense;
    }

    @Override
    @Transactional
    public List<ExpenseDetailDto> getGroupExpenses(long groupId) {
        Group group = groupRepository.getById(groupId);
        List<ExpenseDetailDto> expenses = new ArrayList<>();

        for (Expense expense : group.getExpenses()) {
            ExpenseDetailDto edto = new ExpenseDetailDto(expense.getId(), expense.getName(), expense.getPrice(),
                    expense.getDateCreated(), expense.getAddedBy().getFullName(), expense.getPaidBy().getFullName(),
                    groupId, new HashSet<>());

            for (UserExpense ue : expense.getUserExpenses()) {
                edto.getUsers().add(new UserExpenseDto(ue.getId(), ue.getUser().getFullName(), ue.getExpenseAmount()));
            }

            expenses.add(edto);
        }

        expenses.sort((o1, o2) -> o2.getDateCreated().compareTo(o1.getDateCreated()));

        return expenses;
    }

    @Override
    @Transactional
    public ExpenseDetailDto getExpenseById(Long expenseId) {
        Expense expense =  expenseRepository.getById(expenseId);
        ExpenseDetailDto edto = new ExpenseDetailDto(expense.getId(), expense.getName(), expense.getPrice(),
                expense.getDateCreated(), expense.getAddedBy().getFullName(), expense.getPaidBy().getFullName(),
                expense.getGroup().getId(), new HashSet<>());

        for (UserExpense ue : expense.getUserExpenses()) {
            edto.getUsers().add(new UserExpenseDto(ue.getId(), ue.getUser().getFullName(), ue.getExpenseAmount()));
        }

        return edto;
    }

    @Override
    @Transactional
    public void deleteById(Long expenseId) {
        this.expenseRepository.deleteById(expenseId);
    }

}
