# Logistics Management App

A **self-contained demo bundle** — copy this entire folder to any machine with **Java 17+** and run it immediately. No database, no Kafka, no Redis, no Docker required.

## Quick Start

### Windows

1. Set `JAVA_HOME` if not already set (default: `C:\Program Files\Java\jdk-17`):
   ```cmd
   set JAVA_HOME=C:\path\to\your\jdk-17
   ```
2. Double-click **`start.bat`** or run from command prompt:
   ```cmd
   start.bat
   ```
3. A browser will automatically open to **http://localhost:8080** after 5 seconds.

### Linux / macOS

1. Set `JAVA_HOME` if not already set (default: `/opt/jdk-17`):
   ```bash
   export JAVA_HOME=/path/to/your/jdk-17
   ```
2. Run:
   ```bash
   chmod +x start.sh
   ./start.sh
   ```
3. A browser will automatically open to **http://localhost:8080** after 5 seconds.

## Login Credentials

| Username | Email                  | Password    | Role    |
|----------|------------------------|-------------|---------|
| admin    | admin@logistics.com    | admin123    | ADMIN   |
| manager  | manager@logistics.com  | manager123  | MANAGER |
| john     | john@example.com       | pass123     | USER    |
| alice    | alice@example.com      | pass123     | USER    |
| bob      | bob@example.com        | pass123     | USER    |

The login form is pre-filled with **admin@logistics.com / admin123** for easy access.

## What's Included

```
LogisticsManagementApp/
├── logistics-mock-service.jar    # Pre-built Spring Boot fat JAR
├── start.bat                     # Windows launcher (double-click)
├── start.sh                      # Linux / macOS launcher
├── build-from-source.bat         # Rebuild JAR from source (optional)
├── README.md                     # This file
└── mock-service-src/             # Full Maven source code
    ├── pom.xml
    ├── mvnw / mvnw.cmd           # Maven Wrapper
    └── src/main/
        ├── java/com/logistics/mock/   # Backend Java source
        └── resources/
            ├── application.properties
            └── static/index.html      # Embedded admin dashboard UI
```

## Pages Available for Demo

| Page           | Description                                               |
|----------------|-----------------------------------------------------------|
| **Dashboard**  | Metrics overview: total orders, active orders, warehouses, balance |
| **Orders**     | View, create, and delete orders                           |
| **Warehouses** | View warehouse details with utilization progress bars     |
| **Route Planner** | Calculate mock routes to warehouses (Paris area)       |
| **Payments**   | Create fiat/crypto payments, view transaction history     |
| **Admin**      | Manage users and balances (ADMIN/MANAGER only)            |

## HAR Capture Tips

To capture HTTP traffic as a `.har` file for analysis:

1. Open the app in **Google Chrome**
2. Press **F12** to open DevTools
3. Go to the **Network** tab
4. Check **Preserve log** to keep requests across page navigations
5. Interact with the application (login, navigate pages, create orders, etc.)
6. Right-click on the request list → **Save all as HAR with content**
7. Save the `.har` file

**Recommended workflow for a good HAR capture:**
- Login with admin credentials
- Visit Dashboard
- Navigate to Orders → Create a new order
- Navigate to Warehouses → View warehouse list
- Navigate to Route Planner → Calculate a route
- Navigate to Payments → Create a payment
- Navigate to Admin → View users

## API Endpoints

| Method | Endpoint                          | Description                    |
|--------|-----------------------------------|--------------------------------|
| POST   | `/api/auth/login`                 | User login                     |
| POST   | `/api/auth/register`              | User registration              |
| GET    | `/api/auth/me?email=`             | Get current user info          |
| GET    | `/api/order/get-all`              | List all orders                |
| POST   | `/api/order/new-order`            | Create a new order             |
| DELETE | `/api/order?orderId=`             | Delete an order                |
| GET    | `/api/warehouse/`                 | List all warehouses            |
| POST   | `/api/route/to-warehouse`         | Plan route to warehouse        |
| POST   | `/api/route/from-warehouse`       | Plan route from warehouse      |
| GET    | `/api/details/balance?userId=`    | Get user balance               |
| GET    | `/api/details/all/balance`        | Get all balances               |
| GET    | `/api/details/all/fiat-transactions`   | List fiat transactions    |
| GET    | `/api/details/all/crypto-transactions` | List crypto transactions  |
| POST   | `/api/fiat/create-payment`        | Create fiat payment            |
| POST   | `/api/crypto/create-payment`      | Create crypto payment          |
| GET    | `/api/admin/get-all-users`        | List all users (admin)         |
| POST   | `/api/admin/delete?userId=`       | Delete a user (admin)          |
| GET    | `/api/gateway-admin/users-with-balance` | Users with balances     |

## Rebuild from Source

If you want to rebuild the JAR from source:

### Windows
```cmd
build-from-source.bat
```

### Manual
```cmd
cd mock-service-src
mvnw.cmd clean package -DskipTests
copy target\mock-service-0.0.1-SNAPSHOT.jar ..\logistics-mock-service.jar
```

## Prerequisites

- **Java 17+** (JDK or JRE)
- **Port 8080** must be available
- No other dependencies required

## License

This project is licensed under the [MIT License](LICENSE).
