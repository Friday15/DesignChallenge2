/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import java.util.Date;



/**
 *
 * @author Ashen One
 */
public class Event extends Plan{                                                            //The specs of the event itself
    private boolean shown;
    public Event(String name, Date S, Date E, Boolean D){
        super(name, S, E, D);
        shown = false;
    }
    
    public boolean getShown(){
        return shown;
    }
    
    public void checkShown(){
        shown = true;
    }
}
