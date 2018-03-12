/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Emir
 */
public class PSVDataParser extends DataParser{
    private final ArrayList <String []> lines = new ArrayList();
    public PSVDataParser(){
        
    }
    @Override
    void readData(String name){
        System.out.println("Reading data from csv file");
        String psvFile = name;               
        String line = "";
        String pvsSplitBy = "\\|";
       
        try (BufferedReader br = new BufferedReader(new FileReader(psvFile))) {    

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String [] events = line.split(pvsSplitBy);
                
                lines.add(events);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    @Override
    void readData(){
        System.out.println("Reading data from psv file");
        String psvFile = "F:\\Documents\\NetBeansProjects\\DesignChallenge1\\src\\designchallenge1\\DLSU Unicalendar.psv";               
        String line = "";
        String pvsSplitBy = " \\| ";
       
        try (BufferedReader br = new BufferedReader(new FileReader(psvFile))) {     

            while ((line = br.readLine()) != null) {

                String [] events = line.split(pvsSplitBy);
                System.out.println(events[0] + events[1] + events[2]);
                lines.add(events);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    
    public ArrayList getPlans(){                                           
        return this.lines;
    }
}
