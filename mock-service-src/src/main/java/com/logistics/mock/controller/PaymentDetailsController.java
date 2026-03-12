package com.logistics.mock.controller;

import com.logistics.mock.dto.CryptoTransactionDTO;
import com.logistics.mock.dto.FiatTransactionDTO;
import com.logistics.mock.model.UserBalance;
import com.logistics.mock.store.MockDataStore;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/details")
public class PaymentDetailsController {

    @GetMapping("/balance")
    public BigDecimal getBalance(@RequestParam String userId) {
        return MockDataStore.getBalance(userId);
    }

    @GetMapping("/all/balance")
    public List<UserBalance> getAllBalances() {
        return MockDataStore.getAllBalances();
    }

    @GetMapping("/crypto-transactions")
    public List<CryptoTransactionDTO> getCryptoTransactions(@RequestHeader("X-User-Id") String userId) {
        return MockDataStore.getCryptoTransactions(userId);
    }

    @GetMapping("/all/crypto-transactions")
    public List<CryptoTransactionDTO> getAllCryptoTransactions() {
        return MockDataStore.getCryptoTransactions(null);
    }

    @GetMapping("/fiat-transactions")
    public List<FiatTransactionDTO> getFiatTransactions(@RequestHeader("X-User-Id") String userId) {
        return MockDataStore.getFiatTransactions(userId);
    }

    @GetMapping("/all/fiat-transactions")
    public List<FiatTransactionDTO> getAllFiatTransactions() {
        return MockDataStore.getFiatTransactions(null);
    }

    @PostMapping("/pay-for-delivery")
    public String payForDelivery(@RequestHeader("X-User-Id") String userId, @RequestParam BigDecimal amount) {
        return MockDataStore.payForDelivery(userId, amount);
    }
}
