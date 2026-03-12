package com.logistics.mock.model;

public class Warehouse {

    private Long warehouseId;
    private String address;
    private double latitude;
    private double longitude;
    private int maxCapacity;
    private double currentLoad;
    private String responsibleContact;
    private WarehouseType type;

    public Warehouse() {
    }

    public Warehouse(Long warehouseId, String address, double latitude, double longitude,
                     int maxCapacity, double currentLoad, String responsibleContact, WarehouseType type) {
        this.warehouseId = warehouseId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxCapacity = maxCapacity;
        this.currentLoad = currentLoad;
        this.responsibleContact = responsibleContact;
        this.type = type;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public double getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(double currentLoad) {
        this.currentLoad = currentLoad;
    }

    public String getResponsibleContact() {
        return responsibleContact;
    }

    public void setResponsibleContact(String responsibleContact) {
        this.responsibleContact = responsibleContact;
    }

    public WarehouseType getType() {
        return type;
    }

    public void setType(WarehouseType type) {
        this.type = type;
    }
}
