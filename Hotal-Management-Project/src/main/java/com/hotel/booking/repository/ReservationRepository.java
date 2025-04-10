package com.hotel.booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hotel.booking.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByGuestId(Long guestId);

    List<Reservation> findByRoomId(Long roomId);
    
    @Query("SELECT r FROM Reservation r JOIN FETCH r.room JOIN FETCH r.guest WHERE r.id = :id")
    Optional<Reservation> findByIdWithRoomAndGuest(Long id);

    @Query("SELECT r FROM Reservation r JOIN FETCH r.room JOIN FETCH r.guest")
    List<Reservation> findAllWithRoomAndGuest();
    
}
