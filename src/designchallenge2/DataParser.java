/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import java.util.ArrayList;

/**
 *
 * @author Emir
 */
abstract public class DataParser {
    abstract void readData();
    abstract void readData(String name);
    abstract ArrayList getEvents();
}
