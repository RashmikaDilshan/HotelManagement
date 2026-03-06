# Room Management Service - Complete Documentation

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

The Room Management Service strictly tracks room availability, pricing, capacity, and status changes for the Hotel Management System. It also retains historical data on room statuses to generate advanced statistics and availability overviews for given dates.

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
| **MySQL** | Primary Database |
| **Hibernate** | ORM Framework |
| **Lombok** | Boilerplate Code Reduction |
| **SpringDoc OpenAPI** | API Documentation |
| **Spring Actuator** | Health Monitoring |
| **Maven** | Build Tool |


## 💻 Frontend Application

### Project Structure
The frontend is built using **React** and **Vite**.
```text
frontend/room-management/
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
backend/roomManagementService/
├── src/
│   ├── main/
│   │   ├── java/com/nsbm/group03/roomManagementService/
│   │   │   ├── Controller/                # REST Controllers
│   │   │   │   └── RoomController.java
│   │   │   ├── Dto/                       # Data Transfer Objects
│   │   │   ├── Entity/                    # JPA Entities
│   │   │   │   ├── Room.java
│   │   │   │   ├── RoomStatusHistory.java
│   │   │   │   └── RoomTypeEntity.java
│   │   │   ├── Enum/                      # Enums for RoomStatus, RoomType
│   │   │   ├── Mapper/                    # Object conversion mappers
│   │   │   ├── Repository/                # Data Access Layer
│   │   │   ├── Service/                   # Business Logic
│   │   │   └── RoomManagementServiceApplication.java
│   │   └── resources/
│   │       └── application.yaml           # Configuration
│   └── test/                              # Unit Tests
├── pom.xml                                # Maven Dependencies
```

### Key Backend Components

#### 1. Room Entity (`Room.java`)
```java
@Entity
public class Room {
    @Id    
    private String roomId;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    @Column(unique = true, nullable = false)
    private String roomNumber;
    private double pricePerNight;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomStatusHistory> statusHistory = new ArrayList<>();
}
```

#### 2. Room Status History Entity (`RoomStatusHistory.java`)
Tracks historical changes to any room.
```java
@Entity
public class RoomStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
    private String changedBy;
    private LocalDateTime changedAt;
}
```

#### 3. Constants & Configurations (`application.yaml`)
- Configured to use MySQL Database: `hms`.
- Application runs on **port 8082**.

### API Endpoints (`/api/rooms`)

#### Core Room Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Get all rooms |
| POST | `/` | Add a new room |
| GET | `/{roomNumber}` | Get room by room number |
| DELETE | `/{roomNumber}` | Delete a room & corresponding history |
| PATCH | `/{roomNumber}/status` | Force update a room's status |

#### Status Management Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/{roomNumber}/check-in` | Change room status to OCCUPIED |
| POST | `/{roomNumber}/check-out` | Change room status to AVAILABLE |
| POST | `/{roomNumber}/maintenance` | Mark room for MAINTENANCE |
| POST | `/{roomNumber}/available-after-maintenance` | Ready room after MAINTENANCE completion |

#### Analytics & History Filtering
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/available` | Filter available rooms for today |
| GET | `/available/by-date?date=X` | Filter available rooms on date X |
| GET | `/occupied` | Today's occupied rooms |
| GET | `/maintenance` | Today's rooms under maintenance |
| GET | `/{roomNumber}/status-history` | View history mapping of room timeline |
| GET | `/statistics` | Fetch high-level statistics & revenue estimates |

---

## ✨ Features

### Operations
✅ **Robust Room Lifecycle**
- Tracks daily lifecycle status (Available, Occupied, Maintenance) effectively.
- Provides specialized action endpoints enabling smooth guest Check-in and Check-out.
- Automates and maps out a 30-day default availability schedule upon room creation.

✅ **Capacity & Role Analytics**
- Extracts counts and details dynamically per `RoomType` (Single, Double, Deluxe).
- Produces aggregated profitability statistics and occupancy rates metrics globally or specifically grouped by type.

✅ **Comprehensive Audit History**
- Protects historical status states guaranteeing audit-friendly integrity through independent `RoomStatusHistory` records.

---

## 📚 API Documentation

### Interactive Swagger UI
The application makes use of SpringDoc OpenAPI for seamless API exploration:
`http://localhost:8082/swagger-ui.html`

---

## 🗄 Database Schema

### MySQL Schema

```sql
CREATE TABLE room (
    room_id VARCHAR(255) PRIMARY KEY,
    room_number VARCHAR(255) UNIQUE NOT NULL,
    room_type VARCHAR(255) NOT NULL,
    price_per_night DOUBLE NOT NULL,
    capacity INT NOT NULL,
    status VARCHAR(255) NOT NULL
);

CREATE TABLE room_status_history (
    id VARCHAR(255) PRIMARY KEY,
    room_id VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    changed_by VARCHAR(255) NOT NULL,
    changed_at DATETIME NOT NULL,
    CONSTRAINT fk_room FOREIGN KEY (room_id) REFERENCES room(room_id) ON DELETE CASCADE
);

CREATE TABLE room_type_entity (
    id VARCHAR(255) PRIMARY KEY,
    room_type VARCHAR(255) UNIQUE NOT NULL,
    price_per_night DOUBLE NOT NULL,
    image_path VARCHAR(255)
);
```

### Properties mapping
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hms
    username: root
    password: 1234
    driver-class-name: com.mysql.cj.jdbc.Driver
server:
  port: 8082
```

---

## 🚀 Deployment

### Frontend Deployment (React + Vite)
```bash
# 1. Navigate to frontend directory
cd frontend/room-management

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
cd backend/roomManagementService

# 2. Build via Maven
mvn clean install

# 3. Run application
mvn spring-boot:run
```

---

## 💻 Development Setup

### Frontend Setup
1. Ensure **Node.js** and **npm** are installed.
2. Navigate to `frontend/room-management`.
3. Run `npm install` to download required dependencies.
4. Run `npm run dev` to start the Vite development server.
5. Access the application in your browser (usually at `http://localhost:5173`).

### Backend Setup
1. Ensure **Java 21**, **Maven**, and **MySQL** are installed.
2. Clone repository and navigate to `backend/roomManagementService`.
3. Update Database Credentials inside `application.yaml` if needed.
4. Ensure your local MySQL instance is running and has a schema named `hms`.
5. Run `mvn spring-boot:run` or execute `RoomManagementServiceApplication.java` from your Java IDE.

---

**Status**: ✅ Implementation Available  
**Port**: `8082`
