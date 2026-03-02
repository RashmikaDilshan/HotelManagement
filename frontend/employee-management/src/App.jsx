import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { AuthProvider } from './context/AuthContext';
import ProtectedRoute from './components/ProtectedRoute';
import Layout from './components/Layout';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import EmployeeList from './pages/EmployeeList';
import EmployeeDetails from './pages/EmployeeDetails';
import AddEmployee from './pages/AddEmployee';
import EditEmployee from './pages/EditEmployee';
import Statistics from './pages/Statistics';
import './App.css';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      retry: 1,
    },
  },
});

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <Router>
        <AuthProvider>
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/" element={
              <ProtectedRoute>
                <Layout>
                  <Dashboard />
                </Layout>
              </ProtectedRoute>
            } />
            <Route path="/employees" element={
              <ProtectedRoute>
                <Layout>
                  <EmployeeList />
                </Layout>
              </ProtectedRoute>
            } />
            <Route path="/employees/:id" element={
              <ProtectedRoute>
                <Layout>
                  <EmployeeDetails />
                </Layout>
              </ProtectedRoute>
            } />
            <Route path="/employees/add" element={
              <ProtectedRoute>
                <Layout>
                  <AddEmployee />
                </Layout>
              </ProtectedRoute>
            } />
            <Route path="/employees/edit/:id" element={
              <ProtectedRoute>
                <Layout>
                  <EditEmployee />
                </Layout>
              </ProtectedRoute>
            } />
            <Route path="/statistics" element={
              <ProtectedRoute>
                <Layout>
                  <Statistics />
                </Layout>
              </ProtectedRoute>
            } />
          </Routes>
        </AuthProvider>
      </Router>
    </QueryClientProvider>
  );
}

export default App;
