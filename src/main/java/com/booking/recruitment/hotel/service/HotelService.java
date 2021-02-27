package com.booking.recruitment.hotel.service;

import com.booking.recruitment.hotel.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
  List<Hotel> getAllHotels();

  List<Hotel> getHotelsByCity(Long cityId, Optional<String> sortingParam, Optional<Long> limit);

  Hotel createNewHotel(Hotel hotel);

  Hotel getHotelById(Long id);

  void deleteHotel(Long id);
}
