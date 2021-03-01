package com.booking.recruitment.hotel.sorter;

import java.util.List;

/**
 * @author Shailendra Chauhan
 */
public interface Sorter<T> {

    List<T> sort(List<T> list, Object ... params);
}
