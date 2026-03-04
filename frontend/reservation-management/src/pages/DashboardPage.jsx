import React, { useState, useEffect } from 'react';
import { getAllRooms } from '../services/api';
import { useNavigate } from 'react-router-dom';
import HotelLogo from "../assets/Hotel_Logo.png";
const DashboardPage = () => {
    const [rooms, setRooms] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        fetchRooms();
    }, []);

    const fetchRooms = async () => {
        try {
            const response = await getAllRooms();
            setRooms(response.data);
        } catch (error) {
            console.error('Error fetching rooms:', error);
        } finally {
            setLoading(false);
        }
    };

    const stats = {
        available: rooms.filter(r => r.status === 'AVAILABLE').length,
        occupied: rooms.filter(r => r.status === 'OCCUPIED').length,
        maintenance: rooms.filter(r => r.status === 'MAINTENANCE').length,
    };

    const getStatusColor = (status) => {
        switch (status) {
            case 'AVAILABLE': return 'status-green';
            case 'OCCUPIED': return 'status-red';
            case 'MAINTENANCE': return 'status-yellow';
            default: return 'slate-400';
        }
    };

    const handleBookNow = (room) => {
        navigate('/reservation', {
            state: {
                roomNumber: room.roomNumber,
                roomType: room.roomType,
                pricePerNight: room.pricePerNight
            }
        });
    };

    return (
        <div className="flex h-screen overflow-hidden bg-background">
            <main className="flex-1 flex flex-col overflow-hidden">
                <header className="h-16 border-b border-border bg-white flex items-center justify-between px-8">
                    <div className="flex items-center gap-4 flex-1 max-w-xl">
                        <img src={HotelLogo} alt="Hotel Logo" className="h-20 w-auto object-contain" />
                        <h2 className="text-primary text-lg font-bold">Reservation Management</h2>
                    </div>
                    <button
                        onClick={() => navigate('/reservation')}
                        className="flex items-center gap-2 bg-primary text-white px-4 py-2 rounded-lg text-sm font-semibold hover:bg-primary-dark transition-colors"
                    >
                        <span className="material-symbols-outlined text-sm">add</span>
                        <span>New Booking</span>
                    </button>
                </header>

                <div className="flex-1 overflow-y-auto p-8">
                    <div className="mb-8">
                        <h2 className="text-3xl font-extrabold text-slate-900 tracking-tight">Dashboard Overview</h2>
                        <p className="text-text-secondary mt-1">Real-time hotel status and room availability summary.</p>
                    </div>

                    {/* KPI Cards */}
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">
                        <div className="bg-white p-6 rounded-xl border border-border shadow-sm">
                            <div className="flex items-center justify-between mb-4">
                                <span className="p-2 bg-status-green/10 text-status-green rounded-lg material-symbols-outlined">meeting_room</span>
                            </div>
                            <p className="text-text-secondary text-sm font-medium">Available Rooms</p>
                            <p className="text-3xl font-bold text-slate-900 mt-1">{stats.available}</p>
                        </div>
                        <div className="bg-white p-6 rounded-xl border border-border shadow-sm">
                            <div className="flex items-center justify-between mb-4">
                                <span className="p-2 bg-status-red/10 text-status-red rounded-lg material-symbols-outlined">person_pin_circle</span>
                            </div>
                            <p className="text-text-secondary text-sm font-medium">Occupied Rooms</p>
                            <p className="text-3xl font-bold text-slate-900 mt-1">{stats.occupied}</p>
                        </div>
                        <div className="bg-white p-6 rounded-xl border border-border shadow-sm">
                            <div className="flex items-center justify-between mb-4">
                                <span className="p-2 bg-status-yellow/10 text-status-yellow rounded-lg material-symbols-outlined">cleaning_services</span>
                            </div>
                            <p className="text-text-secondary text-sm font-medium">Maintenance</p>
                            <p className="text-3xl font-bold text-slate-900 mt-1">{stats.maintenance}</p>
                        </div>
                    </div>

                    {/* Floor Plan */}
                    <div className="bg-white rounded-xl border border-border shadow-sm p-6 mb-8">
                        <div className="flex items-center justify-between mb-8">
                            <div>
                                <h3 className="text-xl font-bold text-slate-900">Room Status Floor Plan</h3>
                                <p className="text-sm text-text-secondary">All Floors Overview</p>
                            </div>
                            <div className="flex items-center gap-4 text-xs font-medium">
                                <div className="flex items-center gap-2">
                                    <span className="w-3 h-3 rounded-full bg-status-green"></span>
                                    <span className="text-text-secondary">Available</span>
                                </div>
                                <div className="flex items-center gap-2">
                                    <span className="w-3 h-3 rounded-full bg-status-red"></span>
                                    <span className="text-text-secondary">Occupied</span>
                                </div>
                                <div className="flex items-center gap-2">
                                    <span className="w-3 h-3 rounded-full bg-status-yellow"></span>
                                    <span className="text-text-secondary">Maintenance</span>
                                </div>
                            </div>
                        </div>
                        <div className="grid grid-cols-5 md:grid-cols-8 lg:grid-cols-10 gap-4">
                            {rooms.map(room => (
                                <div
                                    key={room.roomNumber}
                                    onClick={() => room.status === 'AVAILABLE' && handleBookNow(room)}
                                    className={`aspect-square bg-${getStatusColor(room.status)}/10 border-2 border-${getStatusColor(room.status)}/30 rounded-lg flex flex-col items-center justify-center cursor-pointer hover:bg-${getStatusColor(room.status)}/20 transition-all`}
                                >
                                    <span className={`text-sm font-bold text-${getStatusColor(room.status)}`}>{room.roomNumber}</span>
                                </div>
                            ))}
                            {rooms.length === 0 && <p className="col-span-full text-center text-text-secondary py-4">No rooms found.</p>}
                        </div>
                    </div>

                    {/* Room Cards (Available Only) */}
                    <div className="mb-6">
                        <h3 className="text-xl font-bold text-slate-900 mb-4">Non-Reserved Rooms</h3>
                        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
                            {rooms.filter(r => r.status === 'AVAILABLE').map(room => (
                                <div key={room.roomNumber} className="bg-white rounded-xl border border-border overflow-hidden group hover:shadow-xl transition-all">
                                    <div className="h-48 relative overflow-hidden bg-slate-100 flex items-center justify-center">
                                        <span className="material-symbols-outlined text-slate-300 text-5xl">bed</span>
                                        <div className="absolute top-4 right-4 px-2 py-1 bg-emerald-500 text-white text-[10px] font-bold rounded uppercase">Ready</div>
                                    </div>
                                    <div className="p-5">
                                        <div className="flex justify-between items-start mb-2">
                                            <div>
                                                <h3 className="font-bold text-slate-900">{room.roomType}</h3>
                                                <p className="text-xs text-text-secondary">Room {room.roomNumber}</p>
                                            </div>
                                            <span className="text-primary font-bold">Rs{room.pricePerNight}<span className="text-xs text-text-secondary font-medium">/nt</span></span>
                                        </div>
                                        <div className="flex gap-4 mb-6">
                                            <div className="flex items-center gap-1 text-text-secondary">
                                                <span className="material-symbols-outlined !text-base">person</span>
                                                <span className="text-xs font-medium">{room.capacity} Guests</span>
                                            </div>
                                        </div>
                                        <button
                                            onClick={() => handleBookNow(room)}
                                            className="w-full py-2.5 bg-primary text-white rounded-lg text-sm font-bold flex items-center justify-center gap-2 hover:bg-primary-dark transition-colors"
                                        >
                                            Book Now
                                        </button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
};

export default DashboardPage;
