/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ashen One
 */
//F:\\Documents\\NetBeansProjects\\DesignChallenge1\\src\\designchallenge1\\Event List.csv
//C:\\Users\\Mart\\Desktop\\temp folder\\swdespa calendar\\DesignChallenge2\\src\\designchallenge2\\Event List.csv
public class DataWriter {
    FileWriter fw = null;
    public void writeData(ArrayList<Plan> planList){
        char separator = ',';
        try {
           fw = new FileWriter("C:\\Users\\Mart\\Desktop\\temp folder\\swdespa calendar\\DesignChallenge2\\src\\designchallenge2\\Event List.csv");
            
            for(int i = 0; i < planList.size(); i++){
                fw.append(planList.get(i).getString(separator)); 
                fw.append("\n");
                
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
            
        }finally{
            try {
                fw.flush();
                fw.close();
                
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
        
    }
}
