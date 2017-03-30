/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author SONY
 */
public class TimeConverter {
    public Date dateStringtoDate(String date, String time){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d = null;
        try {
            d = df.parse(date + " " + time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
    public String dateToString(Date d){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = "";
        date = df.format(d).toString();
        return date;
    }
    public String dateFormat(String date){
        String d = "";
        return d;
    }
    public String timeFormat(String time){
        String t = "";
        return t;
    }
}
