package com.hotel.booking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.booking.entity.Guest;
import com.hotel.booking.entity.Reservation;
import com.hotel.booking.entity.dto.GuestLoginRequest;
import com.hotel.booking.entity.dto.GuestLoginResponse;
import com.hotel.booking.entity.dto.GuestRequest;
import com.hotel.booking.entity.dto.GuestResponse;
import com.hotel.booking.entity.dto.ReservationResponse;
import com.hotel.booking.exception.ResourceAlreadyExistsException;
import com.hotel.booking.repository.GuestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final PasswordEncoder passwordEncoder;  // BCrypt
    
    public GuestResponse registerGuest(GuestRequest guestRequest) throws ResourceAlreadyExistsException{

    	 Guest existingGuest = guestRepository.findByEmail(guestRequest.getEmail())
    	            .orElse(null);

    	    if (existingGuest != null) {
    	        throw new ResourceAlreadyExistsException("Email already registered: " + guestRequest.getEmail());
    	    }

        Guest guest = Guest.builder()
                .fullName(guestRequest.getFullName())
                .email(guestRequest.getEmail())
                .phone(guestRequest.getPhone())
                .password(passwordEncoder.encode(guestRequest.getPassword()))
                .build();

        Guest savedGuest = guestRepository.save(guest);

        return new GuestResponse(
                savedGuest.getId(),
                savedGuest.getFullName(),
                savedGuest.getEmail(),
                savedGuest.getPhone()
        );
    }



    public Guest getGuestByEmail(String email) {
        return guestRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Guest not found with email: " + email));
    }

    public Guest getGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));
    }


    public GuestLoginResponse login(GuestLoginRequest request) {

        Guest guest = guestRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Guest not found with email : " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), guest.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return new GuestLoginResponse(guest.getId(),guest.getFullName(), guest.getEmail(),guest.getPassword());
    }
    
    public List<ReservationResponse> getReservationsByEmail(String email) {
        Guest byEmail = guestRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Guest not found with email: " + email));

        return byEmail.getReservations()
                .stream()
                .map(this::mapToReservationResponse)
                .collect(Collectors.toList());
    }

    private ReservationResponse mapToReservationResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate(),
                reservation.getStatus(),
                reservation.getRoom().getRoomNumber(),
                reservation.getGuest().getFullName()
        );
    }

}
