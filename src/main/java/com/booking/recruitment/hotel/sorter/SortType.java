package com.booking.recruitment.hotel.sorter;

/**
 * @author Shailendra Chauhan
 */
public enum SortType {
    DISTANCE("distance"),

    PRICE("price");

    private String value;

    SortType(String value) {
        this.value = value;
    }

    public static SortType fromString(String source) {
        for(SortType sortType : values()){
            if(source.equalsIgnoreCase(sortType.value)){
                return sortType;
            }
        }

        return null;
    }

    public String toString() {
        return value;
    }
}
