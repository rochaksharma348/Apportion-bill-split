package com.splitwise.app.splitwise.service;

import com.splitwise.app.splitwise.dto.BalanceDto;
import com.splitwise.app.splitwise.dto.PaymentDto;

import java.util.List;

public interface SettleUpService {
    public List<BalanceDto> getAllBalancesForUser(Long userId, Long groupId);
    public void createPayment(PaymentDto paymentDto);

    public List<PaymentDto> getGroupPayments(Long groupId);
}
