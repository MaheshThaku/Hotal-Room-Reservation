package com.hotel.booking.entity.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {

    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;
    private String roomNumber;
    private String guestName;
}
