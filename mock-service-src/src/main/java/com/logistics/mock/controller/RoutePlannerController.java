package com.logistics.mock.controller;

import com.logistics.mock.dto.DeliveryRequestDTO;
import com.logistics.mock.dto.RouteRequestDTO;
import com.logistics.mock.dto.RouteResponseDTO;
import com.logistics.mock.store.MockDataStore;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/route")
public class RoutePlannerController {

    @PostMapping("/to-warehouse")
    public RouteResponseDTO toWarehouse(@RequestBody RouteRequestDTO req) {
        return MockDataStore.planRouteToWarehouse(req);
    }

    @PostMapping("/from-warehouse")
    public RouteResponseDTO fromWarehouse(@RequestBody DeliveryRequestDTO req) {
        return MockDataStore.planRouteFromWarehouse(req);
    }
}
