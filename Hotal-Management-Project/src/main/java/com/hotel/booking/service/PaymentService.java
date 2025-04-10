package com.hotel.booking.service;

import com.hotel.booking.entity.Payment;
import com.hotel.booking.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment makePayment(Payment payment) {
        payment.setPaymentDate(LocalDateTime.now());
// 		Ideally should come from Payment Gateway Response
        payment.setPaymentStatus("SUCCESS"); 
        return paymentRepository.save(payment);
    }
}
