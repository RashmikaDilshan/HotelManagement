# Restaurant Management Service - Complete Documentation

## 📋 Table of Contents
- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Backend Service](#backend-service)
- [Features](#features)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Deployment](#deployment)
- [Development Setup](#development-setup)

---

## 🎯 Overview

The Restaurant Management Service manages the hotel's dining operations. It coordinates restaurant tables, the menu offerings, and guest orders, tracking operations dynamically from new orders to delivery and billing.

---

## 🛠 Technology Stack

### Frontend Technologies
| Technology | Purpose |
|------------|---------|
| **React** | Component-based UI Library |
| **Vite** | Build Tool & Dev Server |
| **Axios** | API Interfacing |
| **React Router** | Application Routing |

### Backend Technologies
| Technology | Purpose |
|------------|---------|
| **Java** | 21 |
| **Spring Boot** | Application Framework |
| **Spring Data JPA** | Data Persistence Layer |
| **MySQL & H2** | Primary and Embedded Databases |
| **Hibernate** | ORM Framework |
| **Lombok** | Boilerplate Code Reduction |
| **SpringDoc OpenAPI** | API Documentation |
| **Spring Actuator** | Health Monitoring |
| **Maven** | Build Tool |


## 💻 Frontend Application

### Project Structure
The frontend is built using **React** and **Vite**.
```text
frontend/restaurant-management/
├── src/                         # Main source code directory
│   ├── components/              # Reusable UI components
│   ├── pages/                   # Application pages/screens
│   ├── App.jsx                  # Main application component
│   └── main.jsx                 # Application entry point
├── public/                      # Static assets
├── package.json                 # Dependencies and project definition
└── vite.config.js               # Vite configuration
```

### Key Dependencies (`package.json`)
- **react / react-dom**: Core UI library for building component-centric interfaces
- **vite**: Next-generation frontend tooling for fast development
- **axios**: Promise-based HTTP client for backend API interactions
- **react-router-dom**: Declarative routing for React applications

### UI Features
- Fast module replacement and fast startup thanks to Vite.
- Responsive, component-based user interface.
- Direct connectivity to Sprint Boot backend REST endpoints using Axios.

---

## 🔧 Backend Service

### Project Structure
```
backend/restaurantManagementService/
├── src/
│   ├── main/
│   │   ├── java/com/nsbm/group03/restaurantManagementService/
│   │   │   ├── config/                    # Configuration classes
│   │   │   ├── controller/                # REST Controllers
│   │   │   │   ├── MenuItemController.java
│   │   │   │   ├── RestaurantOrderController.java
│   │   │   │   └── RestaurantTableController.java
│   │   │   ├── dto/                       # Data Transfer Objects
│   │   │   ├── entity/                    # JPA Entities
│   │   │   │   ├── MenuItem.java
│   │   │   │   ├── RestaurantOrder.java
│   │   │   │   └── RestaurantTable.java
│   │   │   ├── enums/                     # Enums for TableStatus, OrderStatus
│   │   │   ├── repository/                # Data Access Layer
│   │   │   ├── service/                   # Business Logic
│   │   │   └── RestaurantManagementServiceApplication.java
│   │   └── resources/
│   │       ├── application.properties     # General Configuration
│   │       └── application.yaml           # Detailed Configuration
│   └── test/                              # Unit Tests
├── pom.xml                                # Maven Dependencies
```

### Key Backend Components

#### 1. Menu Item Entity (`MenuItem.java`)
```java
@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Double price;
    private boolean available;
}
```

#### 2. Restaurant Order Entity (`RestaurantOrder.java`)
```java
@Entity
@Table(name = "restaurant_orders")
public class RestaurantOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tableId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Double totalAmount;
    private String specialNotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```

#### 3. Restaurant Table Entity (`RestaurantTable.java`)
```java
@Entity
@Table(name = "restaurant_tables")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int tableNumber;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private TableStatus status;
}
```

#### 4. Constants & Configurations (`application.yaml`)
- Configured to use MySQL Database: `hotel_db`.
- Application runs on **port 8086**.

### API Endpoints

#### Tables (`/api/restaurant/tables`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Get all tables |
| POST | `/` | Add a new table |
| GET | `/{id}` | Get table by ID |
| GET | `/available` | List available tables |
| GET | `/status/{status}` | Filter tables by status |
| PUT | `/{id}` | Update table details |
| PUT | `/{id}/occupy` | Mark table as occupied |
| PUT | `/{id}/free` | Mark table as free |
| DELETE | `/{id}` | Delete a table |

#### Menu Items (`/api/restaurant/menu`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Get *available* menu items |
| GET | `/all` | Get *all* menu items |
| POST | `/` | Add a new menu item |
| GET | `/{id}` | Get menu item by ID |
| GET | `/category/{category}` | Browse menu by category |
| GET | `/search?name=X` | Search items by name |
| PUT | `/{id}` | Update menu item details |
| PATCH | `/{id}/availability` | Toggle item availability |
| DELETE | `/{id}` | Delete a menu item |

#### Orders (`/api/restaurant/orders`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Get all orders |
| POST | `/` | Create a new order |
| GET | `/{id}` | Get specific order |
| GET | `/active` | View all active orders |
| GET | `/status/{status}` | Filter orders by status |
| GET | `/table/{tableId}` | Retrieve orders for a specific table |
| PATCH | `/{id}/status` | Update order status (JSON `{"status": "..."}`) |
| PUT | `/{id}` | Update total order |
| DELETE | `/{id}` | Delete an order |
| GET | `/dashboard/counts` | Get order counts grouped by status |

---

## ✨ Features

### Operations
✅ **Table Management**
- Register tables with specific numbers and guest capacities.
- Update table statuses directly depending on occupancy (`AVAILABLE`, `OCCUPIED`).
- Query available tables instantly for new walk-ins.

✅ **Flexible Menu Configuration**
- Maintain a list of menu items categorized by dining options or course type.
- Quickly toggle availability to hide items that are out of stock.
- Filter and search mechanism.

✅ **Order Tracking**
- Manage full-lifecycle orders assigned to tables.
- Track active running orders and update statuses as meals are prepared and delivered.
- Note any special dietary requirements or preparation instructions.

---

## 📚 API Documentation

### Interactive Swagger UI
The application makes use of SpringDoc OpenAPI for seamless API exploration:
`http://localhost:8086/swagger-ui.html`

---

## 🗄 Database Schema

### MySQL Schema

```sql
CREATE TABLE menu_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    available BOOLEAN NOT NULL
);

CREATE TABLE restaurant_tables (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    table_number INT NOT NULL,
    capacity INT NOT NULL,
    status VARCHAR(255) NOT NULL
);

CREATE TABLE restaurant_orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    table_id BIGINT NOT NULL,
    order_status VARCHAR(255) NOT NULL,
    total_amount DOUBLE NOT NULL,
    special_notes VARCHAR(500),
    created_at DATETIME,
    updated_at DATETIME
);
```

### Properties mapping
```yaml
spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver
server:
  port: 8086
```

---

## 🚀 Deployment

### Frontend Deployment (React + Vite)
```bash
# 1. Navigate to frontend directory
cd frontend/restaurant-management

# 2. Install dependencies
npm install

# 3. Build for Production
npm run build
# Output will be located in the dist/ directory
```

### Backend Deployment (Docker & Spring Boot)
The codebase uses Maven for building the project.

#### Using Maven Spring Boot
```bash
# 1. Navigate to backend directory
cd backend/restaurantManagementService

# 2. Build via Maven
mvn clean install

# 3. Run application
mvn spring-boot:run
```

---

## 💻 Development Setup

### Frontend Setup
1. Ensure **Node.js** and **npm** are installed.
2. Navigate to `frontend/restaurant-management`.
3. Run `npm install` to download required dependencies.
4. Run `npm run dev` to start the Vite development server.
5. Access the application in your browser (usually at `http://localhost:5173`).

### Backend Setup
1. Ensure **Java 21**, **Maven**, and **MySQL** are installed.
2. Clone repository and navigate to `backend/restaurantManagementService`.
3. Provide missing configurations or update Database Credentials inside `application.yaml`.
4. Ensure your local MySQL instance is running.
5. Run `mvn spring-boot:run` or execute `RestaurantManagementServiceApplication.java` from your Java IDE.
6. To run in dev mode with H2 in-memory DB instead, execute with `--spring.profiles.active=dev`.

---

**Status**: ✅ Implementation Available  
**Port**: `8086`
