package com.hotel.booking.service;

import com.hotel.booking.entity.Guest;
import com.hotel.booking.entity.Reservation;
import com.hotel.booking.entity.Room;
import com.hotel.booking.entity.dto.ReservationRequest;
import com.hotel.booking.entity.dto.ReservationResponse;
import com.hotel.booking.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomService roomService;
    private final GuestService guestService;

    public Reservation bookRoom(ReservationRequest request) {
        
        Room room = roomService.getRoomById(request.getRoomId());
        if (!room.isAvailable()) {
            throw new RuntimeException("Room is not available for booking.");
        }

        Guest guest = guestService.getGuestById(request.getGuestId());

        Reservation reservation = Reservation.builder()
                .room(room)
                .guest(guest)
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .status("BOOKED")
                .build();

        // Mark room unavailable
        room.setAvailable(false);
        roomService.saveRoom(room);

        return reservationRepository.save(reservation);
    }


    public List<ReservationResponse> getReservationsByGuest(Long guestId) {
        List<Reservation> reservations = reservationRepository.findByGuestId(guestId);

        return reservations.stream()
                .map(reservation -> new ReservationResponse(
                        reservation.getId(),
                        reservation.getCheckInDate(),
                        reservation.getCheckOutDate(),
                        reservation.getStatus(),
                        reservation.getRoom().getRoomNumber(),
                        reservation.getGuest().getFullName()
                ))
                .collect(Collectors.toList());
    }



    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setStatus("CANCELLED");

        Room room = reservation.getRoom();
        room.setAvailable(true);
        roomService.saveRoom(room);

        reservationRepository.save(reservation);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }
}
