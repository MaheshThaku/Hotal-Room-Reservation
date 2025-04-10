package com.hotel.booking.entity.dto;


import java.time.LocalDate;

import lombok.Data;

@Data
public class ReservationRequest {
    private Long roomId;
    private Long guestId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String paymentMethod;  // Optional if payment is in same flow
}
