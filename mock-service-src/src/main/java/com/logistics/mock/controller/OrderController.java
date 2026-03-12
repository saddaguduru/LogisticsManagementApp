package com.logistics.mock.controller;

import com.logistics.mock.dto.ChangeOrderStatusRequest;
import com.logistics.mock.dto.NewOrderDTO;
import com.logistics.mock.model.Order;
import com.logistics.mock.store.MockDataStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @GetMapping("/get")
    public List<Order> getOrders(@RequestHeader("X-User-Id") String userId) {
        return MockDataStore.getOrdersByUserId(userId);
    }

    @PostMapping("/new-order")
    public ResponseEntity<String> newOrder(@RequestBody NewOrderDTO dto) {
        Order order = MockDataStore.createOrder(dto);
        return ResponseEntity.ok("Order created with id: " + order.getOrderId());
    }

    @GetMapping("/get-capacity")
    public Integer getCapacity(@RequestParam String orderId) {
        return MockDataStore.getOrderCapacity(orderId);
    }

    @GetMapping("/get-all")
    public List<Order> getAllOrders(
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return MockDataStore.getAllOrders();
    }

    @PostMapping("/change-status")
    public ResponseEntity<String> changeStatus(
            @RequestBody ChangeOrderStatusRequest req,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        boolean updated = MockDataStore.changeOrderStatus(req.getOrderId(), req.getStatus());
        if (!updated) {
            return ResponseEntity.status(404).body("Order not found");
        }
        return ResponseEntity.ok("Status updated");
    }

    @PostMapping("/set-deliveryTime")
    public ResponseEntity<String> setDeliveryTime(
            @RequestBody Map<String, String> body,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        String orderId = body.get("orderId");
        return ResponseEntity.ok("Delivery time updated for order " + orderId);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteOrder(
            @RequestHeader(value = "X-User-Role", required = false) String role,
            @RequestParam String orderId) {
        boolean deleted = MockDataStore.deleteOrder(orderId);
        if (!deleted) {
            return ResponseEntity.status(404).body("Order not found");
        }
        return ResponseEntity.ok("Order deleted");
    }
}
