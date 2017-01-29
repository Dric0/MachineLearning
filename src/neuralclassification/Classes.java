/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralclassification;

/**
 *
 * @author lionswrath
 */
public class Classes {
    Boolean classes[];
    private int numberOfClasses;

    public Classes(String[] data, int number) {
        numberOfClasses = number;
        classes = new Boolean[numberOfClasses];
        fill(data);
    }
    
    void fill(String[] data) {
        for (int i=1; i<numberOfClasses+1; i++) {
            classes[i-1] = stringToBool(data[i]);
        }
    }
    
    Boolean verifyClass(int idx) {
        return classes[idx];
    }
    
    double[] toData() {
        double[] data = new double[numberOfClasses];
        
        for (int i=0; i<numberOfClasses; i++) {
            if (classes[i])
                data[i] = 1;
            else
                data[i] = 0;
        }
        
        return data;
    }
    
    public static boolean stringToBool(String s) {
        if (s.equals("1")) return true;
        if (s.equals("0")) return false;
        
        throw new IllegalArgumentException(s + " Not a bool.");
    }
    
    @Override
    public String toString() {
        String print = new String();
        for (Boolean bool: classes) {
            print += bool.toString() + " ";
        }
        
        return print;
    }
}
