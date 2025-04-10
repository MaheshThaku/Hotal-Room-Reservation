package com.hotel.booking.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hotel.booking.entity.Guest;
import com.hotel.booking.entity.Payment;
import com.hotel.booking.entity.Reservation;
import com.hotel.booking.entity.Room;
import com.hotel.booking.repository.GuestRepository;
import com.hotel.booking.repository.PaymentRepository;
import com.hotel.booking.repository.ReservationRepository;
import com.hotel.booking.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	private final RoomRepository roomRepository;
	private final GuestRepository guestRepository;
	private final ReservationRepository reservationRepository;
	private final PaymentRepository paymentRepository;
	private final PasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {

		List<Room> rooms = Arrays.asList(
				Room.builder().roomNumber("201").roomType("Suite").location("Delhi").occupancy(4).pricePerNight(7500.0)
						.isAvailable(true).build(),
				Room.builder().roomNumber("103").roomType("Single").location("Chennai").occupancy(1)
						.pricePerNight(2200.0).isAvailable(true).build(),
				Room.builder().roomNumber("104").roomType("Double").location("Kolkata").occupancy(2)
						.pricePerNight(3800.0).isAvailable(true).build(),
				Room.builder().roomNumber("202").roomType("Suite").location("Hyderabad").occupancy(4)
						.pricePerNight(7200.0).isAvailable(true).build(),
				Room.builder().roomNumber("105").roomType("Single").location("Mumbai").occupancy(1)
						.pricePerNight(2600.0).isAvailable(true).build(),
				Room.builder().roomNumber("106").roomType("Double").location("Pune").occupancy(2).pricePerNight(4100.0)
						.isAvailable(true).build(),
				Room.builder().roomNumber("203").roomType("Suite").location("Delhi").occupancy(4).pricePerNight(7700.0)
						.isAvailable(true).build(),
				Room.builder().roomNumber("107").roomType("Single").location("Chennai").occupancy(1)
						.pricePerNight(2300.0).isAvailable(true).build(),
				Room.builder().roomNumber("108").roomType("Double").location("Kolkata").occupancy(2)
						.pricePerNight(3900.0).isAvailable(true).build(),
				Room.builder().roomNumber("204").roomType("Suite").location("Hyderabad").occupancy(4)
						.pricePerNight(7300.0).isAvailable(true).build(),
				Room.builder().roomNumber("109").roomType("Single").location("Mumbai").occupancy(1)
						.pricePerNight(2550.0).isAvailable(true).build(),
				Room.builder().roomNumber("110").roomType("Double").location("Pune").occupancy(2).pricePerNight(4050.0)
						.isAvailable(true).build(),
				Room.builder().roomNumber("205").roomType("Suite").location("Delhi").occupancy(4).pricePerNight(7600.0)
						.isAvailable(true).build(),
				Room.builder().roomNumber("111").roomType("Single").location("Chennai").occupancy(1)
						.pricePerNight(2400.0).isAvailable(true).build(),
				Room.builder().roomNumber("112").roomType("Double").location("Kolkata").occupancy(2)
						.pricePerNight(3950.0).isAvailable(true).build(),
				Room.builder().roomNumber("206").roomType("Suite").location("Hyderabad").occupancy(4)
						.pricePerNight(7400.0).isAvailable(true).build(),
				Room.builder().roomNumber("113").roomType("Single").location("Mumbai").occupancy(1)
						.pricePerNight(2700.0).isAvailable(true).build(),
				Room.builder().roomNumber("114").roomType("Double").location("Pune").occupancy(2).pricePerNight(4150.0)
						.isAvailable(true).build());

		roomRepository.saveAll(rooms);
		
		Room room1 = Room.builder().roomNumber("101").roomType("Single").location("Mumbai").occupancy(1)
				.pricePerNight(2500.0).isAvailable(true).build();

		Room room2 = Room.builder().roomNumber("102").roomType("Double").location("Pune").occupancy(2)
				.pricePerNight(4000.0).isAvailable(true).build();
		roomRepository.save(room1);
		roomRepository.save(room2);
		
		// Guests Creation
		Guest guest1 = Guest.builder().fullName("Mahesh Thakur").email("mahesh@gmail.com").phone("9876543210")
				.password(encoder.encode("Pass1"))
				.build();

		Guest guest2 = Guest.builder().fullName("Anjali Patil").email("anjali@gmail.com").phone("9123456780")
				.password(encoder.encode("encryptedPass2"))
				.build();

		guestRepository.saveAll(List.of(guest1, guest2));

		// Reservation Data
		Reservation reservation1 = Reservation.builder().checkInDate(LocalDate.now())
				.checkOutDate(LocalDate.now().plusDays(2)).status("BOOKED").guest(guest1).room(room1).build();

		Reservation reservation2 = Reservation.builder().checkInDate(LocalDate.now().plusDays(1))
				.checkOutDate(LocalDate.now().plusDays(4)).status("BOOKED").guest(guest2).room(room2).build();

		reservationRepository.saveAll(List.of(reservation1, reservation2));

		// Payment Data
		Payment payment1 = Payment.builder().reservation(reservation1).paymentMethod("UPI")
				.amount(room1.getPricePerNight() * 2).paymentDate(LocalDateTime.now()).paymentStatus("SUCCESS").build();

		Payment payment2 = Payment.builder().reservation(reservation2).paymentMethod("CARD")
				.amount(room2.getPricePerNight() * 3).paymentDate(LocalDateTime.now()).paymentStatus("SUCCESS").build();

		paymentRepository.saveAll(List.of(payment1, payment2));

		System.out.println("=== Sample Data Loaded Successfully ===");
	}
}
