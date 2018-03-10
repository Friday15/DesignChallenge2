/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

/**
 *
 * @author Arturo III
 */
public class DesignChallenge2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CalendarModel cm = new CalendarModel();
        //EventView ev = new  EventView();
        CalendarController cont = new CalendarController(cm);
        //CalendarProgramView cp = new CalendarProgramView(cm, cont);
        
        //TodoListView tlv = new TodoListView();
        //tlv.setVisible(true);
    }
}
