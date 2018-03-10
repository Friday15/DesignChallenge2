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
public class Task extends Plan{
    private boolean done;
    
    public Task(String name, Date s){
        super(name, s);
        done = false;
    }
    
    public boolean getDone(){
        return done;
    }
    
    public void checkDone(){
        done = true;
    }
}
