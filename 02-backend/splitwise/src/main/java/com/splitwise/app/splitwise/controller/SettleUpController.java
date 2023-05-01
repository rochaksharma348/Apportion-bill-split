package com.splitwise.app.splitwise.controller;

import com.splitwise.app.splitwise.dto.BalanceDto;
import com.splitwise.app.splitwise.dto.PaymentDto;
import com.splitwise.app.splitwise.service.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settle-up")
public class SettleUpController {
    @Autowired
    private SettleUpService settleUpService;

    @GetMapping("/balances")
    public List<BalanceDto> getAllBalancesForUser(@RequestParam("userId") Long userId, @RequestParam("groupId") Long groupId) {
        return settleUpService.getAllBalancesForUser(userId, groupId);
    }

    @PostMapping("/create-payment")
    public void createPayment(@RequestBody PaymentDto paymentDto) {
        settleUpService.createPayment(paymentDto);
        
    }

    @GetMapping("/group-payments/{groupId}")
    public List<PaymentDto> getGroupPayments(@PathVariable("groupId") Long groupId) {
        return settleUpService.getGroupPayments(groupId);
    }
}
