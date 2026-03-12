package com.logistics.mock.controller;

import com.logistics.mock.model.Role;
import com.logistics.mock.model.User;
import com.logistics.mock.store.MockDataStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/get-all-users")
    public List<User> getAllUsers() {
        return MockDataStore.getAllUsers();
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String userId) {
        boolean deleted = MockDataStore.deleteUser(userId);
        if (!deleted) {
            return ResponseEntity.status(404).body("User not found");
        }
        return ResponseEntity.ok("User deleted");
    }

    @PostMapping("/change-role")
    public ResponseEntity<String> changeRole(@RequestParam String userId, @RequestParam Role role) {
        boolean updated = MockDataStore.changeRole(userId, role);
        if (!updated) {
            return ResponseEntity.status(404).body("User not found");
        }
        return ResponseEntity.ok("Role updated");
    }
}
