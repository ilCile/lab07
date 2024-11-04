package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    private class SortByDate implements Comparator<String>{

        public int compare(String o1, String o2) {
            return Month.fromString(o2).getDays() - Month.fromString(o1).getDays();
        }
        
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    private class SortByMonthOrder implements Comparator<String>{

        public int compare(String o1, String o2) {
            return Month.fromString(o1).getId() - Month.fromString(o2).getId();
        }
        
    }
}
