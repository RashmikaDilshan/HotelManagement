import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import DashboardPage from './pages/DashboardPage';
import CreateReservationPage from './pages/CreateReservationPage';

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-background">
        <Routes>
          <Route path="/" element={<DashboardPage />} />
          <Route path="/reservation" element={<CreateReservationPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
