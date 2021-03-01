package com.booking.recruitment.hotel.sorter;

import com.booking.recruitment.hotel.model.City;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.util.CommonUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Shailendra Chauhan
 */
public class DistanceSorter implements Sorter<Hotel> {

    @Override
    public List<Hotel> sort(List<Hotel> hotelList, Object... params) {
        if(params.length == 0){
            return hotelList;
        }
        if(!(params[0] instanceof City)){
            throw new IllegalArgumentException("Invalid parameter passed");
        }
        City city = (City) params[0];

        return hotelList.stream()
                .flatMap(hotel -> distanceFromCityMap(hotel, city).entrySet().stream())
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

    }

    private Map<Hotel, Double> distanceFromCityMap(Hotel hotel, City city){
        return Collections.singletonMap(hotel, CommonUtil.distanceCalculator(city, hotel));
    }
}
