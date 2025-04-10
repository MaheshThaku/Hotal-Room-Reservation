package com.hotel.booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.booking.entity.Guest;
import com.hotel.booking.entity.dto.GuestLoginRequest;
import com.hotel.booking.entity.dto.GuestLoginResponse;
import com.hotel.booking.entity.dto.GuestRequest;
import com.hotel.booking.entity.dto.GuestResponse;
import com.hotel.booking.entity.dto.ReservationResponse;
import com.hotel.booking.exception.ResourceAlreadyExistsException;
import com.hotel.booking.service.GuestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/guests")
@RequiredArgsConstructor
@CrossOrigin
public class GuestController {

    private final GuestService guestService;

    @PostMapping("/register")
    public ResponseEntity<GuestResponse> registerGuest(@RequestBody GuestRequest guest) throws ResourceAlreadyExistsException {
        return ResponseEntity.ok(guestService.registerGuest(guest));
    }
    @PostMapping("/login")
    public ResponseEntity<GuestLoginResponse> login(@RequestBody GuestLoginRequest request) {
        return ResponseEntity.ok(guestService.login(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Long id) {
        return ResponseEntity.ok(guestService.getGuestById(id));
    }

    @GetMapping("/get-profile-by-email")
    public ResponseEntity<Guest> getGuestByEmail(@RequestParam String email) {
        return ResponseEntity.ok(guestService.getGuestByEmail(email));
    }
    
    @GetMapping("/get-reservation-list-by-email")
    public ResponseEntity<List<ReservationResponse>> getReservationListByEmail(@RequestParam String email) {
        return ResponseEntity.ok(guestService.getReservationsByEmail(email));
    }
}
