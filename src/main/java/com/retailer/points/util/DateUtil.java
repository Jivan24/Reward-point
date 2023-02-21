package com.retailer.points.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public static DateFormat responseDateFormat = new SimpleDateFormat("MMM-yyyy");

    public static Date getStartDate(int month){
        LocalDate startDate = LocalDate.now();
        startDate = startDate.minusMonths(month);
        return Date.from(startDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
