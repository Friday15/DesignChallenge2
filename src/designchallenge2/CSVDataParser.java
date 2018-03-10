/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ashen One
 */
public class CSVDataParser extends DataParser{
    private final ArrayList <String []> lines = new ArrayList();
    public CSVDataParser(){
        
    }
    @Override
    void readData(String name){
        String csvFile = name;
        String line = "";
        String cvsSplitBy = ",";
       
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {     

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String [] events = line.split(cvsSplitBy);
                
                lines.add(events);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    void readData(){
        System.out.println("Reading data from csv file");
        String csvFile = "F:\\Documents\\NetBeansProjects\\DesignChallenge1\\src\\designchallenge1\\Event List.csv";               
        String line = "";
        String cvsSplitBy = ",";
       
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {     

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String [] events = line.split(cvsSplitBy);
                System.out.println(events[0] + events[1] + events[2]);
                lines.add(events);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    
    public ArrayList getEvents(){                                           
        return this.lines;
    }
}
