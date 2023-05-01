package com.splitwise.app.splitwise.service;

import com.splitwise.app.splitwise.dao.ExpenseRepository;
import com.splitwise.app.splitwise.dao.GroupRepository;
import com.splitwise.app.splitwise.dao.PaymentRepository;
import com.splitwise.app.splitwise.dao.UserRepository;
import com.splitwise.app.splitwise.dto.BalanceDto;
import com.splitwise.app.splitwise.dto.PaymentDto;
import com.splitwise.app.splitwise.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SettleUpServiceImpl implements SettleUpService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @Transactional
    public List<BalanceDto> getAllBalancesForUser(Long userId, Long groupId) {
        Group group = groupRepository.getById(groupId);
        User currentUser = userRepository.getById(userId);

        Map<User, BigDecimal> map = new HashMap<>();

        for (Expense expense : group.getExpenses()) {
            boolean isPaidByCurrentUser = expense.getPaidBy().equals(currentUser);
            for (UserExpense ue : expense.getUserExpenses()) {
                if (ue.getStatus().equals("unsettled")) {
                    if (isPaidByCurrentUser && !ue.getUser().equals(currentUser)) {
                        BigDecimal amount = map.getOrDefault(ue.getUser(), BigDecimal.valueOf(0));
                        amount = amount.add(ue.getExpenseAmount());
                        map.put(ue.getUser(), amount);
                    } else if (!isPaidByCurrentUser && ue.getUser().equals(currentUser)) {
                        BigDecimal amount = map.getOrDefault(expense.getPaidBy(), BigDecimal.valueOf(0));
                        amount = amount.subtract(ue.getExpenseAmount());
                        map.put(expense.getPaidBy(), amount);
                    }
                }
            }
        }

        List<BalanceDto> balances = new ArrayList<>();

        for (Map.Entry<User, BigDecimal> entry : map.entrySet()) {
            if (entry.getValue().compareTo(BigDecimal.valueOf(0)) < 0) {
                balances.add(new BalanceDto(currentUser.getId(), entry.getKey().getId(), currentUser.getFullName(), entry.getKey().getFullName(), entry.getValue().abs()));
            } else {
                balances.add(new BalanceDto(entry.getKey().getId(), currentUser.getId(), entry.getKey().getFullName(), currentUser.getFullName(), entry.getValue().abs()));
            }
        }

        return balances;
    }

    @Override
    @Transactional
    public void createPayment(PaymentDto paymentDto) {
        User payer = userRepository.getById(paymentDto.getPayerId());
        User payee = userRepository.getById(paymentDto.getPayeeId());
        Group group = groupRepository.getById(paymentDto.getGroupId());

        for (Expense expense : group.getExpenses()) {
            if (expense.getPaidBy().equals(payer) || expense.getPaidBy().equals(payee)) {
                for (UserExpense ue: expense.getUserExpenses()) {
                    if (ue.getUser().equals(payee) || ue.getUser().equals(payer)) {
                        ue.setStatus("settled");
                    }
                }
            }
        }

        Notification payeeNf = new Notification(payer.getFullName() + " paid Rs. " + paymentDto.getAmount() +
                " To You" + " in \"" + group.getName() + "\"");

        Notification payerNf = new Notification("You paid Rs. " + paymentDto.getAmount() +
                " To " + payee.getFullName() + " in \"" + group.getName() + "\"");

        payee.addNotification(payeeNf);
        payer.addNotification(payerNf);

        Payment newPayment = new Payment();
        newPayment.setPayer(payer);
        newPayment.setPayee(payee);
        newPayment.setGroup(group);
        newPayment.setAmount(paymentDto.getAmount());
        newPayment.setType(paymentDto.getType());

        paymentRepository.save(newPayment);
    }

    @Override
    @Transactional
    public List<PaymentDto> getGroupPayments(Long groupId) {
        Group group = groupRepository.getById(groupId);
        List<PaymentDto> payments = new ArrayList<>();

        for (Payment payment : group.getPayments()) {
            PaymentDto pdto = new PaymentDto();
            pdto.setPayerId(payment.getPayer().getId());
            pdto.setPayeeId(payment.getPayee().getId());
            pdto.setPayerName(payment.getPayer().getFullName());
            pdto.setPayeeName(payment.getPayee().getFullName());
            pdto.setAmount(payment.getAmount());
            pdto.setType(payment.getType());
            pdto.setDateCreated(payment.getDateCreated());
            pdto.setGroupId(groupId);

            payments.add(pdto);
        }

        payments.sort((o1, o2) -> o2.getDateCreated().compareTo(o1.getDateCreated()));

        return payments;
    }
}
