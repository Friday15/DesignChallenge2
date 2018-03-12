/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mart
 */
public class CalendarModel implements ModelVC{
    public int yearBound, monthBound, dayBound, yearToday, monthToday, dayToday;
    private ArrayList <Event> events;
    private ArrayList <Task> tasks;
    private ArrayList <Plan> plans;
    private ArrayList <Date> takenDates;
    private ArrayList <ModelListener> listeners;
    public DefaultTableModel modelCalendarTable;
    CSVDataParser cdp = new CSVDataParser();
    PSVDataParser pdp = new PSVDataParser();
    DataWriter dw = new DataWriter();
    
    public CalendarModel(){
        GregorianCalendar cal = new GregorianCalendar();
	dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
	monthBound = cal.get(GregorianCalendar.MONTH);
	yearBound = cal.get(GregorianCalendar.YEAR);
	monthToday = monthBound; 
	yearToday = yearBound;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy/HH/mm/ss");
            Date date = new Date();
            String CDate=dateFormat.format(date);
            String[] day = CDate.split("/");
            System.out.println(day[0]);
            
        dayToday = Integer.parseInt(day[0]);
        System.out.println("day today " + dayToday);
        modelCalendarTable = new DefaultTableModel(){
                    public boolean isCellEditable(int rowIndex, int mColIndex)
                    {
                        return false;
                    }
                };
                
        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
		for (int i=0; i<7; i++){
			modelCalendarTable.addColumn(headers[i]);
		}
        

        events = new ArrayList();
        tasks = new ArrayList();
        plans = new ArrayList();
        listeners = new ArrayList();
        
        cdp.readData();                                                         //Reads data from csv immediately
        pdp.readData();
        CSVreaderToModel(cdp.getPlans());
//        PSVreaderToModel(pdp.getEvents());
    }
   
    void mergeLists(){
        for(int i = 0;i < tasks.size();i++){
            plans.add(tasks.get(i));
        }
        for(int j = 0;j < events.size();j++){
            plans.add(events.get(j));
        }
    }
    
    void CSVreaderToModel(ArrayList <String []> plansRead){
        for(int i = 0; i < plansRead.size(); i++){
            try {
                String Sdates = plansRead.get(i)[3];                  //in the csv date was all in one string separated by /
                Date start=new SimpleDateFormat("MM/dd/yyyy/HH/mm").parse(Sdates);
                String Edates = plansRead.get(i)[4];
                Date end=new SimpleDateFormat("MM/dd/yyyy/HH/mm").parse(Edates);
                Boolean done;
                if(plansRead.get(i)[2].equals("1"))
                    done = true;
                else
                    done = false;
                if(plansRead.get(i)[1].equals("T")){
                    Task newTask = new Task(plansRead.get(i)[0],start,done);
                    addNewPlan(newTask);
                    addNewTask(newTask);
                }else{
                    Event newEvent = new Event(plansRead.get(i)[0],start,end,done);
                    addNewPlan(newEvent);
                    addNewEvent(newEvent);
                }
                 //0 name 1 (T)askor(E)vent 2 done(1)ornot(0) 3 startdate 4 enddate
            } catch (Exception ex) {
                System.out.println(ex);
                System.out.println("CSVreaderToModel");//error handling
            }
        }
        
    }
//     void PSVreaderToModel(ArrayList <String []> plansRead){
//        for(int i = 0; i < plansRead.size(); i++){
//            String [] dates = plansRead.get(i)[1].split("/");                  //in the psv date was all in one string separated by /
//            addNewEvent(new Event(plansRead.get(i)[0], plansRead.get(i)[2], dates[0], dates[1], dates[2]));
//        }
//        
//    }
     
    public ArrayList<Date> getDaysBetweenDates(Date startdate, Date enddate){
        
        ArrayList<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate))
        {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
    
    public void addNewEvent(Event eve){
        System.out.println("potato event");
        events.add(eve);
//        for(int i = 0;i < getDaysBetweenDates(eve.getStartDate(), eve.getEndDate()).size();i++){
//            addNewTakenDate(getDaysBetweenDates(eve.getStartDate(), eve.getEndDate()).get(i));
//        }  
    }
    
    public void addNewTask(Task t){
        tasks.add(t);
        //addNewTakenDate(t.getStartDate());
    }
    
    public void addNewPlan(Plan p){
        plans.add(p);
    }
    
//    public void addNewTakenDate(Date d){
//        takenDates.add(d);
//    }
    
    public ArrayList getEventList(){
        return this.events;
    }
    
    public ArrayList getTaskList(){
        return this.tasks;
    }

    public ArrayList getPlanList(){
        return this.plans;
    }
    
    public ArrayList getTakenDateList(){
        return this.takenDates;
    }
    
    @Override
    public void attachModelListener(ModelListener ml){
        if(!listeners.contains(ml)){
            listeners.add(ml);
        }
    }
    
    @Override
    public void detachModelListener(ModelListener ml){
        listeners.remove(ml);
    }
    
    @Override
    public void updateViews() {
        for(int i = listeners.size() -1;i >= 0;i--){
            listeners.get(i).modelUpdated(this);
        }
    }
}
