# Hotel Management System - Main Frontend

This is the main login portal for the Hotel Management System. It provides role-based authentication and access to various hotel management services.

## 🚀 Features

- Static authentication (no backend required)
- 17 different user roles with role-based access control
- Modern and responsive UI with stunning animations
- Beautiful Unsplash background images
- Smooth transitions and hover effects
- Glass morphism design elements
- Direct links to microservices
- Easy to deploy on Vercel
- Custom hotel branding with logo support
- Animated service cards with staggered appearance
- Professional gradient designs

## 📋 User Roles and Credentials

### Admin
- **Username:** `admin`
- **Password:** `admin123`
- **Access:** All Services (Full Access)
  - Employee Management
  - Event Management
  - Inventory Management
  - Kitchen Management
  - Reservation Management
  - Restaurant Management
  - Room Management

### Manager
- **Username:** `manager`
- **Password:** `manager123`
- **Access:** All Services (Full Access)
  - Employee Management
  - Event Management
  - Inventory Management
  - Kitchen Management
  - Reservation Management
  - Restaurant Management
  - Room Management

### Receptionist
- **Username:** `receptionist`
- **Password:** `receptionist123`
- **Access:** Front Desk Services
  - Employee Management
  - Reservation Management
  - Room Management

### Room Manager
- **Username:** `roommanager`
- **Password:** `roommanager123`
- **Access:** Room Operations
  - Room Management
  - Inventory Management

### Kitchen Manager
- **Username:** `kitchenmanager`
- **Password:** `kitchenmanager123`
- **Access:** Kitchen & Restaurant Operations
  - Kitchen Management
  - Restaurant Management
  - Inventory Management

### Event Manager
- **Username:** `eventmanager`
- **Password:** `eventmanager123`
- **Access:** Event Planning Services
  - Event Management
  - Reservation Management

### Chef
- **Username:** `chef`
- **Password:** `chef123`
- **Access:** Kitchen Services
  - Kitchen Management
  - Restaurant Management

### Inventory Manager
- **Username:** `inventorymanager`
- **Password:** `inventory123`
- **Access:** Inventory Services
  - Inventory Management

### Housekeeping Manager
- **Username:** `housekeeping`
- **Password:** `housekeeping123`
- **Access:** Room & Inventory Services
  - Room Management
  - Inventory Management

### Security Manager
- **Username:** `security`
- **Password:** `security123`
- **Access:** Employee & Event Services
  - Employee Management
  - Event Management

### Maintenance Manager
- **Username:** `maintenance`
- **Password:** `maintenance123`
- **Access:** Room & Inventory Services
  - Room Management
  - Inventory Management

### Guest Relations Manager
- **Username:** `guestrelations`
- **Password:** `guest123`
- **Access:** Guest Services
  - Reservation Management
  - Event Management
  - Restaurant Management

### Accountant
- **Username:** `accountant`
- **Password:** `accountant123`
- **Access:** Financial Services
  - Employee Management
  - Inventory Management

### Restaurant Manager
- **Username:** `restaurantmanager`
- **Password:** `restaurant123`
- **Access:** Food Services
  - Restaurant Management
  - Kitchen Management
  - Inventory Management

### Banquet Manager
- **Username:** `banquetmanager`
- **Password:** `banquet123`
- **Access:** Banquet Services
  - Event Management
  - Restaurant Management
  - Kitchen Management

### Front Office Manager
- **Username:** `frontoffice`
- **Password:** `frontoffice123`
- **Access:** Front Desk Operations
  - Reservation Management
  - Room Management
  - Employee Management

### Supervisor
- **Username:** `supervisor`
- **Password:** `supervisor123`
- **Access:** Operations Services
  - Employee Management
  - Room Management
  - Restaurant Management
  - Kitchen Management

**Total: 17 Different User Roles**

## 🔗 Integrated Services

- **Employee Management Service:** https://employee-management-nu-eight.vercel.app
- **Event Management:** https://event-management-frontend-mocha.vercel.app
- **Inventory Management:** https://inventory-service-70b6b.web.app
- **Kitchen Management:** https://hotel-management-systemk.vercel.app/
- **Reservation Management:** Test Link (Coming Soon)
- **Restaurant Management:** https://hotelmanagement-system.vercel.app/
- **Room Management:** Test Link (Coming Soon)

## 💻 Local Development

### Prerequisites
- Node.js (v18 or higher)
- npm or yarn

### Installation

1. Navigate to the MainFrontend folder:
```bash
cd frontend/MainFrontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```

4. Open your browser and navigate to `http://localhost:5173`

### Build for Production

```bash
npm run build
```

The built files will be in the `dist` folder.

## 🌐 Deploy to Vercel

### Option 1: Deploy via Vercel CLI

1. Install Vercel CLI:
```bash
npm install -g vercel
```

2. Login to Vercel:
```bash
vercel login
```

3. Deploy:
```bash
vercel
```

### Option 2: Deploy via Vercel Dashboard

1. Push your code to GitHub
2. Visit [Vercel Dashboard](https://vercel.com/dashboard)
3. Click "Add New Project"
4. Import your GitHub repository
5. Set the root directory to `frontend/MainFrontend`
6. Click "Deploy"

### Vercel Configuration

The project includes a `vercel.json` file that configures the routing for the single-page application. No additional configuration is needed.

## 🎨 Customization

### Adding New Users

Edit the `USERS` object in `src/pages/Login.jsx`:

```javascript
const USERS = {
  newuser: {
    password: 'password123',
    role: 'Role Name',
    fullName: 'Full Name',
    services: ['service1', 'service2'] // Array of service keys
  }
};
```

### Adding New Services

Edit the `SERVICES` object in `src/pages/Dashboard.jsx`:

```javascript
const SERVICES = {
  newservice: {
    name: 'Service Name',
    url: 'https://service-url.com',
    description: 'Service description',
    icon: IconComponent // Import from lucide-react
  }
};
```

## 📱 Responsive Design

The application is fully responsive and works on:
- Desktop (1200px and above)
- Tablet (768px - 1199px)
- Mobile (below 768px)

## 🔒 Security Note

This application uses static credentials for demonstration purposes. In a production environment, you should implement proper authentication with a backend service, secure password hashing, and token-based authentication.

## 🛠️ Tech Stack

- **React 19.2.0** - UI Library
- **Vite** - Build Tool
- **React Router DOM** - Routing
- **Lucide React** - Icons
- **CSS3** - Styling

## 📄 License

This project is part of the Hotel Management System microservices architecture.

## 👥 Support

For any issues or questions, please contact the development team.

---

**Last Updated:** March 2026
