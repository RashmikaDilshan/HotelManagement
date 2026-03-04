package com.nsbm.group03.reservationManagementService.service;

import com.nsbm.group03.reservationManagementService.dto.ReservationDTO;
import com.nsbm.group03.reservationManagementService.entity.Guest;
import com.nsbm.group03.reservationManagementService.entity.Reservation;
import com.nsbm.group03.reservationManagementService.repository.GuestRepository;
import com.nsbm.group03.reservationManagementService.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Save Reservation
    public ReservationDTO saveReservation(ReservationDTO reservationDTO) {

        Guest guest = guestRepository.findById(reservationDTO.getGuestId())
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        reservation.setGuest(guest);
        reservation.setCreatedAt(LocalDateTime.now());

        reservationRepository.save(reservation);

        return modelMapper.map(reservation, ReservationDTO.class);
    }

    // Get All Reservations
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> list = reservationRepository.findAll();
        return modelMapper.map(list, new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    // Get Reservations By Guest
    public List<ReservationDTO> getReservationsByGuest(Long guestId) {
        List<Reservation> list = reservationRepository.findByGuest_GuestId(guestId);
        return modelMapper.map(list, new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    // Update Status
    public ReservationDTO updateStatus(Long id, String status) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setStatus(status.toUpperCase());
        reservationRepository.save(reservation);

        return modelMapper.map(reservation, ReservationDTO.class);
    }

    // Delete Reservation
    public boolean deleteReservation(Long id) {
        reservationRepository.deleteById(id);
        return true;
    }
}