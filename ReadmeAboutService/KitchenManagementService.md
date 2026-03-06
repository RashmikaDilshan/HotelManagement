# Kitchen Management Service - Complete Documentation

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

The Kitchen Management Service is a comprehensive microservice designed for a Hotel Management System. It handles the complete lifecycle of kitchen menu items and kitchen orders, tracking their progress and integrating with the Inventory Management Service to consume stock.

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
frontend/kitchen-management/
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
backend/kitchenManagementService/
├── src/
│   ├── main/
│   │   ├── java/com/nsbm/group03/kitchenManagementService/
│   │   │   ├── config/                    # Configuration classes
│   │   │   ├── controller/                # REST Controllers
│   │   │   │   ├── KitchenMenuItemController.java
│   │   │   │   └── KitchenOrderController.java
│   │   │   ├── dto/                       # Data Transfer Objects
│   │   │   ├── entity/                    # JPA Entities
│   │   │   │   ├── KitchenMenuItem.java
│   │   │   │   ├── KitchenOrder.java
│   │   │   │   └── KitchenOrderItem.java
│   │   │   ├── enums/                     # Enums for Status, MealType, ServiceType
│   │   │   ├── repository/                # Data Access Layer
│   │   │   ├── service/                   # Business Logic
│   │   │   └── KitchenManagementServiceApplication.java
│   │   └── resources/
│   │       └── application.yaml           # Configuration
│   └── test/                              # Unit Tests
├── pom.xml                                # Maven Dependencies
```

### Key Backend Components

#### 1. Kitchen Menu Item Entity (`KitchenMenuItem.java`)
```java
@Entity
@Table(name = "kitchen_menu_items")
public class KitchenMenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String category;
    private Double price;
    private boolean available;
    @Enumerated(EnumType.STRING)
    private MealType mealType;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    private LocalDate menuDate;
    private Long restaurantId;
    private String description;
}
```

#### 2. Kitchen Order Entity (`KitchenOrder.java`)
```java
@Entity
@Table(name = "kitchen_orders")
public class KitchenOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long restaurantId;
    private String tableNumber;
    private Long staffId; 
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String specialInstructions;
    private Double totalAmount;
    @OneToMany(mappedBy = "kitchenOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KitchenOrderItem> orderItems = new ArrayList<>();
}
```

#### 3. Constants & Configurations (`application.yaml`)
- Configured to use MySQL by default.
- Uses environment variables for database URLs and credentials.
- Application runs on **port 8083**.
- Automatically integrates with the Inventory service running on port 8082.

### API Endpoints

#### Menu Items
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/kitchen/menu` | Get all menu items |
| POST | `/api/kitchen/menu` | Create a new menu item |
| GET | `/api/kitchen/menu/{id}` | Get menu item by ID |
| PUT | `/api/kitchen/menu/{id}` | Update menu item |
| DELETE | `/api/kitchen/menu/{id}` | Delete menu item |
| PATCH | `/api/kitchen/menu/{id}/toggle-availability` | Toggle item availability |
| GET | `/api/kitchen/menu/restaurant/{id}` | Filter by restaurant |
| GET | `/api/kitchen/menu/meal-type/{type}` | Filter by meal type |
| GET | `/api/kitchen/menu/search?name=X` | Search items by keyword |

#### Orders
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/kitchen/orders` | Get all orders |
| POST | `/api/kitchen/orders` | Create new kitchen order |
| GET | `/api/kitchen/orders/{id}` | Get specific order |
| PATCH | `/api/kitchen/orders/{id}/status` | Update order status (PENDING -> COOKING -> READY -> SERVED) |
| PATCH | `/api/kitchen/orders/{id}/assign-staff` | Assign staff to order |
| GET | `/api/kitchen/orders/status/{status}` | Get orders by status |
| GET | `/api/kitchen/orders/dashboard/counts` | Get order counts grouped by status |

---

## ✨ Features

### Operations
✅ **Menu Management**
- Complete CRUD operations for menu items.
- Dynamic filtering by restaurant ID, meal type (BREAKFAST, LUNCH, DINNER, BUFFET), service type (RESTAURANT, EVENT), category, and price range.
- Dedicated endpoints for event and restaurant-specific menus.
- Easy toggling of menu item availability.

✅ **Order Tracking & Processing**
- Place multi-item kitchen orders mapped to restaurant tables.
- Advanced state pattern for statuses (PENDING -> COOKING -> READY -> SERVED).
- Staff assignment functionality to delegate orders to particular cooks/kitchen staff.

✅ **Inventory Integration**
- The Kitchen service communicates directly with the Inventory Management Service.
- Can fetch available stock directly from the inventory microservice endpoints.
- Ensures stock levels are properly evaluated when preparing dishes.

---

## 📚 API Documentation

### Interactive Swagger UI
If enabled via `springdoc-openapi-starter-webmvc-ui`, you can access detailed endpoint testing at:
`http://localhost:8083/swagger-ui.html` or `http://localhost:8083/swagger-ui/index.html`

---

## 🗄 Database Schema

### MySQL Schema

```sql
CREATE TABLE kitchen_menu_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    available BOOLEAN NOT NULL,
    meal_type VARCHAR(255) NOT NULL,
    service_type VARCHAR(255) NOT NULL,
    menu_date DATE NOT NULL,
    restaurant_id BIGINT NOT NULL,
    description VARCHAR(500),
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE kitchen_orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    restaurant_id BIGINT NOT NULL,
    table_number VARCHAR(255),
    staff_id BIGINT,
    order_status VARCHAR(255) NOT NULL,
    special_instructions VARCHAR(500),
    total_amount DOUBLE NOT NULL,
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
  port: 8083
```

---

## 🚀 Deployment

### Frontend Deployment (React + Vite)
```bash
# 1. Navigate to frontend directory
cd frontend/kitchen-management

# 2. Install dependencies
npm install

# 3. Build for Production
npm run build
# Output will be located in the dist/ directory
```

### Backend Deployment (Docker & Spring Boot)
The provided codebase can be dockerized.

#### Using Maven Spring Boot
```bash
# 1. Navigate to backend directory
cd backend/kitchenManagementService

# 2. Build via Maven
mvn clean install

# 3. Run application
mvn spring-boot:run
```

---

## 💻 Development Setup

### Frontend Setup
1. Ensure **Node.js** and **npm** are installed.
2. Navigate to `frontend/kitchen-management`.
3. Run `npm install` to download required dependencies.
4. Run `npm run dev` to start the Vite development server.
5. Access the application in your browser (usually at `http://localhost:5173`).

### Backend Setup
1. Ensure **Java 21**, **Maven**, and **MySQL** are installed.
2. Clone repository and navigate to `backend/kitchenManagementService`.
3. Provide missing environment variables (e.g., `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`), or configure `application.yaml` directly for local testing.
4. Run `mvn spring-boot:run` or execute `KitchenManagementServiceApplication.java` from your Java IDE.
5. To run in dev mode with H2 in-memory DB instead, execute with `--spring.profiles.active=dev`.

---

**Status**: ✅ Implementation Available  
**Port**: `8083`
