import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import DashboardPage from './pages/DashboardPage';
import CreateReservationPage from './pages/CreateReservationPage';
import ViewReservationsPage from './pages/ViewReservationsPage';

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-background">
        <Routes>
          <Route path="/" element={<DashboardPage />} />
          <Route path="/reservation" element={<CreateReservationPage />} />
          <Route path="/reservations" element={<ViewReservationsPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
