/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralclassification;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lionswrath
 */
class WordChecker {

    BufferedReader be;
    BufferedReader br;
    BufferedReader ae;
    
    Map<String, Integer> consult_be;
    Map<String, Integer> consult_br;
    Map<String, Integer> consult_ae;
    
    public WordChecker() throws FileNotFoundException {
        be = new BufferedReader(new FileReader("/usr/share/dict/british-english"));
        br = new BufferedReader(new FileReader("/usr/share/dict/brazilian"));
        ae = new BufferedReader(new FileReader("/usr/share/dict/american-english"));
        
        consult_be = new HashMap<>();
        consult_br = new HashMap<>();
        consult_ae = new HashMap<>();
        
        loadBE();
        loadBR();
        loadAE();
    }
    
    public boolean checkAll(String word) {
        if (checkBE(word) || checkBR(word) || checkAE(word)) {
            return true;
        }
        return false;
    }
    
    public boolean checkBE(String word) {
        if (consult_be.containsKey(word)) return true;
        return false;
    }
    
    public boolean checkBR(String word) {
        if (consult_br.containsKey(word)) return true;
        return false;
    }
    
    public boolean checkAE(String word) {
        if (consult_ae.containsKey(word)) return true;
        return false;
    }
    
    public void loadBE() {
        try {
            String str;
            while ((str = be.readLine()) != null) {
                consult_be.put(str, 0);
            }
            be.close();
        } catch (IOException e) {}
    }
    
    public void loadBR() {
        try {
            String str;
            while ((str = br.readLine()) != null) {
                consult_br.put(str, 0);
            }
            br.close();
        } catch (IOException e) {}
    }
    
    public void loadAE() {
        try {
            String str;
            while ((str = br.readLine()) != null) {
                consult_ae.put(str, 0);
            }
            br.close();
        } catch (IOException e) {}
    }
}
