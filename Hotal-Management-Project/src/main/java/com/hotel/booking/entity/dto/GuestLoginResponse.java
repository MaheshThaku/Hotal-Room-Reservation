package com.hotel.booking.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GuestLoginResponse {
	
	private Long id;
	private String fullName;
	private String email;
	private String phone;
}
