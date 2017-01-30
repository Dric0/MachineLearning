/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralclassification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.neuroph.core.NeuralNetwork;

/**
 *
 * @author lionswrath
 */
public class Classificator {
    
    PreProcessor PP;

    String path;
    String nnetname;
    NeuralNetwork MLPerceptron;
    
    ArrayList<String> keywords;
    
    public Classificator(String path, String name) throws IOException {
        PP = new PreProcessor(path);
        keywords = new ArrayList<>();
        
        this.path = path;
        this.nnetname = name;
        
        loadKeywords();
        loadNeuralNetwork();
    }
    
    double normalizeData(int data, int min, int max) {
        double upper = data - min;
        double lower = max - min;
        
        return upper/lower;
    }
    
    String readText(String filepath, String name) {
        PDDocument pdfDocument = null;
        String paper = null;
        try {
            pdfDocument = PDDocument.load(new File(filepath + "/" + name));
            PDFTextStripper stripper = new PDFTextStripper();
            paper = stripper.getText(pdfDocument);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (pdfDocument != null) try {
                pdfDocument.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
        return paper;
    }
    
    double[] getInput(Map<String, Integer> frequency) {
        double[] data = new double[keywords.size()];
        for (int i=0; i<keywords.size(); i++) {
            String word = keywords.get(i);

            if (frequency.containsKey(word))
                data[i] = normalizeData(frequency.get(word), 0, 100);
            else data[i] = 0;
        }
        
        return data;
    }
    
    public double[] classify(String pdfpath, String name) {
        PP.process(readText(pdfpath, name));
        double[] input = getInput(PP.getFrequency());
    
        MLPerceptron.setInput(input);
        MLPerceptron.calculate();
        
        return MLPerceptron.getOutput();
    }
    
    void loadKeywords() throws IOException {
        String[] slice = nnetname.split("\\.");
        
        try(BufferedReader br = new BufferedReader(
                new FileReader(path + "/" + slice[0] + "_kw.txt"))) {
            String line = br.readLine();
            
            while (line != null) {
                keywords.add(line);
                line = br.readLine();
            }
        }
    }
    
    void loadNeuralNetwork() {
        MLPerceptron = NeuralNetwork.createFromFile(path + "/" + nnetname);
    }
}
