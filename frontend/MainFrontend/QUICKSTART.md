# 🚀 Quick Start Guide

## Get Started in 3 Minutes!

### Step 1: Install Dependencies
```bash
cd frontend/MainFrontend
npm install
```

### Step 2: Run Development Server
```bash
npm run dev
```
Open http://localhost:5173 in your browser

### Step 3: Login & Test

Try logging in with:
- **Admin:** `admin` / `admin123`
- **Manager:** `manager` / `manager123`
- **Chef:** `chef` / `chef123`

---

## 📋 All User Credentials

| Username | Password | Role | Services Available |
|----------|----------|------|-------------------|
| admin | admin123 | Admin | All (7 services) |
| manager | manager123 | Manager | All (7 services) |
| receptionist | receptionist123 | Receptionist | 3 services |
| roommanager | roommanager123 | Room Manager | 2 services |
| kitchenmanager | kitchenmanager123 | Kitchen Manager | 3 services |
| eventmanager | eventmanager123 | Event Manager | 2 services |
| chef | chef123 | Chef | 2 services |
| inventorymanager | inventory123 | Inventory Manager | 1 service |
| housekeeping | housekeeping123 | Housekeeping Manager | 2 services |
| security | security123 | Security Manager | 2 services |
| maintenance | maintenance123 | Maintenance Manager | 2 services |
| guestrelations | guest123 | Guest Relations Manager | 3 services |
| accountant | accountant123 | Accountant | 2 services |
| restaurantmanager | restaurant123 | Restaurant Manager | 3 services |
| banquetmanager | banquet123 | Banquet Manager | 3 services |
| frontoffice | frontoffice123 | Front Office Manager | 3 services |
| supervisor | supervisor123 | Supervisor | 4 services |

**Total Users: 17 Different Roles**

---

## 🌐 Deploy to Vercel

### Option A: Vercel CLI
```bash
npm install -g vercel
vercel login
vercel
```

### Option B: GitHub + Vercel Dashboard
1. Push code to GitHub
2. Go to vercel.com
3. Import repository
4. Set root: `frontend/MainFrontend`
5. Deploy!

---

## 📁 Project Structure

```
MainFrontend/
├── src/
│   ├── pages/
│   │   ├── Login.jsx       # Login page with authentication
│   │   └── Dashboard.jsx   # Role-based dashboard
│   ├── App.jsx             # Main app routing
│   ├── main.jsx            # Entry point
│   └── index.css           # Global styles
├── public/
│   └── hotel-icon.svg      # Hotel icon
├── package.json            # Dependencies
├── vite.config.js          # Vite configuration
├── vercel.json             # Vercel deployment config
├── README.md               # Full documentation
├── USER_CREDENTIALS.md     # Complete credentials list
└── DEPLOYMENT.md           # Deployment guide
```

---

## ✨ Features

✅ Static authentication (no backend needed)  
✅ 17 different user roles with role-based access  
✅ Responsive design (mobile, tablet, desktop)  
✅ Modern UI with smooth animations  
✅ Unsplash background imagery  
✅ Glass morphism effects  
✅ Animated service cards  
✅ Direct links to microservices  
✅ Easy Vercel deployment  
✅ Secure password handling  
✅ Error handling & validation  
✅ Custom hotel branding (ආලකමන්දා Hotel)  
✅ Professional gradient themes  

---

## 🎨 Customization

### Change Colors
Edit `src/index.css` - look for gradient definitions:
```css
background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
```

### Add New User
Edit `src/pages/Login.jsx` - add to USERS object

### Add New Service
Edit `src/pages/Dashboard.jsx` - add to SERVICES object

---

## 📞 Need Help?

- 📖 Full details: See `README.md`
- 🔐 Credentials: See `USER_CREDENTIALS.md`
- 🚀 Deployment: See `DEPLOYMENT.md`

---

## 🧪 Quick Test

1. **Test Admin Login:**
   - Login: admin / admin123
   - Should see: All 7 services

2. **Test Chef Login:**
   - Login: chef / chef123
   - Should see: Only Kitchen & Restaurant

3. **Test Invalid Login:**
   - Login: wrong / wrong
   - Should see: Error message

---

**Ready to Deploy?** Run `vercel` from the MainFrontend directory!

**Last Updated:** March 2026
