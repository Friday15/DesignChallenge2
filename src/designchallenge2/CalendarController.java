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
public class CalendarController implements MVController{
    CalendarProgramView calP;
    CalendarModel cm;
    EventView eview;
    TodoListView tlv;
    ModifyPlanView mpv;
    PlanButton pb;
    
    public CalendarController(final CalendarModel cm){
        this.calP = new CalendarProgramView(cm, this);
        this.eview = new EventView();
        this.tlv = new TodoListView(cm);
        this.mpv = new ModifyPlanView();
        this.cm = cm;


        this.calP.addBtnPrevActionListener(new btnPrev_Action());
	this.calP.addBtnNextActionListener(new btnNext_Action());
	this.calP.addCmbYearActionListener(new cmbYear_Action());
        
        this.calP.addTableListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent evt)  
                    {  
                        int col = calP.calendarTable.getSelectedColumn();  
                        int row = calP.calendarTable.getSelectedRow();  
                        if(calP.calendarTable.getValueAt(row, col) != null){
                            cm.dayToday = (int) calP.calendarTable.getValueAt(row, col);
                            cm.updateViews();
                        }
                        
                    }
        });
        
    
        this.eview.SubmitListener(new  SubmitListen());
        this.eview.CSVListener(new CSVListen());
        this.eview.PSVListener(new PSVListen());
        this.tlv.CreateListener(new CreateListen());
        this.tlv.addSlotsListener(new SlotsListen());
        this.tlv.addCheckListener(new CheckListen());
        this.mpv.addBackListener(new BackListen());
        this.mpv.addDoneListener(new DoneListen());
        this.mpv.addRemoveListener(new RemoveListen());
        this.cm.attachModelListener(calP);
        this.cm.attachModelListener(tlv);
        
        cm.updateViews();
        //calP.refreshCalendar(cm.monthToday, cm.yearToday);
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

        class CSVListen implements ActionListener{
            
            public void actionPerformed (ActionEvent e){
                String csv = eview.getCSV();
               cm.cdp.readData(csv);
               cm.CSVreaderToModel(cm.cdp.getPlans());
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
                boolean possible = true;
                if(eview.isTask()){
                    try {
                        String STask = eview.getDMonth()+"/"+eview.getDDay()+"/"+calP.getDYear()+"/"+eview.getDHour()+"/"+eview.getDMin()+"/00";    
                        Date start=new SimpleDateFormat("MM/dd/yyyy/HH/mm/ss").parse(STask);
                        
                        
                        for(int i = 0;i < cm.getPlanList().size();i++){
                            if(start.equals(((Plan)cm.getPlanList().get(i)).getStartDate())||(start.after(((Plan)cm.getPlanList().get(i)).getStartDate())&&start.before(((Plan)cm.getPlanList().get(i)).getEndDate()))){
                                possible = false;
                                break;
                            }
                            
                        }
                        if(possible){
                            Task newTask= new Task(eview.getDName(),start,false);
                            cm.addNewTask(newTask);
                            cm.addNewPlan(newTask);
                        }

                    } catch (Exception ex) {
                        System.out.println("submit "+ex);
                        ex.printStackTrace();
                    }
                        
                }else{
                    try {
                        String SEvent = eview.getDMonth()+"/"+eview.getDDay()+"/"+calP.getDYear()+"/"+eview.getDHour()+"/"+eview.getDMin()+"/00";    
                        Date start=new SimpleDateFormat("MM/dd/yyyy/HH/mm/ss").parse(SEvent);
                        
                        String EEvent = eview.getEndMonth()+"/"+eview.getEndDay()+"/"+calP.getDYear()+"/"+eview.getEndHour()+"/"+eview.getEndMin()+"/00";   
                        Date End=new SimpleDateFormat("MM/dd/yyyy/HH/mm/ss").parse(EEvent);
                        System.out.println(start+" To "+End);
                        
                        for(int i = 0;i < cm.getPlanList().size();i++){
                            if(start.after(End)||start.equals(End)){
                                possible = false;
                                break;
                            }
                            if(start.equals(((Plan)cm.getPlanList().get(i)).getStartDate())||(start.after(((Plan)cm.getPlanList().get(i)).getStartDate())&&start.before(((Plan)cm.getPlanList().get(i)).getEndDate()))){
                                possible = false;
                                break;
                            }
                            if(End.equals(((Plan)cm.getPlanList().get(i)).getEndDate())||(End.after(((Plan)cm.getPlanList().get(i)).getStartDate())&&End.before(((Plan)cm.getPlanList().get(i)).getEndDate()))){
                                possible = false;
                                break;
                            }
                        }
                        if(possible){
                            Event newEvent= new Event(eview.getDName(), start, End,false);
                            cm.addNewEvent(newEvent);
                            cm.addNewPlan(newEvent);
                        }

                    } catch (Exception ex) {
                        System.out.println("exception "+ex);
                        ex.printStackTrace();
                    } 
                }
                //add newevent to some array list
                if(possible){
                    cm.dw.writeData(cm.getPlanList());                                //writes to Event List.csv

                    eview.reset();
                    eview.setVisible(false);//only use it if date is actaully useable

                    cm.updateViews();
                }else{
                    eview.errorWarn("Date is not Possible");
                }

            }
    }
        
        
        class BackListen implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent ae) {
                mpv.setVisible(false);
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
                    pb=((PlanButton)ae.getSource());
                    String name = ((Plan)cm.getPlanList().get(i)).getName();
                    Date startDate = ((Plan)cm.getPlanList().get(i)).getStartDate();
                    
                    if(((Plan)cm.getPlanList().get(i)) instanceof Event){
                        Date endDate = ((Plan)cm.getPlanList().get(i)).getEndDate();
                        mpv.setPlanTexts(name, startDate, endDate);
                    }else{
                        mpv.setPlanTexts(name, startDate);
                    }
                        
                    if(pb.getPlan().getDone()==true)
                        mpv.disableDone();
                    else
                        mpv.enableDone();
                    mpv.setVisible(true);
                }
            }

        }
            
        }

        
        class CheckListen implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent ae) {

                cm.updateViews();
            }
            
        }
        
        class RemoveListen implements ActionListener{
        /**
         * does the action
         * @param e contains the action performed
         */
            @Override
            public void actionPerformed(ActionEvent e) {
                mpv.setVisible(false);
                for(int i = 0;i<cm.getPlanList().size();i++){
                    if(pb.getPlan()==cm.getPlanList().get(i)){
                        cm.getPlanList().remove(i);
                        System.out.println("Remove Plan");
                    }    
                }
                if(pb.getPlan() instanceof Event){
                    for(int i = 0;i<cm.getEventList().size();i++){
                        if(pb.getPlan()==cm.getEventList().get(i)){
                            cm.getEventList().remove(i);
                            System.out.println("Remove Event");
                        }    
                    }
                }else{
                    for(int i = 0;i<cm.getTaskList().size();i++){
                        if(pb.getPlan()==cm.getTaskList().get(i)){
                            cm.getTaskList().remove(i);
                            System.out.println("Remove Task");
                        }    
                    }  
                }
                cm.dw.writeData(cm.getEventList()); 
                cm.updateViews();
            }
        }
        
        class DoneListen implements ActionListener{
        /**
         * does the action
         * @param e contains the action performed
         */
            @Override
            public void actionPerformed(ActionEvent e) {
                //pb.planEnded();
                mpv.setVisible(false);
                for(int i = 0;i<cm.getPlanList().size();i++){
                    if(pb.getPlan()==cm.getPlanList().get(i)){
                        ((Plan)cm.getPlanList().get(i)).markedDone();
                        System.out.println("Done Plan");
                    }    
                }
                if(pb.getPlan() instanceof Event){
                    for(int i = 0;i<cm.getEventList().size();i++){
                        if(pb.getPlan()==cm.getEventList().get(i)){
                            ((Event)cm.getEventList().get(i)).markedDone();
                            System.out.println("Done Event");
                        }    
                    }
                }else{
                    for(int i = 0;i<cm.getTaskList().size();i++){
                        if(pb.getPlan()==cm.getTaskList().get(i)){
                            ((Task)cm.getTaskList().get(i)).markedDone();
                            System.out.println("Done Task");
                        }    
                    }  
                }
                pb.getPlan().markedDone();
                cm.dw.writeData(cm.getEventList()); 
                cm.updateViews();
                
            }
            
        }
}
