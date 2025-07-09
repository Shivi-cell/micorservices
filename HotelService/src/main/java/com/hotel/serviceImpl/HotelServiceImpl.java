package com.hotel.serviceImpl;

import com.hotel.entities.Hotel;
import com.hotel.exceptions.ResourceNotFoundException;
import com.hotel.repositories.HotelRepository;
import com.hotel.services.HotelService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        log.info("Creating a new hotel with details: {}", hotel);

        String randomHotelId = UUID.randomUUID().toString();
        log.info("Generated random hotelId is {}",randomHotelId);
        hotel.setId(randomHotelId);

        Hotel savedHotel = hotelRepository.save(hotel);
        log.info("Hotel created with ID: {}", savedHotel.getId());
        return savedHotel;
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        log.info("Fetching hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() ->
                new ResourceNotFoundException("Hotel not found with the given id " + hotelId)
        );
        log.info("Hotel found: {}", hotel);
        return hotel;
    }

    @Override
    public List<Hotel> getAllHotels() {
        log.info("Fetching all hotels");
        List<Hotel> hotels = hotelRepository.findAll();
        log.info("Number of hotels found: {}", hotels.size());
        return hotels;
    }
}
