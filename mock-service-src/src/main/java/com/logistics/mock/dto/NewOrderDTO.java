package com.logistics.mock.dto;

public class NewOrderDTO {

    private double capacity;
    private String userId;
    private String email;
    private RouteResponseDTO routeResponseDTO;

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RouteResponseDTO getRouteResponseDTO() {
        return routeResponseDTO;
    }

    public void setRouteResponseDTO(RouteResponseDTO routeResponseDTO) {
        this.routeResponseDTO = routeResponseDTO;
    }
}
