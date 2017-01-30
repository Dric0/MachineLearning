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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;

/**
 *
 * @author lionswrath
 */
public class Trainer {
    
    PreProcessor PP;

    String filepath;
    String basefile;
    String trainingfile;
    
    Map<String, Classes> texts;
    ArrayList<String> textNames;
    ArrayList< Map<String, Integer> > frequencys;
    ArrayList<String> keywords;
    
    DataSet trainingSet;
    MultiLayerPerceptron MLPerceptron;
    
    int inputNeurons;
    int outputNeurons;
    int hiddenNeurons;
    
    public Trainer(String filepath, String file, String output) throws IOException {
        PP = new PreProcessor();
        texts = new HashMap<>();
        textNames = new ArrayList<>();
        frequencys = new ArrayList<>();
        keywords = new ArrayList<>();
        
        this.filepath = filepath;
        this.basefile = file;
        this.trainingfile = output;
    }
    
    String readText(String name) {
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
    
    double normalizeData(int data, int min, int max) {
        double upper = data - min;
        double lower = max - min;
        
        return upper/lower;
    }
    
    void processTexts(float porcentage) {
        //Testing
        for (String name : texts.keySet()) {
            PP.process(readText(name));
            frequencys.add(PP.getFrequency(porcentage));
        }
    }
    
    void processKeywords() {
        for (Map<String, Integer> frequency : frequencys) {
            for (String word : frequency.keySet()) {
                if (!keywords.contains(word)) {
                    keywords.add(word);
                }
            }
        }
    }
    
    void calculateHiddenNeurons() {
        hiddenNeurons = 1;
    }
    
    void calculateInputNeurons() {
        inputNeurons = keywords.size();
    }
    
    void calculateOutputNeurons() throws IOException {
        try(BufferedReader br = new BufferedReader(
                new FileReader(filepath + "/" + basefile))) {
            String line = br.readLine();
            String[] temp = line.split("\\s+");
            
            outputNeurons = temp.length - 1;
        }
    }
    
    void createTrainingSet() {
        trainingSet = new DataSet(inputNeurons, outputNeurons);
        
        int textCounter = 0;
        for (Map<String, Integer> frequency : frequencys) {
            double[] data = new double[keywords.size()];
            for (int i=0; i<keywords.size(); i++) {
                String word = keywords.get(i);
                
                if (frequency.containsKey(word))
                    data[i] = normalizeData(frequency.get(word), 0, 100);
                else data[i] = 0;
            }
            
            String name = textNames.get(textCounter);
            trainingSet.addRow( new DataSetRow(data, texts.get(name).toData()) );
            
            textCounter++;
        }
    }
    
    void calculateNeuralNetwork(float porcentage) throws IOException {
        calculateOutputNeurons();
        //System.out.println(outputNeurons);
        loadAllTexts();
        //System.out.println(texts);
        //System.out.println(textNames);
        processTexts(porcentage);
        //System.out.println(frequencys);
        processKeywords();
        //System.out.println(keywords);
        calculateInputNeurons();
        //System.out.println(inputNeurons);
        calculateHiddenNeurons();
        //System.out.println(hiddenNeurons);
        createTrainingSet();
    }
    
    void configureNeuralNetwork(float rate, float momentum, int iterations) {
        MLPerceptron = new MultiLayerPerceptron(
                TransferFunctionType.TANH, 
                inputNeurons, 
                hiddenNeurons, 
                outputNeurons);
        
        MomentumBackpropagation learningRule = 
                (MomentumBackpropagation) MLPerceptron.getLearningRule();
        learningRule.setLearningRate(rate);
        learningRule.setMomentum(momentum);
        learningRule.setMaxIterations(iterations);
    }
    
    void trainNeuralNetwork() {
        MLPerceptron.learn(trainingSet);
    }
    
    void stopNeuralNetwork() {
        MLPerceptron.stopLearning();
    }
    
    void pauseNeuralNetwork() {
        MLPerceptron.pauseLearning();
    }
    
    void resumeNeuralNetwork() {
        MLPerceptron.resumeLearning();
    }
    
    void finishNeuralNetwork() {
        MLPerceptron.save(filepath + "/" + trainingfile);
        saveKeywords();
    }
    
    void loadAllTexts() throws IOException{
        try(BufferedReader br = new BufferedReader(
                new FileReader(filepath + "/" + basefile))) {
            String line = br.readLine();
            
            while (line != null) {
                String[] temp = line.split("\\s+");
                texts.put(temp[0], new Classes(temp, outputNeurons));
                textNames.add(temp[0]);
                
                line = br.readLine();
            }
        }
    }
    
    void printLoadedTextsData() {
        System.out.println(texts);
    }
    
    void saveKeywords() {
        String[] slice = trainingfile.split("\\.");
        
        try{
            PrintWriter writer = new PrintWriter(filepath + "/" + slice[0] + "_kw.txt", "UTF-8");
            for (String word : keywords) {
                writer.println(word);
            }
            writer.close();
        } catch (IOException e) {}
    }
}
