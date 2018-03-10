/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import javax.swing.JButton;

/**
 *
 * @author Ashen One
 */
public class PlanButton extends JButton{
    private Plan plan;
    
    public PlanButton(){
        
    }
    
    public void setPlan(Plan plan){
        this.plan = plan;
    }
    
    public Plan getPlan(){
        return this.plan;
    }
}
