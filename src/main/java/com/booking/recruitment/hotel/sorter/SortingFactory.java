package com.booking.recruitment.hotel.sorter;

/**
 * @author Shailendra Chauhan
 */
public class SortingFactory {

    private SortingFactory() {
    }

    public static Sorter getSorter(SortType type){
        switch (type){
            case DISTANCE: return new DistanceSorter();
            default: return new DistanceSorter();
        }
    }
}
