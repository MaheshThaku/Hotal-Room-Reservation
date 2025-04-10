	package com.hotel.booking.controller;

import com.hotel.booking.entity.Reservation;
import com.hotel.booking.entity.dto.ReservationRequest;
import com.hotel.booking.entity.dto.ReservationResponse;
import com.hotel.booking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@CrossOrigin
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/book")
    public ResponseEntity<Reservation> bookRoom(@RequestBody ReservationRequest reservation) {
        return ResponseEntity.ok(reservationService.bookRoom(reservation));
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByGuest(@PathVariable Long guestId) {
        return ResponseEntity.ok(reservationService.getReservationsByGuest(guestId));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok("Reservation cancelled successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }
}
