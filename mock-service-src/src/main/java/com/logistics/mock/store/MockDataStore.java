package com.logistics.mock.store;

import com.logistics.mock.dto.*;
import com.logistics.mock.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MockDataStore {

    private static final AtomicLong userIdSeq = new AtomicLong(100);
    private static final AtomicLong orderIdSeq = new AtomicLong(1003);
    private static final Map<String, User> usersByEmail = new ConcurrentHashMap<>();
    private static final Map<String, String> passwords = new ConcurrentHashMap<>();
    private static final Map<String, BigDecimal> balances = new ConcurrentHashMap<>();
    private static final List<Order> orders = Collections.synchronizedList(new ArrayList<>());
    private static final List<Warehouse> warehouses = new ArrayList<>();
    private static final List<CryptoTransactionDTO> cryptoTransactions = Collections.synchronizedList(new ArrayList<>());
    private static final List<FiatTransactionDTO> fiatTransactions = Collections.synchronizedList(new ArrayList<>());

    static {
        addUser(1L, "admin", "admin@logistics.com", "admin123", Role.ADMIN);
        addUser(2L, "manager", "manager@logistics.com", "manager123", Role.MANAGER);
        addUser(3L, "john", "john@example.com", "pass123", Role.USER);
        addUser(4L, "alice", "alice@example.com", "pass123", Role.USER);
        addUser(5L, "bob", "bob@example.com", "pass123", Role.USER);

        balances.put("1", new BigDecimal("10000.00"));
        balances.put("2", new BigDecimal("5000.00"));
        balances.put("3", new BigDecimal("1500.50"));
        balances.put("4", new BigDecimal("2300.00"));
        balances.put("5", new BigDecimal("800.75"));

        warehouses.add(new Warehouse(1L, "Paris, Rue de Rivoli 9", 48.8566, 2.3522, 5000, 2300, "Jane Smith", WarehouseType.FOOD));
        warehouses.add(new Warehouse(2L, "Paris, Av. des Champs-Elysees 15", 48.8698, 2.3076, 8000, 4100, "John Davis", WarehouseType.MATERIALS));
        warehouses.add(new Warehouse(3L, "Versailles, Boulevard du Roi 3", 48.8049, 2.1204, 3000, 1200, "Alex Johnson", WarehouseType.ELECTRONICS));
        warehouses.add(new Warehouse(4L, "Orly, Route de Fontainebleau 201", 48.7262, 2.3652, 6000, 3500, "Mary Wilson", WarehouseType.FOOD));

        long now = System.currentTimeMillis();
        long day = 86400000L;

        orders.add(new Order(1001L, 1L, "john@example.com", "3",
                new Date(now - 3 * day), null, "Paris, Boulevard Haussmann 1",
                12500, 50, WarehouseType.FOOD, new BigDecimal("450.00"), OrderStatus.PROCESSING));
        orders.add(new Order(1002L, 2L, "alice@example.com", "4",
                new Date(now - 1 * day), null, "Paris, Rue de la Paix 24",
                8200, 120, WarehouseType.MATERIALS, new BigDecimal("780.00"), OrderStatus.CREATED));
        orders.add(new Order(1003L, 3L, "bob@example.com", "5",
                new Date(now - 7 * day), new Date(now - 2 * day), "Versailles, Rue du Parc 10",
                5300, 15, WarehouseType.ELECTRONICS, new BigDecimal("320.00"), OrderStatus.DELIVERED_TO_CLIENT));

        cryptoTransactions.add(new CryptoTransactionDTO("3", "INV-CRYPTO-001", new BigDecimal("0.015"),
                "USD", "BTC", new Date(now - 5 * day), PaymentStatus.SUCCESS));
        cryptoTransactions.add(new CryptoTransactionDTO("4", "INV-CRYPTO-002", new BigDecimal("1.200"),
                "USD", "ETH", new Date(now - 2 * day), PaymentStatus.CREATED));

        fiatTransactions.add(new FiatTransactionDTO("3", "INV-FIAT-001", new BigDecimal("450.00"),
                "UAH", new Date(now - 3 * day), PaymentStatus.SUCCESS));
        fiatTransactions.add(new FiatTransactionDTO("5", "INV-FIAT-002", new BigDecimal("320.00"),
                "UAH", new Date(now - 7 * day), PaymentStatus.SUCCESS));
        fiatTransactions.add(new FiatTransactionDTO("4", "INV-FIAT-003", new BigDecimal("780.00"),
                "UAH", new Date(now - 1 * day), PaymentStatus.CREATED));
    }

    private static void addUser(Long id, String username, String email, String password, Role role) {
        usersByEmail.put(email, new User(id, username, email, role));
        passwords.put(email, password);
        if (id >= userIdSeq.get()) {
            userIdSeq.set(id + 1);
        }
    }

    public static AuthResponse register(RegisterRequest req) {
        long id = userIdSeq.incrementAndGet();
        Role role = req.getRole() != null ? req.getRole() : Role.USER;
        User user = new User(id, req.getUsername(), req.getEmail(), role);
        usersByEmail.put(req.getEmail(), user);
        passwords.put(req.getEmail(), req.getPassword());
        balances.put(String.valueOf(id), BigDecimal.ZERO);
        return new AuthResponse(user.getUsername(), user.getEmail(), user.getRole(), "mock-jwt-token-" + id);
    }

    public static AuthResponse login(LoginRequest req) {
        User user = usersByEmail.get(req.getEmail());
        if (user == null) return null;
        String storedPass = passwords.get(req.getEmail());
        if (storedPass == null || !storedPass.equals(req.getPassword())) return null;
        return new AuthResponse(user.getUsername(), user.getEmail(), user.getRole(), "mock-jwt-token-" + user.getId());
    }

    public static AuthResponse getMe(String email) {
        User user = usersByEmail.get(email);
        if (user == null) return null;
        return new AuthResponse(user.getUsername(), user.getEmail(), user.getRole(), "mock-jwt-token-" + user.getId());
    }

    public static List<User> getAllUsers() {
        return new ArrayList<>(usersByEmail.values());
    }

    public static boolean deleteUser(String userId) {
        Long id = Long.parseLong(userId);
        return usersByEmail.values().removeIf(u -> u.getId().equals(id));
    }

    public static boolean changeRole(String userId, Role role) {
        Long id = Long.parseLong(userId);
        for (User u : usersByEmail.values()) {
            if (u.getId().equals(id)) {
                u.setRole(role);
                return true;
            }
        }
        return false;
    }

    public static List<FullUserInfo> getUsersWithBalance() {
        List<FullUserInfo> result = new ArrayList<>();
        for (User u : usersByEmail.values()) {
            BigDecimal bal = balances.getOrDefault(String.valueOf(u.getId()), BigDecimal.ZERO);
            result.add(new FullUserInfo(u.getId(), u.getUsername(), u.getEmail(), u.getRole(), bal));
        }
        return result;
    }

    public static BigDecimal getBalance(String userId) {
        return balances.getOrDefault(userId, BigDecimal.ZERO);
    }

    public static List<UserBalance> getAllBalances() {
        List<UserBalance> result = new ArrayList<>();
        long seq = 1;
        for (Map.Entry<String, BigDecimal> entry : balances.entrySet()) {
            result.add(new UserBalance(seq++, entry.getKey(), entry.getValue()));
        }
        return result;
    }

    public static List<CryptoTransactionDTO> getCryptoTransactions(String userId) {
        if (userId == null) return new ArrayList<>(cryptoTransactions);
        return cryptoTransactions.stream()
                .filter(t -> t.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public static List<FiatTransactionDTO> getFiatTransactions(String userId) {
        if (userId == null) return new ArrayList<>(fiatTransactions);
        return fiatTransactions.stream()
                .filter(t -> t.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public static String payForDelivery(String userId, BigDecimal amount) {
        BigDecimal current = balances.getOrDefault(userId, BigDecimal.ZERO);
        if (current.compareTo(amount) < 0) {
            return "Insufficient balance";
        }
        BigDecimal remaining = current.subtract(amount);
        balances.put(userId, remaining);
        String invoiceId = "INV-FIAT-" + System.currentTimeMillis();
        fiatTransactions.add(new FiatTransactionDTO(userId, invoiceId, amount, "UAH", new Date(), PaymentStatus.SUCCESS));
        return "Payment successful. Remaining balance: " + remaining;
    }

    public static String createCryptoPayment(String userId, PaymentRequest req) {
        String invoiceId = "INV-CRYPTO-" + System.currentTimeMillis();
        cryptoTransactions.add(new CryptoTransactionDTO(userId, invoiceId, req.getAmount(),
                req.getCurrency(), "BTC", new Date(), PaymentStatus.CREATED));
        return "https://mock-crypto-gateway.example.com/pay/" + invoiceId;
    }

    public static String createFiatPayment(String userId, PaymentRequest req) {
        String invoiceId = "INV-FIAT-" + System.currentTimeMillis();
        fiatTransactions.add(new FiatTransactionDTO(userId, invoiceId, req.getAmount(),
                req.getCurrency(), new Date(), PaymentStatus.CREATED));
        return "https://mock-stripe.example.com/checkout/" + invoiceId;
    }

    public static List<Order> getOrdersByUserId(String userId) {
        return orders.stream()
                .filter(o -> o.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public static List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public static Order createOrder(NewOrderDTO dto) {
        long id = orderIdSeq.incrementAndGet();
        RouteResponseDTO route = dto.getRouteResponseDTO();
        Long warehouseId = route != null && route.getWarehouse() != null ? route.getWarehouse().getWarehouseId() : 1L;
        double distance = route != null ? route.getDistanceMeters() : 0;
        BigDecimal price = route != null && route.getPriceUah() != null ? route.getPriceUah() : BigDecimal.ZERO;
        String address = route != null && route.getWarehouse() != null ? route.getWarehouse().getName() : "Unknown";

        Order order = new Order(id, warehouseId, dto.getEmail(), dto.getUserId(),
                new Date(), null, address, distance, (int) dto.getCapacity(),
                WarehouseType.FOOD, price, OrderStatus.CREATED);
        orders.add(order);
        return order;
    }

    public static int getOrderCapacity(String orderId) {
        Long id = Long.parseLong(orderId);
        for (Order o : orders) {
            if (o.getOrderId().equals(id)) {
                return o.getWeight();
            }
        }
        return 0;
    }

    public static boolean changeOrderStatus(String orderId, OrderStatus status) {
        Long id = Long.parseLong(orderId);
        for (Order o : orders) {
            if (o.getOrderId().equals(id)) {
                o.setStatus(status);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteOrder(String orderId) {
        Long id = Long.parseLong(orderId);
        return orders.removeIf(o -> o.getOrderId().equals(id));
    }

    public static List<Warehouse> getWarehouses() {
        return new ArrayList<>(warehouses);
    }

    public static RouteResponseDTO planRouteToWarehouse(RouteRequestDTO req) {
        Warehouse nearest = warehouses.get(0);
        double distance = 15200 + Math.random() * 10000;
        double duration = distance / 10.0;
        BigDecimal price = BigDecimal.valueOf(250.00 + distance * 0.01).setScale(2, RoundingMode.HALF_UP);

        RouteResponseDTO.WarehouseInfo wInfo = new RouteResponseDTO.WarehouseInfo(
                nearest.getWarehouseId(), nearest.getAddress(),
                nearest.getLatitude(), nearest.getLongitude(), nearest.getType().name());

        double midLat = (req.getLat() + nearest.getLatitude()) / 2;
        double midLng = (req.getLng() + nearest.getLongitude()) / 2;

        List<List<Double>> coords = new ArrayList<>();
        coords.add(Arrays.asList(req.getLng(), req.getLat()));
        coords.add(Arrays.asList(midLng, midLat));
        coords.add(Arrays.asList(nearest.getLongitude(), nearest.getLatitude()));

        RouteResponseDTO.GeoJsonRoute geoJson = new RouteResponseDTO.GeoJsonRoute(coords);

        return new RouteResponseDTO(distance, duration, price, wInfo, geoJson);
    }

    public static RouteResponseDTO planRouteFromWarehouse(DeliveryRequestDTO req) {
        Warehouse source = warehouses.get(0);
        double distance = 8300 + Math.random() * 8000;
        double duration = distance / 8.5;
        BigDecimal price = BigDecimal.valueOf(180.00 + distance * 0.008).setScale(2, RoundingMode.HALF_UP);

        RouteResponseDTO.WarehouseInfo wInfo = new RouteResponseDTO.WarehouseInfo(
                source.getWarehouseId(), source.getAddress(),
                source.getLatitude(), source.getLongitude(), source.getType().name());

        double midLat = (source.getLatitude() + req.getToLat()) / 2;
        double midLng = (source.getLongitude() + req.getToLng()) / 2;

        List<List<Double>> coords = new ArrayList<>();
        coords.add(Arrays.asList(source.getLongitude(), source.getLatitude()));
        coords.add(Arrays.asList(midLng, midLat));
        coords.add(Arrays.asList(req.getToLng(), req.getToLat()));

        RouteResponseDTO.GeoJsonRoute geoJson = new RouteResponseDTO.GeoJsonRoute(coords);

        return new RouteResponseDTO(distance, duration, price, wInfo, geoJson);
    }
}
