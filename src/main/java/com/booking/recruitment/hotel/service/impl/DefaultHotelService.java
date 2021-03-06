package com.booking.recruitment.hotel.service.impl;

import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.repository.HotelRepository;
import com.booking.recruitment.hotel.service.CityService;
import com.booking.recruitment.hotel.service.HotelService;
import com.booking.recruitment.hotel.sorter.SortType;
import com.booking.recruitment.hotel.sorter.Sorter;
import com.booking.recruitment.hotel.sorter.SortingFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class DefaultHotelService implements HotelService {
    private final HotelRepository hotelRepository;

    private static final int LIMIT_RECORD = 3;

    @Autowired
    DefaultHotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Autowired
    private CityService cityService;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Hotel> getHotelsByCity(Long cityId, Optional<SortType> sortingParam, Optional<Long> optionalLimit) {
        List<Hotel> hotelList = hotelRepository.findAll().stream()
                .filter((hotel) -> cityId.equals(hotel.getCity().getId()))
                .collect(Collectors.toList());

        if (sortingParam.isPresent()) {
            Sorter<Hotel> sorter = SortingFactory.getSorter(sortingParam.get());
            hotelList = sorter.sort(hotelList, cityService.getCityById(cityId));
        }

        return hotelList.stream()
                .limit(optionalLimit.isPresent() ? optionalLimit.get() : LIMIT_RECORD)
                .collect(Collectors.toList());
    }

    @Override
    public Hotel createNewHotel(Hotel hotel) {
        if (hotel.getId() != null) {
            throw new BadRequestException("The ID must not be provided when creating a new Hotel");
        }

        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelById(Long id) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent() && !hotelOptional.get().isDeleted()) {
            return hotelOptional.get();
        }
        throw new ElementNotFoundException(String.format("Hotel with id %d not exist", id));
    }

    @Override
    public void deleteHotel(Long id) {
        Hotel hotel = getHotelById(id);
        hotel.setDeleted(true);
        hotelRepository.save(hotel);
    }
}
