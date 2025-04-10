package com.hotel.booking.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestRequest {

    private String fullName;
    private String email;
    private String phone;
    private String password;
}
