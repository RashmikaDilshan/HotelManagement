import { LogOut, Users, Calendar, Package, ChefHat, BookOpen, UtensilsCrossed, Bed } from 'lucide-react';

// Service definitions with their URLs and icons
const SERVICES = {
  employee: {
    name: 'Employee Management',
    url: 'https://employee-management-nu-eight.vercel.app',
    description: 'Manage employee records, schedules, and payroll',
    icon: Users
  },
  event: {
    name: 'Event Management',
    url: 'https://event-management-frontend-mocha.vercel.app',
    description: 'Organize and manage hotel events and bookings',
    icon: Calendar
  },
  inventory: {
    name: 'Inventory Management',
    url: 'https://inventory-service-70b6b.web.app',
    description: 'Track and manage hotel inventory and supplies',
    icon: Package
  },
  kitchen: {
    name: 'Kitchen Management',
    url: 'https://hotel-management-systemk.vercel.app/',
    description: 'Manage kitchen operations and food preparation',
    icon: ChefHat
  },
  reservation: {
    name: 'Reservation Management',
    url: '#',
    description: 'Handle room reservations and bookings (Test Link)',
    icon: BookOpen
  },
  restaurant: {
    name: 'Restaurant Management',
    url: 'https://hotelmanagement-system.vercel.app/',
    description: 'Manage restaurant operations and orders',
    icon: UtensilsCrossed
  },
  room: {
    name: 'Room Management',
    url: '#',
    description: 'Manage room availability and maintenance (Test Link)',
    icon: Bed
  }
};

function Dashboard({ user, onLogout }) {
  const userServices = user.services.map(serviceKey => ({
    key: serviceKey,
    ...SERVICES[serviceKey]
  }));

  const handleServiceClick = (url) => {
    if (url !== '#') {
      window.open(url, '_blank');
    }
  };

  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <div className="header-left">
          <img src="/Hotel_Logo.png" alt="Hotel Logo" className="dashboard-logo" />
          <div className="user-info">
            <h1>
              Welcome, {user.fullName}
              <span className="role-badge">{user.role}</span>
            </h1>
            <p className="hotel-name">ආලකමන්දා Hotel Management System</p>
          </div>
        </div>
        <button onClick={onLogout} className="logout-button">
          <LogOut size={18} />
          Logout
        </button>
      </div>

      <div className="services-section">
        <h2>Your Available Services</h2>
        {userServices.length > 0 ? (
          <div className="services-grid">
            {userServices.map((service) => {
              const IconComponent = service.icon;
              return (
                <div
                  key={service.key}
                  className="service-card"
                  onClick={() => handleServiceClick(service.url)}
                >
                  <div className="service-icon">
                    <IconComponent size={30} />
                  </div>
                  <h3>{service.name}</h3>
                  <p>{service.description}</p>
                  <div className="service-link">
                    {service.url === '#' ? 'Coming Soon' : 'Open Service →'}
                  </div>
                </div>
              );
            })}
          </div>
        ) : (
          <div className="no-services">
            <p>No services available for your role</p>
          </div>
        )}
      </div>
    </div>
  );
}

export default Dashboard;
