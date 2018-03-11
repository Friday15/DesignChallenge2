/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import java.awt.Color;
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
        String coloredname ="<html> <font color = ";
        if(plan instanceof Event){
            coloredname = coloredname + "Blue> ";
            this.setBackground(Color.BLUE);
        }else{
            coloredname = coloredname + "Green> ";
            this.setBackground(Color.GREEN);
        }
        coloredname = coloredname + plan.getName()+" </font> <br> </html>";
        plan.setColoredName(coloredname);
    }
    public void planEnded(){
        String coloredname ="<html> <font color = ";
        if(plan instanceof Event){
            coloredname = coloredname + "Gray> ";
            this.setBackground(Color.GRAY);
        }else{
            coloredname = coloredname + "Gray> ";
            this.setBackground(Color.GRAY);
        }
        this.setEnabled(false);
    }
    
    public Plan getPlan(){
        return this.plan;
    }
}
