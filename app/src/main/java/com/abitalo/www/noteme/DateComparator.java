package com.abitalo.www.noteme;

import java.util.Comparator;

/**
 * Created by asus on 2015/11/25.
 */
public class DateComparator implements Comparator<DateText> {

    @Override
    public int compare(DateText lhs, DateText rhs) {
        return rhs.getDate().compareTo(lhs.getDate());
    }

}