/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralclassification;

import java.io.*;
import org.apache.pdfbox.pdmodel.*;
//import org.apache.pdfbox.util.*;
import org.apache.pdfbox.text.PDFTextStripper;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

/**
 *
 * @author dric0
 */
public class NeuralClassificator {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        PDDocument pdfDocument = null;
        String paper = null;
        try {
            pdfDocument = PDDocument.load(new File("/home/lionswrath/Documents/IA2/PDFClassificator/PDFs/2.pdf"));
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
        
        Trainer t = new Trainer("/home/lionswrath/Documents/IA2/PDFClassificator/PDFs","trainingfiles.txt", "pdf_classificator.nnet");
        t.trainNeuralNetwork();
        
//        // create new perceptron network
//        NeuralNetwork neuralNetwork = new Perceptron(2, 1);
//        // create training set
//        DataSet trainingSet = new DataSet(2, 1);
//        // add training data to training set (logical OR function)
//        trainingSet. addRow (new DataSetRow (new double[]{0, 0}, new double[]{0}));
//        trainingSet. addRow (new DataSetRow (new double[]{0, 1}, new double[]{1}));
//        trainingSet. addRow (new DataSetRow (new double[]{1, 0}, new double[]{1}));
//        trainingSet. addRow (new DataSetRow (new double[]{1, 1}, new double[]{1}));
//        // learn the training set
//        neuralNetwork.learn(trainingSet);
//        // save the trained network into file
//        neuralNetwork.save("or_perceptron.nnet");
//        
//        // load the saved network
//        neuralNetwork = NeuralNetwork.createFromFile("or_perceptron.nnet");
//        // set network input
//        neuralNetwork.setInput(0, 1);
//        // calculate network
//        neuralNetwork.calculate();
//        // get network output
//        double[] networkOutput = neuralNetwork.getOutput();
//        
//        System.out.println(networkOutput[0]);
    }
}
