/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralclassification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author lionswrath
 */
public class Utils {

    String strpath;
    ArrayList<String> classes;
    
    public Utils(String strpath) {
        this.strpath = strpath;
        classes = new ArrayList<>();
        
        try {
            loadClasses();
        } catch (IOException e) {}
    }
    
    void loadClasses() throws IOException {
        try(BufferedReader br = new BufferedReader(
                new FileReader(strpath + "/" + "classes.txt"))) {
            String line = br.readLine();
            
            while (line != null) {
                classes.add(line);
                line = br.readLine();
            }
        }
    }
    
    public ArrayList<String> convertData(double[] data) {
        ArrayList<String> strdata = new ArrayList<>();
        
        for (int i=0; i<data.length; i++) {
            if (data[i] == (double) 1)
                strdata.add(classes.get(i));
        }
        
        return strdata;
    }
}
