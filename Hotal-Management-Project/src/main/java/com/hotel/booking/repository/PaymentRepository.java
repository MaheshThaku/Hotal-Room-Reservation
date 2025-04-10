package com.hotel.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.booking.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
