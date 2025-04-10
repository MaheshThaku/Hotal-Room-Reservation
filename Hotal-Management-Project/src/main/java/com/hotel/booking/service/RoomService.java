package com.hotel.booking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hotel.booking.entity.Room;
import com.hotel.booking.repository.RoomRepository;
import com.hotel.booking.util.RoomSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Page<Room> searchAvailableRooms(String location, String roomType, Integer occupancy, Pageable pageable) {
        Specification<Room> spec = Specification.where(RoomSpecification.isAvailable());

        if (location != null && !location.isEmpty()) {
            spec = spec.and(RoomSpecification.hasLocation(location));
        }
        if (roomType != null && !roomType.isEmpty()) {
            spec = spec.and(RoomSpecification.hasRoomType(roomType));
        }
        if (occupancy != null) {
            spec = spec.and(RoomSpecification.hasOccupancy(occupancy));
        }

        return roomRepository.findAll(spec, pageable);
    }


    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }
}
