/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
//        CSVreaderToModel(cdp.getEvents());
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
    
//    void CSVreaderToModel(ArrayList <String []> eventsRead){
//        for(int i = 0; i < eventsRead.size(); i++){
//            String [] dates = eventsRead.get(i)[0].split("/");                  //in the csv date was all in one string separated by /
//            addNewEvent(new Event(eventsRead.get(i)[1], eventsRead.get(i)[2], dates[0], dates[1], dates[2]));
//        }
//        
//    }
//     void PSVreaderToModel(ArrayList <String []> eventsRead){
//        for(int i = 0; i < eventsRead.size(); i++){
//            String [] dates = eventsRead.get(i)[1].split("/");                  //in the psv date was all in one string separated by /
//            addNewEvent(new Event(eventsRead.get(i)[0], eventsRead.get(i)[2], dates[0], dates[1], dates[2]));
//        }
//        
//    }
     
    public void addNewEvent(Event eve){
        events.add(eve);
        
    }
    
    public void addNewTask(Task t){
        tasks.add(t);
    }
    
    public void addNewPlan(Plan p){
        plans.add(p);
    }
    public ArrayList getEventList(){
        return this.events;
    }
    
    public ArrayList getTaskList(){
        return this.tasks;
    }

    public ArrayList getPlanList(){
        return this.plans;
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
