/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Ashen One
 */
abstract public class Plan {
    private final String name;
    private final Date StartDate;
    private final Date EndDate;
    private final String Month;
    private final String Year;
    private final String Day;
    private final String Hour;
    private final String Min;
    private final String EMonth;
    private final String EYear;
    private final String EDay;
    private final String EHour;
    private final String EMin;
    
    
    public Plan(String name ,Date s, Date e){
        this.name = name;
        StartDate = s;
        EndDate = e;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy/HH/mm");
        String[] stuff =df.format(s).split("/");
        Month = stuff[0];
        Day = stuff[1];
        Year =stuff[2];
        Hour = stuff[3];
        Min = stuff[4];
        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy/HH/mm");
        String[] stuff2 =df2.format(e).split("/");
        EMonth = stuff2[0];
        EDay = stuff2[1];
        EYear =stuff2[2];
        EHour = stuff2[3];
        EMin = stuff2[4];
        
    }
    public Plan(String name ,Date s){
        this.name = name;
        StartDate = s;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy/HH/mm");
        String[] stuff =df.format(s).split("/");
        Month = stuff[0];
        Day = stuff[1];
        Year =stuff[2];
        Hour = stuff[3];
        Min = stuff[4];
        long MS = 60000;
        Calendar cal = Calendar.getInstance();
        cal.setTime(s);
        long t= cal.getTimeInMillis();
        EndDate = new Date(t + (30 * MS));
        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy/HH/mm");
        String[] stuff2 =df2.format(this.EndDate).split("/");
        EMonth = stuff2[0];
        EDay = stuff2[1];
        EYear =stuff2[2];
        EHour = stuff2[3];
        EMin = stuff2[4];
        //this.timeStart = timeStart;
    }
    
    public String getName(){
        return name;
    }
    
    public Date getStartDate(){
        return StartDate;
    }
    public Date getEndDate(){
        return EndDate;
    }
    public String getDay(){
        return Day;
    }
    public String getMonth(){
        return Month;
    }
    public String getYear(){
        return Year;
    }
    public String getHour(){
        return Hour;
    }
    public String getMin(){
        return Min;
    }
    public String getEDay(){
        return EDay;
    }
    public String getEMonth(){
        return EMonth;
    }
    public String getEYear(){
        return EYear;
    }
    public String getEHour(){
        return EHour;
    }
    public String getEMin(){
        return EMin;
    }
}
    
