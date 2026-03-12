package com.logistics.mock.dto;

import java.math.BigDecimal;
import java.util.List;

public class RouteResponseDTO {

    private double distanceMeters;
    private double durationSeconds;
    private BigDecimal priceUah;
    private WarehouseInfo warehouse;
    private GeoJsonRoute geoJsonRoute;

    public RouteResponseDTO() {
    }

    public RouteResponseDTO(double distanceMeters, double durationSeconds, BigDecimal priceUah,
                             WarehouseInfo warehouse, GeoJsonRoute geoJsonRoute) {
        this.distanceMeters = distanceMeters;
        this.durationSeconds = durationSeconds;
        this.priceUah = priceUah;
        this.warehouse = warehouse;
        this.geoJsonRoute = geoJsonRoute;
    }

    public double getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(double distanceMeters) {
        this.distanceMeters = distanceMeters;
    }

    public double getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(double durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public BigDecimal getPriceUah() {
        return priceUah;
    }

    public void setPriceUah(BigDecimal priceUah) {
        this.priceUah = priceUah;
    }

    public WarehouseInfo getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseInfo warehouse) {
        this.warehouse = warehouse;
    }

    public GeoJsonRoute getGeoJsonRoute() {
        return geoJsonRoute;
    }

    public void setGeoJsonRoute(GeoJsonRoute geoJsonRoute) {
        this.geoJsonRoute = geoJsonRoute;
    }

    public static class WarehouseInfo {

        private Long warehouseId;
        private String name;
        private double latitude;
        private double longitude;
        private String type;

        public WarehouseInfo() {
        }

        public WarehouseInfo(Long warehouseId, String name, double latitude, double longitude, String type) {
            this.warehouseId = warehouseId;
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
            this.type = type;
        }

        public Long getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(Long warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class GeoJsonRoute {

        private String type = "LineString";
        private List<List<Double>> coordinates;

        public GeoJsonRoute() {
        }

        public GeoJsonRoute(List<List<Double>> coordinates) {
            this.coordinates = coordinates;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<List<Double>> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<List<Double>> coordinates) {
            this.coordinates = coordinates;
        }
    }
}
