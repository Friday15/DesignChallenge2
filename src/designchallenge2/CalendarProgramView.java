/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

/**
 *
 * @author Arturo III
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;


public class CalendarProgramView implements MViewController, ModelListener{

        /**** Swing Components ****/
        public JLabel monthLabel, yearLabel;
	public JButton btnPrev, btnNext, eventBtn;
        public JComboBox cmbYear;
	public JFrame frmMain;
	public Container pane;
	public JScrollPane scrollCalendarTable;
	public JPanel calendarPanel;
        
        /**** Calendar Table Components ***/
	public JTable calendarTable;
        
        CalendarModel cm;
        CalendarController cont;
        
	public CalendarProgramView(CalendarModel cm, CalendarController cont)
        {
                this.cm = cm;
                this.cont = cont;
		try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
		catch (Exception e) {}

                
		frmMain = new JFrame ("Calendar Application");
                frmMain.setSize(660, 750);
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		monthLabel = new JLabel ("January");
		yearLabel = new JLabel ("Change year:");
		cmbYear = new JComboBox();
		btnPrev = new JButton ("<<");
		btnNext = new JButton (">>");
                eventBtn = new JButton ("Add Event");
		
                
		calendarTable = new JTable(this.cm.modelCalendarTable);

                
		scrollCalendarTable = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);

		calendarPanel.setBorder(BorderFactory.createTitledBorder("Calendar"));

		
		pane.add(calendarPanel);
		calendarPanel.add(monthLabel);
		calendarPanel.add(yearLabel);
                calendarPanel.add(eventBtn);
		calendarPanel.add(cmbYear);
		calendarPanel.add(btnPrev);
		calendarPanel.add(btnNext);
		calendarPanel.add(scrollCalendarTable);
		
                calendarPanel.setBounds(0, 0, 640, 670);
                monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 200, 50);
		yearLabel.setBounds(20, 610, 160, 40);
                eventBtn.setBounds(220, 610, 160, 40);
		cmbYear.setBounds(460, 610, 160, 40);
		btnPrev.setBounds(20, 50, 100, 50);
		btnNext.setBounds(520, 50, 100, 50);
		scrollCalendarTable.setBounds(20, 100, 600, 500);
                
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		
		
		
		calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background

		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);

		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		calendarTable.setRowHeight(76);
		this.cm.modelCalendarTable.setColumnCount(7);
		this.cm.modelCalendarTable.setRowCount(6);
		
		for (int i = this.cm.yearBound-100; i <= this.cm.yearBound+100; i++)
                {
			cmbYear.addItem(String.valueOf(i));
		}
                
		
	}
	
        void refreshCalendar(int month, int year)
        {
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som, i, j;
			
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= cm.yearBound-10)
                    btnPrev.setEnabled(false);
		if (month == 11 && year >= cm.yearBound+100)
                    btnNext.setEnabled(false);
                
		monthLabel.setText(months[month]);
		monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 360, 50);
                
		cmbYear.setSelectedItem(""+year);                          
		
		for (i = 0; i < 6; i++)
			for (j = 0; j < 7; j++)
				cm.modelCalendarTable.setValueAt(null, i, j);
		
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
                
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		for (i = 1; i <= nod; i++)
                {
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			
                        
                        int haveEvent=0;
                        
                        String text ="<html> <b> "+i+" </b> <br> ";
                        for(int k=0;k<cm.getEventList().size();k++){
                            if(Integer.parseInt(((Event)(cm.getEventList().get(k))).getDay())==i&&Integer.parseInt(((Event)(cm.getEventList().get(k))).getMonth())==month+1
                                    &&Integer.parseInt(((Event)(cm.getEventList().get(k))).getYear())==year){

                                text=text+"<font color = "+"Red"+"> "+((Event)(cm.getEventList().get(k))).getName()+" </font> <br> ";
                                haveEvent=1;
                            }
                        }
                        text=text+"</html>";    
                        if(haveEvent==1){
                           cm.modelCalendarTable.setValueAt(text, row, column);
                        }
                        else
                           cm.modelCalendarTable.setValueAt(i, row, column);// THIS IS HOW U CHANGE STUFF IN SQUARES
                        
		}

		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer());
	}
        
        
        public void addBtnNextActionListener(ActionListener al){
            this.btnNext.addActionListener(al);
        }
	
        public void addBtnPrevActionListener(ActionListener al){
            this.btnPrev.addActionListener(al);
        }
        
        public void addCmbYearActionListener(ActionListener al){
            this.cmbYear.addActionListener(al);
        }
        
        public void addEventBtnActionListener(ActionListener al){
            this.eventBtn.addActionListener(al);
        }
        
        public void addTableListener(MouseListener ml){
            this.calendarTable.addMouseListener(ml);
        }
        //extra stuff
        public int getDYear(){
            return cmbYear.getSelectedIndex()+2018-100;
    }

    @Override
    public void refresh() {
        refreshCalendar(cm.monthToday, cm.yearToday);
    }

    @Override
    public void modelUpdated(ModelVC model) {
        refresh();
    }
}
