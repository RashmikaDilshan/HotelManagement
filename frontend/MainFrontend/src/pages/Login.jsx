import { useState } from 'react';
import { LogIn } from 'lucide-react';

// Static user credentials with roles and their accessible services
const USERS = {
  admin: {
    password: 'admin123',
    role: 'Admin',
    fullName: 'System Administrator',
    services: ['employee', 'event', 'inventory', 'kitchen', 'reservation', 'restaurant', 'room']
  },
  manager: {
    password: 'manager123',
    role: 'Manager',
    fullName: 'Hotel Manager',
    services: ['employee', 'event', 'inventory', 'kitchen', 'reservation', 'restaurant', 'room']
  },
  receptionist: {
    password: 'receptionist123',
    role: 'Receptionist',
    fullName: 'Front Desk Receptionist',
    services: ['employee', 'reservation', 'room']
  },
  roommanager: {
    password: 'roommanager123',
    role: 'Room Manager',
    fullName: 'Room Manager',
    services: ['room', 'inventory']
  },
  kitchenmanager: {
    password: 'kitchenmanager123',
    role: 'Kitchen Manager',
    fullName: 'Kitchen Manager',
    services: ['kitchen', 'restaurant', 'inventory']
  },
  eventmanager: {
    password: 'eventmanager123',
    role: 'Event Manager',
    fullName: 'Event Manager',
    services: ['event', 'reservation']
  },
  chef: {
    password: 'chef123',
    role: 'Chef',
    fullName: 'Head Chef',
    services: ['kitchen', 'restaurant']
  },
  inventorymanager: {
    password: 'inventory123',
    role: 'Inventory Manager',
    fullName: 'Inventory Manager',
    services: ['inventory']
  },
  housekeeping: {
    password: 'housekeeping123',
    role: 'Housekeeping Manager',
    fullName: 'Housekeeping Manager',
    services: ['room', 'inventory']
  },
  security: {
    password: 'security123',
    role: 'Security Manager',
    fullName: 'Security Manager',
    services: ['employee', 'event']
  },
  maintenance: {
    password: 'maintenance123',
    role: 'Maintenance Manager',
    fullName: 'Maintenance Manager',
    services: ['room', 'inventory']
  },
  guestrelations: {
    password: 'guest123',
    role: 'Guest Relations Manager',
    fullName: 'Guest Relations Manager',
    services: ['reservation', 'event', 'restaurant']
  },
  accountant: {
    password: 'accountant123',
    role: 'Accountant',
    fullName: 'Senior Accountant',
    services: ['employee', 'inventory']
  },
  restaurantmanager: {
    password: 'restaurant123',
    role: 'Restaurant Manager',
    fullName: 'Restaurant Manager',
    services: ['restaurant', 'kitchen', 'inventory']
  },
  banquetmanager: {
    password: 'banquet123',
    role: 'Banquet Manager',
    fullName: 'Banquet Manager',
    services: ['event', 'restaurant', 'kitchen']
  },
  frontoffice: {
    password: 'frontoffice123',
    role: 'Front Office Manager',
    fullName: 'Front Office Manager',
    services: ['reservation', 'room', 'employee']
  },
  supervisor: {
    password: 'supervisor123',
    role: 'Supervisor',
    fullName: 'Operations Supervisor',
    services: ['employee', 'room', 'restaurant', 'kitchen']
  }
};

function Login({ onLogin }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    setError('');

    const user = USERS[username.toLowerCase()];

    if (!user) {
      setError('Invalid username or password');
      return;
    }

    if (user.password !== password) {
      setError('Invalid username or password');
      return;
    }

    onLogin({
      username: username.toLowerCase(),
      ...user
    });
  };

  return (
    <div className="login-container">
      <div className="login-card">
        <div className="login-header">
          <img src="/Hotel_Logo.png" alt="Hotel Logo" className="hotel-logo" />
          <h1>ආලකමන්දා Hotel</h1>
          <p className="subtitle">Management System</p>
          <p>Please login to access your dashboard</p>
        </div>

        <form onSubmit={handleSubmit} className="login-form">
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              placeholder="Enter your username"
              required
              autoComplete="username"
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter your password"
              required
              autoComplete="current-password"
            />
          </div>

          {error && <div className="error-message">{error}</div>}

          <button type="submit" className="login-button">
            <LogIn size={20} style={{ display: 'inline', marginRight: '8px' }} />
            Login
          </button>
        </form>
      </div>
    </div>
  );
}

export default Login;
