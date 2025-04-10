package com.hotel.booking.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.booking.entity.Room;
import com.hotel.booking.service.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@CrossOrigin
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/search")
    @CrossOrigin
    public ResponseEntity<Page<Room>> searchRooms(
            @RequestParam(defaultValue = "") String location,
            @RequestParam(defaultValue = "") String roomType,
            @RequestParam(required = false) Integer occupancy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "pricePerNight,asc") String sortBy) {

        // Sorting logic
        String[] sortParams = sortBy.split(",");
        Sort sort = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Room> rooms = roomService.searchAvailableRooms(location, roomType, occupancy, pageable);

        return ResponseEntity.ok(rooms);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }
}
