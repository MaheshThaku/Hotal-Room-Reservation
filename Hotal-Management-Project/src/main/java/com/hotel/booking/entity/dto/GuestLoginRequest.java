package com.hotel.booking.entity.dto;


import lombok.Data;

@Data
public class GuestLoginRequest {
    private String email;
    private String password;
}
