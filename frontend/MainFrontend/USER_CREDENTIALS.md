# User Credentials and Role Access Matrix

## 🔐 Complete User Login Details

### 1. Admin
- **Username:** `admin`
- **Password:** `admin123`
- **Full Name:** System Administrator
- **Description:** Full system access for administrative tasks
- **Accessible Services:** ✅ All Services

### 2. Manager
- **Username:** `manager`
- **Password:** `manager123`
- **Full Name:** Hotel Manager
- **Description:** Complete operational access to manage the hotel
- **Accessible Services:** ✅ All Services

### 3. Receptionist
- **Username:** `receptionist`
- **Password:** `receptionist123`
- **Full Name:** Front Desk Receptionist
- **Description:** Front desk operations and guest management
- **Accessible Services:**
  - ✅ Employee Management
  - ✅ Reservation Management
  - ✅ Room Management

### 4. Room Manager
- **Username:** `roommanager`
- **Password:** `roommanager123`
- **Full Name:** Room Manager
- **Description:** Oversees room operations and maintenance
- **Accessible Services:**
  - ✅ Room Management
  - ✅ Inventory Management

### 5. Kitchen Manager
- **Username:** `kitchenmanager`
- **Password:** `kitchenmanager123`
- **Full Name:** Kitchen Manager
- **Description:** Manages kitchen operations and staff
- **Accessible Services:**
  - ✅ Kitchen Management
  - ✅ Restaurant Management
  - ✅ Inventory Management

### 6. Event Manager
- **Username:** `eventmanager`
- **Password:** `eventmanager123`
- **Full Name:** Event Manager
- **Description:** Plans and coordinates hotel events
- **Accessible Services:**
  - ✅ Event Management
  - ✅ Reservation Management

### 7. Chef
- **Username:** `chef`
- **Password:** `chef123`
- **Full Name:** Head Chef
- **Description:** Kitchen operations and food preparation
- **Accessible Services:**
  - ✅ Kitchen Management
  - ✅ Restaurant Management

### 8. Inventory Manager
- **Username:** `inventorymanager`
- **Password:** `inventory123`
- **Full Name:** Inventory Manager
- **Description:** Manages hotel supplies and inventory
- **Accessible Services:**
  - ✅ Inventory Management

### 9. Housekeeping Manager
- **Username:** `housekeeping`
- **Password:** `housekeeping123`
- **Full Name:** Housekeeping Manager
- **Description:** Oversees housekeeping staff and room cleanliness
- **Accessible Services:**
  - ✅ Room Management
  - ✅ Inventory Management

### 10. Security Manager
- **Username:** `security`
- **Password:** `security123`
- **Full Name:** Security Manager
- **Description:** Manages hotel security and safety protocols
- **Accessible Services:**
  - ✅ Employee Management
  - ✅ Event Management

### 11. Maintenance Manager
- **Username:** `maintenance`
- **Password:** `maintenance123`
- **Full Name:** Maintenance Manager
- **Description:** Handles maintenance and repairs
- **Accessible Services:**
  - ✅ Room Management
  - ✅ Inventory Management

### 12. Guest Relations Manager
- **Username:** `guestrelations`
- **Password:** `guest123`
- **Full Name:** Guest Relations Manager
- **Description:** Ensures excellent guest experience
- **Accessible Services:**
  - ✅ Reservation Management
  - ✅ Event Management
  - ✅ Restaurant Management

### 13. Accountant
- **Username:** `accountant`
- **Password:** `accountant123`
- **Full Name:** Senior Accountant
- **Description:** Handles financial operations and accounting
- **Accessible Services:**
  - ✅ Employee Management
  - ✅ Inventory Management

### 14. Restaurant Manager
- **Username:** `restaurantmanager`
- **Password:** `restaurant123`
- **Full Name:** Restaurant Manager
- **Description:** Manages restaurant operations
- **Accessible Services:**
  - ✅ Restaurant Management
  - ✅ Kitchen Management
  - ✅ Inventory Management

### 15. Banquet Manager
- **Username:** `banquetmanager`
- **Password:** `banquet123`
- **Full Name:** Banquet Manager
- **Description:** Coordinates banquet events and catering
- **Accessible Services:**
  - ✅ Event Management
  - ✅ Restaurant Management
  - ✅ Kitchen Management

### 16. Front Office Manager
- **Username:** `frontoffice`
- **Password:** `frontoffice123`
- **Full Name:** Front Office Manager
- **Description:** Oversees front office operations
- **Accessible Services:**
  - ✅ Reservation Management
  - ✅ Room Management
  - ✅ Employee Management

### 17. Supervisor
- **Username:** `supervisor`
- **Password:** `supervisor123`
- **Full Name:** Operations Supervisor
- **Description:** Supervises daily operations
- **Accessible Services:**
  - ✅ Employee Management
  - ✅ Room Management
  - ✅ Restaurant Management
  - ✅ Kitchen Management

---

## 📊 Service Access Matrix

| Role | Employee | Event | Inventory | Kitchen | Reservation | Restaurant | Room |
|------|----------|-------|-----------|---------|-------------|------------|------|
| **Admin** | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| **Manager** | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| **Receptionist** | ✅ | ❌ | ❌ | ❌ | ✅ | ❌ | ✅ |
| **Room Manager** | ❌ | ❌ | ✅ | ❌ | ❌ | ❌ | ✅ |
| **Kitchen Manager** | ❌ | ❌ | ✅ | ✅ | ❌ | ✅ | ❌ |
| **Event Manager** | ❌ | ✅ | ❌ | ❌ | ✅ | ❌ | ❌ |
| **Chef** | ❌ | ❌ | ❌ | ✅ | ❌ | ✅ | ❌ |
| **Inventory Manager** | ❌ | ❌ | ✅ | ❌ | ❌ | ❌ | ❌ |
| **Housekeeping Manager** | ❌ | ❌ | ✅ | ❌ | ❌ | ❌ | ✅ |
| **Security Manager** | ✅ | ✅ | ❌ | ❌ | ❌ | ❌ | ❌ |
| **Maintenance Manager** | ❌ | ❌ | ✅ | ❌ | ❌ | ❌ | ✅ |
| **Guest Relations Mgr** | ❌ | ✅ | ❌ | ❌ | ✅ | ✅ | ❌ |
| **Accountant** | ✅ | ❌ | ✅ | ❌ | ❌ | ❌ | ❌ |
| **Restaurant Manager** | ❌ | ❌ | ✅ | ✅ | ❌ | ✅ | ❌ |
| **Banquet Manager** | ❌ | ✅ | ❌ | ✅ | ❌ | ✅ | ❌ |
| **Front Office Manager** | ✅ | ❌ | ❌ | ❌ | ✅ | ❌ | ✅ |
| **Supervisor** | ✅ | ❌ | ❌ | ✅ | ❌ | ✅ | ✅ |

---

## 🔗 Service URLs

1. **Employee Management Service**
   - URL: https://employee-management-nu-eight.vercel.app
   - Status: ✅ Live

2. **Event Management**
   - URL: https://event-management-frontend-mocha.vercel.app
   - Status: ✅ Live

3. **Inventory Management**
   - URL: https://inventory-service-70b6b.web.app
   - Status: ✅ Live

4. **Kitchen Management**
   - URL: https://hotel-management-systemk.vercel.app/
   - Status: ✅ Live

5. **Reservation Management**
   - URL: Test Link
   - Status: ⏳ Coming Soon

6. **Restaurant Management**
   - URL: https://hotelmanagement-system.vercel.app/
   - Status: ✅ Live

7. **Room Management**
   - URL: Test Link
   - Status: ⏳ Coming Soon

---

## 🎯 Testing Guide

### Test Scenario 1: Admin Access
1. Login with: `admin` / `admin123`
2. Expected: See all 7 service buttons
3. Click any service button to open in new tab

### Test Scenario 2: Chef Access
1. Login with: `chef` / `chef123`
2. Expected: See only Kitchen Management and Restaurant Management
3. Click buttons to verify access

### Test Scenario 3: Receptionist Access
1. Login with: `receptionist` / `receptionist123`
2. Expected: See Employee, Reservation, and Room Management
3. Verify other services are not visible

### Test Scenario 4: Invalid Login
1. Try: `wronguser` / `wrongpass`
2. Expected: Error message "Invalid username or password"

---

## 📱 Quick Reference Card

```
┌──────────────────────────────────────────────────────────┐
│     ĀLAKAMAṆḌĀ HOTEL - MANAGEMENT SYSTEM LOGIN          │
├──────────────────────────────────────────────────────────┤
│  Role                  │ Username         │ Password     │
├────────────────────────┼──────────────────┼──────────────┤
│  Admin                 │ admin            │ admin123     │
│  Manager               │ manager          │ manager123   │
│  Receptionist          │ receptionist     │ receptionist123 │
│  Room Manager          │ roommanager      │ roommanager123 │
│  Kitchen Manager       │ kitchenmanager   │ kitchenmanager123 │
│  Event Manager         │ eventmanager     │ eventmanager123 │
│  Chef                  │ chef             │ chef123      │
│  Inventory Manager     │ inventorymanager │ inventory123 │
│  Housekeeping Manager  │ housekeeping     │ housekeeping123 │
│  Security Manager      │ security         │ security123  │
│  Maintenance Manager   │ maintenance      │ maintenance123 │
│  Guest Relations Mgr   │ guestrelations   │ guest123     │
│  Accountant            │ accountant       │ accountant123 │
│  Restaurant Manager    │ restaurantmanager│ restaurant123 │
│  Banquet Manager       │ banquetmanager   │ banquet123   │
│  Front Office Manager  │ frontoffice      │ frontoffice123 │
│  Supervisor            │ supervisor       │ supervisor123 │
└──────────────────────────────────────────────────────────┘
```

---

## 🔄 Adding New Users

To add a new user, edit `src/pages/Login.jsx`:

```javascript
const USERS = {
  newusername: {
    password: 'newpassword',
    role: 'Role Title',
    fullName: 'Full Display Name',
    services: ['service1', 'service2'] // Service keys
  }
};
```

Available service keys:
- `employee` - Employee Management
- `event` - Event Management
- `inventory` - Inventory Management
- `kitchen` - Kitchen Management
- `reservation` - Reservation Management
- `restaurant` - Restaurant Management
- `room` - Room Management

---

**Document Version:** 1.0  
**Last Updated:** March 2026  
**System:** Hotel Management System - Main Frontend
