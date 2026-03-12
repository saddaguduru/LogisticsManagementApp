package com.logistics.mock.controller;

import com.logistics.mock.model.FullUserInfo;
import com.logistics.mock.store.MockDataStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gateway-admin")
public class AdminUserController {

    @GetMapping("/users-with-balance")
    public List<FullUserInfo> getUsersWithBalance() {
        return MockDataStore.getUsersWithBalance();
    }
}
