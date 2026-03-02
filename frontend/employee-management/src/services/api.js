import axios from 'axios';

const API_BASE_URL = 'http://localhost:8085/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor to add JWT token
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor to handle 401 errors
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // Token expired or invalid
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export const employeeService = {
  // Get all employees
  getAllEmployees: () => api.get('/employees'),
  
  // Get employee by ID
  getEmployeeById: (id) => api.get(`/employees/${id}`),
  
  // Create new employee
  createEmployee: (employeeData) => api.post('/employees', employeeData),
  
  // Update employee
  updateEmployee: (id, employeeData) => api.put(`/employees/${id}`, employeeData),
  
  // Delete employee
  deleteEmployee: (id) => api.delete(`/employees/${id}`),
  
  // Update employee status
  updateEmployeeStatus: (id, status) => api.patch(`/employees/${id}/status`, null, {
    params: { status }
  }),
  
  // Search employees by name
  searchEmployees: (name) => api.get('/employees/search', {
    params: { name }
  }),
  
  // Get employees by department
  getEmployeesByDepartment: (department) => api.get(`/employees/department/${department}`),
  
  // Get employees by status
  getEmployeesByStatus: (status) => api.get(`/employees/status/${status}`),
  
  // Get statistics
  getStatistics: () => api.get('/employees/statistics'),
  
  // Get department statistics
  getDepartmentStatistics: () => api.get('/employees/statistics/departments'),
  
  // Get employee count
  getEmployeeCount: () => api.get('/employees/count'),
  
  // Get active employee count
  getActiveEmployeeCount: () => api.get('/employees/count/active'),
};

export default api;
