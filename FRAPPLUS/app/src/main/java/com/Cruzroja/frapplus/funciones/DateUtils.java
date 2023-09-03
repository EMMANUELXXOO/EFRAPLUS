package com.Cruzroja.frapplus.funciones;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd       HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);

        return formattedDate;
    }
}
