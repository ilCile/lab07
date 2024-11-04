package it.unibo.nestedenum;

import java.util.NoSuchElementException;

public enum Month {

    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private final int  id;
    private final int days;
    
    
    private Month(int id, int days){
        this.id = id;
        this.days = days;
    }

    public int getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    public static Month fromString(String month){
        month = month.toLowerCase();
        if(month == "ma" || month == "ju" || month == "a" || month == "j"){
            throw new IllegalArgumentException();
        }
        String prefix;
        for(var m : Month.values()){
            prefix = "" + m.name().charAt(0) + m.name().charAt(1);
            prefix = prefix.toLowerCase();
            if(month == m.name().toLowerCase() || month == prefix || month.charAt(0) == prefix.charAt(0)){
                return m;
            }
        }
        throw new NoSuchElementException();
    }
}
