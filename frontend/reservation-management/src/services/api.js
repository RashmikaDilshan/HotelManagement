import axios from 'axios';

const reservationApi = axios.create({
  baseURL: 'http://localhost:8081/api',
});

const roomApi = axios.create({
  baseURL: 'http://localhost:8082/api',
});

// Reservation Service Endpoints
export const saveGuest = (guestData) => reservationApi.post('/v1/guest/saveguest', guestData);
export const saveReservation = (reservationData) => reservationApi.post('/reservations', reservationData);
export const getAllReservations = () => reservationApi.get('/reservations');
export const updateReservationStatus = (id, status) => reservationApi.patch(`/reservations/${id}/status?status=${status}`);
export const findGuestByPhoneNumber = (phoneNumber) => reservationApi.get(`/v1/guest/findGuestByPhoneNumber/${phoneNumber}`);

// Room Service Endpoints
export const getAllRooms = () => roomApi.get('/rooms');
export const getAvailableRooms = () => reservationApi.get('/reservations/available-rooms');

export default { reservationApi, roomApi };
