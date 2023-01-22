package com.jogamais.ufcg.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}