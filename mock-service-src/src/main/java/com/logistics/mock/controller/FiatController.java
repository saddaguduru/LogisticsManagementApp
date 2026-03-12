package com.logistics.mock.controller;

import com.logistics.mock.dto.PaymentRequest;
import com.logistics.mock.store.MockDataStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fiat")
public class FiatController {

    @PostMapping("/create-payment")
    public String createPayment(@RequestHeader("X-User-Id") String userId, @RequestBody PaymentRequest req) {
        return MockDataStore.createFiatPayment(userId, req);
    }

    @PostMapping("/handle-payment")
    public ResponseEntity<Void> handlePayment(
            @RequestHeader(value = "Stripe-Signature", required = false) String signature,
            @RequestBody(required = false) String payload) {
        return ResponseEntity.ok().build();
    }
}
