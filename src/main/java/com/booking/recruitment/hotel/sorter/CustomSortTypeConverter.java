package com.booking.recruitment.hotel.sorter;

import org.springframework.core.convert.converter.Converter;

/**
 * @author Shailendra Chauhan
 */
public class CustomSortTypeConverter implements Converter<String, SortType> {
    @Override
    public SortType convert(String source) {
        try {
            return SortType.fromString(source);
        } catch (Exception e) {
            return null; // or SortEnum.asc
        }
    }
}
