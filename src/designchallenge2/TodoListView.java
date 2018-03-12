/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author Ashen One
 */
public class TodoListView extends javax.swing.JFrame implements MViewController, ModelListener {

    ArrayList <PlanButton> slots;
    ArrayList <JLabel> times;
    CalendarModel cm;
    /**
     * Creates new form test
     */
    public TodoListView(CalendarModel cm) {
        this.cm = cm;
        initComponents();
        slots = new ArrayList();
        times = new ArrayList();
        
        for(int i = 0;i < 48;i++){
            PlanButton button = new PlanButton();
            button.setPreferredSize(new Dimension(200, 60));
            button.setEnabled(false);
            button.setContentAreaFilled(false);
            
            slots.add(button);
            buttonPanel.add(slots.get(i));
        }
        
        for(int j = 0;j < 24;j++){
            JLabel label = new JLabel();
            if(j == 0)
                label.setText(" "+Integer.toString(12) + " AM");
            else if(j < 12)
                label.setText(" "+Integer.toString(j) + " AM");
            else if(j == 12)
                label.setText(" "+Integer.toString(12) + " PM");
            else
                label.setText(" "+Integer.toString(j-12) + " PM");
            
            label.setBorder(BorderFactory.createLineBorder(Color.RED));
            label.setPreferredSize(new Dimension(100, 60));
            times.add(label);
            timePanel.add(times.get(j));
        }
        
        this.revalidate();
        this.repaint();
    }

    void CreateListener(ActionListener listen){
		Create.addActionListener(listen);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewGroup = new javax.swing.ButtonGroup();
        PlannerPane = new javax.swing.JPanel();
        dateLabel = new javax.swing.JLabel();
        viewLabel = new javax.swing.JLabel();
        dayButton = new javax.swing.JButton();
        agendaButton = new javax.swing.JButton();
        Create = new javax.swing.JButton();
        viewPane = new javax.swing.JPanel();
        dayPane = new javax.swing.JScrollPane();
        jSplitPane = new javax.swing.JSplitPane();
        timePanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        agendaPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        agendaTitles = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        agendaTime = new javax.swing.JTextArea();
        saikouNoPlanner = new javax.swing.JLabel();
        eventCheck = new javax.swing.JCheckBox();
        taskCheck = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PlannerPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        dateLabel.setText("DATE HERE");

        viewLabel.setText("View");

        dayButton.setText("DAY");
        dayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayButtonActionPerformed(evt);
            }
        });

        agendaButton.setText("AGENDA");
        agendaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agendaButtonActionPerformed(evt);
            }
        });

        Create.setText("CREATE");

        viewPane.setLayout(new java.awt.CardLayout());

        dayPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        dayPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jSplitPane.setDividerLocation(-8);
        jSplitPane.setDividerSize(4);

        timePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        timePanel.setPreferredSize(new java.awt.Dimension(50, 100));
        timePanel.setLayout(new java.awt.GridLayout(0, 1));
        jSplitPane.setLeftComponent(timePanel);

        buttonPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonPanel.setLayout(new java.awt.GridLayout(0, 1));
        jSplitPane.setRightComponent(buttonPanel);

        dayPane.setViewportView(jSplitPane);

        viewPane.add(dayPane, "card2");

        agendaPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        agendaTitles.setColumns(20);
        agendaTitles.setRows(5);
        agendaTitles.setText("this is where we put the agenda title");
        jScrollPane1.setViewportView(agendaTitles);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        agendaTime.setColumns(20);
        agendaTime.setRows(5);
        agendaTime.setText("agenda times\n");
        jScrollPane2.setViewportView(agendaTime);

        javax.swing.GroupLayout agendaPaneLayout = new javax.swing.GroupLayout(agendaPane);
        agendaPane.setLayout(agendaPaneLayout);
        agendaPaneLayout.setHorizontalGroup(
            agendaPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agendaPaneLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        agendaPaneLayout.setVerticalGroup(
            agendaPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agendaPaneLayout.createSequentialGroup()
                .addGroup(agendaPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        viewPane.add(agendaPane, "card3");

        saikouNoPlanner.setText("Saikou no Planner");

        eventCheck.setText("Event");

        taskCheck.setText("Task");

        javax.swing.GroupLayout PlannerPaneLayout = new javax.swing.GroupLayout(PlannerPane);
        PlannerPane.setLayout(PlannerPaneLayout);
        PlannerPaneLayout.setHorizontalGroup(
            PlannerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PlannerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saikouNoPlanner, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(dayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(agendaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PlannerPaneLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(PlannerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Create, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventCheck)
                    .addComponent(taskCheck))
                .addGap(37, 37, 37)
                .addComponent(viewPane, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
        );
        PlannerPaneLayout.setVerticalGroup(
            PlannerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlannerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PlannerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PlannerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(PlannerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agendaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(saikouNoPlanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PlannerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PlannerPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Create, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(viewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(eventCheck)
                        .addGap(18, 18, 18)
                        .addComponent(taskCheck)
                        .addGap(169, 169, 169))
                    .addGroup(PlannerPaneLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(viewPane, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(34, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PlannerPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PlannerPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayButtonActionPerformed
        CardLayout cl = (CardLayout)(viewPane.getLayout());
        cl.show(viewPane, "card2");
        dayButton.setEnabled(false);
        agendaButton.setEnabled(true);
    }//GEN-LAST:event_dayButtonActionPerformed

    private void agendaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agendaButtonActionPerformed
        CardLayout cl = (CardLayout)(viewPane.getLayout());
        cl.show(viewPane, "card3");
        agendaButton.setEnabled(false);
        dayButton.setEnabled(true);
    }//GEN-LAST:event_agendaButtonActionPerformed

    
    public void addCheckListener(ActionListener al){
        eventCheck.addActionListener(al);
        taskCheck.addActionListener(al);
    } 
    
    public void addSlotsListener(ActionListener al){
        for(int i = 0;i < slots.size();i++){
            slots.get(i).addActionListener(al);
        }
    }
   
    
    void DayFill(int start, int end, int planNum){
        System.out.println("start " + start);
            
        for(int k = start;k < end;k++){
            if(k == start){
                slots.get(k*2).setPlan(((Plan)cm.getPlanList().get(planNum)));
                String tempName = slots.get(k*2).getPlan().getColoredName();

                slots.get(k*2).setEnabled(true);
                slots.get(k*2).setContentAreaFilled(true);
                slots.get(k*2).setText(tempName);

                this.revalidate();
                this.repaint();
            }else{
                if(k > start*2){
                    slots.get(k).setPlan(((Plan)cm.getPlanList().get(planNum)));
                    String tempName = slots.get(k).getPlan().getColoredName();

                    slots.get(k).setEnabled(true);
                    slots.get(k).setContentAreaFilled(true);
                    slots.get(k).setText(tempName);

                    this.revalidate();
                    this.repaint();
                }
                
            }
            
        }
    }
    
    void DayFill(int start, int end, int planNum, int tempMin){
        System.out.println("start " + start);
        
        int slotNum = start*2;
        if(tempMin == 30)
            slotNum += 1;
        
        for(int k = slotNum;k < end;k++){
            if(k == slotNum){
                slots.get(k).setPlan(((Plan)cm.getPlanList().get(planNum)));

                slots.get(k).setEnabled(true);
                slots.get(k).setContentAreaFilled(true);

                if(slots.get(k).getPlan().getDone()==true)
                    slots.get(k).planEnded();
                
                this.revalidate();
                this.repaint();
            }else{
                if(k > start*2){
                    slots.get(k).setPlan(((Plan)cm.getPlanList().get(planNum)));

                    slots.get(k).setEnabled(true);
                    slots.get(k).setContentAreaFilled(true);

                    if(slots.get(k).getPlan().getDone()==true)
                        slots.get(k).planEnded();

                    this.revalidate();
                    this.repaint();
                }
                
            }
            
        }
    }
    void SingleFill(int slot, int planNum, int minute){
        int slotNum = slot*2;
        if(minute == 30)
            slotNum += 1;

        
        slots.get(slotNum).setPlan(((Plan)cm.getPlanList().get(planNum)));

        slots.get(slotNum).setEnabled(true);
        slots.get(slotNum).setContentAreaFilled(true);

        if(slots.get(slotNum).getPlan().getDone()==true)
            slots.get(slotNum).planEnded();
        
        this.revalidate();
        this.repaint();

                
     }
    
    @Override
    public void refresh() {
        
    }

    public void TaskFill(CalendarModel cm, Plan tempPlan, int planNum){
        int tempDay = Integer.parseInt(tempPlan.getDay());
        
        if(cm.dayToday == tempDay){
            for(int j = 0;j < slots.size();j++){                                                    //increments over slots
                int tempMin = Integer.parseInt(tempPlan.getMin());
                int tempHour = Integer.parseInt(tempPlan.getHour()); 
 
                if(tempHour == j){
                    SingleFill(j, planNum, tempMin);
                }
            }
        }
    }
    
    public void EventFill(CalendarModel cm, Plan tempPlan, int i){
        int tempDaysBetweenSize = tempPlan.getDaysBetween().size();
        int tempDay = Integer.parseInt(tempPlan.getDay());
        int tempMin = Integer.parseInt(tempPlan.getMin());
        int tempHour = Integer.parseInt(tempPlan.getHour());  
        int tempEndMin = Integer.parseInt(tempPlan.getEMin());
        int tempEndHour = Integer.parseInt(tempPlan.getEHour());
        int tempEndDay = Integer.parseInt(tempPlan.getEDay());

                        
        for(int d = 0;d < tempDaysBetweenSize;d++){             //check daysBetween for match
            int dayWithinRange = tempPlan.getDaysBetween().get(d);

            if(cm.dayToday == dayWithinRange){

                for(int j = 0;j < slots.size();j++){                                                    //increments over slots
                    
                    if(tempHour == j){
                        
                        if(tempDay < tempEndDay){
                            if(tempDay == dayWithinRange){
                                DayFill(j, slots.size(), i, tempMin);

                            }else if(dayWithinRange > tempDay && dayWithinRange < tempEndDay){
                                DayFill(0, slots.size(), i);                   //take out minutes

                            }else if(tempEndDay == dayWithinRange){
                                if(tempEndMin == 30){
                                     DayFill(0, tempEndHour*2+1, i);
                                     break;
                                     
                                }else{
                                    DayFill(0, tempEndHour*2, i);
                                    break;
                                }
                                    
                            }    
                        }else{
                            if(tempHour < tempEndHour){
                                if(tempMin == 30){
                                    DayFill(tempHour+1, tempEndHour*2, i);
                                    break;
                                }
                                    
                                else if(tempMin == 0){
                                    DayFill(tempHour, tempEndHour*2, i);
                                    break;
                                }
                                    
                                if(tempEndMin == 30){
                                    DayFill(tempHour, tempEndHour*2+1, i);
                                    break;
                                }
                                    
                                else if(tempEndMin == 0){
                                    DayFill(tempHour, tempEndHour*2, i);    
                                    break;
                                }
                                    
                            }else{
                                if(tempMin < tempEndMin){
                                    DayFill(tempHour, tempHour+1, i);
                                    break;
                                }else{
                                    DayFill(tempHour, tempHour, i);
                                    break;
                                }
                            }
                        }      
                    }
                }
            }
        }
    }
    @Override
    public void modelUpdated(ModelVC model) {
        cm = ((CalendarModel)model);
        resetSlots();                                                           
        
        for(int i = 0;i < cm.getPlanList().size();i++){
            Plan tempPlan = ((Plan)cm.getPlanList().get(i));
            
            if(eventCheck.isSelected()){
                if(tempPlan instanceof Event)
                    EventFill(cm, tempPlan, i);
            }
            if(taskCheck.isSelected()){
                if(tempPlan instanceof Task)
                    TaskFill(cm, tempPlan, i);
            }
            
            if(eventCheck.isSelected() == false && taskCheck.isSelected() == false){
                if(tempPlan instanceof Event){                               //checks if instance of event
                    EventFill(cm, tempPlan, i);
                }else if(tempPlan instanceof Task){
                    TaskFill(cm, tempPlan, i);
                }  
            }
                                                       
        } 
    }
    
    void resetSlots(){
        for(int i = 0;i < 48;i++){
            slots.get(i).setEnabled(false);
            slots.get(i).setContentAreaFilled(false);
            slots.get(i).setText("");
        }
    }
    
    public JCheckBox getEventCheck(){
        return this.eventCheck;      
    }
    
    public JCheckBox getTaskCheck(){
        return this.taskCheck;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TodoListView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TodoListView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TodoListView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TodoListView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
        //        new TodoListView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Create;
    private javax.swing.JPanel PlannerPane;
    private javax.swing.JButton agendaButton;
    private javax.swing.JPanel agendaPane;
    private javax.swing.JTextArea agendaTime;
    private javax.swing.JTextArea agendaTitles;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton dayButton;
    private javax.swing.JScrollPane dayPane;
    private javax.swing.JCheckBox eventCheck;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane;
    private javax.swing.JLabel saikouNoPlanner;
    private javax.swing.JCheckBox taskCheck;
    private javax.swing.JPanel timePanel;
    private javax.swing.ButtonGroup viewGroup;
    private javax.swing.JLabel viewLabel;
    private javax.swing.JPanel viewPane;
    // End of variables declaration//GEN-END:variables

    
}
