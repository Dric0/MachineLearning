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
        //Trainer t = new Trainer("/home/lionswrath/Documents/IA2/PDFClassificator/PDFs","trainingfiles.txt", "pdf_classificator.nnet");
        //t.trainNeuralNetwork();
        
        Classificator c = new Classificator("/home/lionswrath/Documents/IA2/PDFClassificator/PDFs", "pdf_classificator.nnet");

    }
}
