/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.banik.registrations.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author majky
 */
public class DateConverter {
    public static String getStringDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("DD.MM.YYYY");
        
        return formatter.format(date);
    }
}
