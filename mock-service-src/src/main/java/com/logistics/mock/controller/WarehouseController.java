package com.logistics.mock.controller;

import com.logistics.mock.model.Warehouse;
import com.logistics.mock.store.MockDataStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @GetMapping("/")
    public List<Warehouse> getWarehouses() {
        return MockDataStore.getWarehouses();
    }
}
