/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Mart
 */
public class CalendarController {
    CalendarProgramView calP;
    CalendarModel cm;
    EventView eview;
    TodoListView tlv;
    ModifyPlanView mpv;
    
    public CalendarController(CalendarModel cm){
        this.calP = new CalendarProgramView(cm, this);
        this.eview = new EventView();
        this.tlv = new TodoListView();
        this.mpv = new ModifyPlanView();
        this.cm = cm;


        this.calP.addBtnPrevActionListener(new btnPrev_Action());
	this.calP.addBtnNextActionListener(new btnNext_Action());
	this.calP.addCmbYearActionListener(new cmbYear_Action());
        this.calP.addEventBtnActionListener(new eventBtn_Action());
        
        this.calP.addTableListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent evt)  
                    {  
                        int col = calP.calendarTable.getSelectedColumn();  
                        int row = calP.calendarTable.getSelectedRow();  
                        calP.calendarTable.getValueAt(row, col);
                        System.out.println("potato");
                        
                    }
        });
        
    
        this.eview.SubmitListener(new  SubmitListen());
        this.eview.CSVListener(new CSVListen());
        this.eview.PSVListener(new PSVListen());
        this.tlv.CreateListener(new CreateListen());
        this.tlv.addSlotsListener(new SlotsListen());
        
        this.cm.attachModelListener(calP);
        
        calP.refreshCalendar(cm.monthToday, cm.yearToday);
        tlv.setVisible(true);
    }
    
    
    class btnPrev_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
			if (cm.monthToday == 0)
                        {
				cm.monthToday = 11;
				cm.yearToday -= 1;
			}
			else
                        {
				cm.monthToday -= 1;
			}
			cm.updateViews();
		}
	}
    

    
	class btnNext_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
			if (cm.monthToday == 11)
                        {
				cm.monthToday = 0;
				cm.yearToday += 1;
			}
			else
                        {
				cm.monthToday += 1;
			}
			cm.updateViews();
		}
	}

	class cmbYear_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
			if (calP.cmbYear.getSelectedItem() != null)
                        {
				String b = calP.cmbYear.getSelectedItem().toString();
				cm.yearToday = Integer.parseInt(b);
				cm.updateViews();
			}
		}
	}
        class eventBtn_Action implements ActionListener{
            
            public void actionPerformed (ActionEvent e){
                eview.setVisible(true);
               
            }
        }
        class CSVListen implements ActionListener{
            
            public void actionPerformed (ActionEvent e){
                String csv = eview.getCSV();
               cm.cdp.readData(csv);
               //cm.CSVreaderToModel(cm.cdp.getEvents());
            }
        }
        class PSVListen implements ActionListener{
            
            public void actionPerformed (ActionEvent e){
                String psv = eview.getPSV();
               cm.pdp.readData(psv);
               //cm.PSVreaderToModel(cm.cdp.getEvents());
            }
        }
        
        class SubmitListen implements ActionListener{
        /**
         * does the action
         * @param e contains the action performed
         */
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(eview.isTask()){
                    try {
                        String STask = eview.getDMonth()+"/"+eview.getDDay()+"/"+calP.getDYear()+"/"+eview.getDHour()+"/"+eview.getDMin()+"/00";    
                        Date start=new SimpleDateFormat("dd/MM/yyyy/HH/mm/ss").parse(STask);
                        Task newTask= new Task(eview.getName(),start);
                        cm.addNewTask(newTask);
                        cm.addNewPlan(newTask);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                        
                }else{
                    try {
                        String SEvent = eview.getDMonth()+"/"+eview.getDDay()+"/"+calP.getDYear()+"/"+eview.getDHour()+"/"+eview.getDMin()+"/00";    
                        Date start=new SimpleDateFormat("dd/MM/yyyy/HH/mm/ss").parse(SEvent);
                        
                        String EEvent = eview.getEndMonth()+"/"+eview.getEndDay()+"/"+calP.getDYear()+"/"+eview.getEndHour()+"/"+eview.getEndMin()+"/00";   
                        Date End=new SimpleDateFormat("dd/MM/yyyy/HH/mm/ss").parse(EEvent);
                        
                        Event newEvent= new Event(eview.getName(),start,End);
                        cm.addNewEvent(newEvent);
                        cm.addNewPlan(newEvent);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    } 
                }
                //add newevent to some array list
                //cm.dw.writeData(cm.getEventList());                                //writes to Event List.csv
                
                eview.reset();
                eview.setVisible(false);//only use it if date is actaully useable
                
                cm.updateViews();
            }
    }
        
        class CreateListen implements ActionListener{
        /**
         * does the action
         * @param e contains the action performed
         */
            @Override
            public void actionPerformed(ActionEvent e) {
                eview.setVisible(true);
            }
        }
        
        class SlotsListen implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            for(int i = 0;i < cm.getPlanList().size();i++){
                if(cm.getPlanList().get(i) == ((PlanButton)ae.getSource()).getPlan()){
                    String name = ((Plan)cm.getPlanList().get(i)).getName();
                    Date date = ((Plan)cm.getPlanList().get(i)).getStartDate();
                    mpv.setPlanTexts(name, date);
                    mpv.setVisible(true);
                }
            }

        }
            
        }
}
