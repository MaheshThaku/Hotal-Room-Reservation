package com.hotel.booking.util;

import org.springframework.data.jpa.domain.Specification;

import com.hotel.booking.entity.Room;

public class RoomSpecification {

    public static Specification<Room> isAvailable() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get("isAvailable"));
    }

    public static Specification<Room> hasLocation(String location) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("location"), location);
    }

    public static Specification<Room> hasRoomType(String roomType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("roomType"), roomType);
    }

    public static Specification<Room> hasOccupancy(int occupancy) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("occupancy"), occupancy);
    }
}

