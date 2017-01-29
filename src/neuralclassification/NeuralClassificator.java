/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralclassification;

import java.io.*;

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
        Trainer t = new Trainer("/home/lionswrath/Documents/IA2/PDFClassificator/PDFs","trainingfiles.txt", "pdf_classificator2.nnet");
        t.trainNeuralNetwork();
        
        //Classificator c = new Classificator("/home/lionswrath/Documents/IA2/PDFClassificator/PDFs", "pdf_classificator.nnet");
        //Utils u = new Utils("/home/lionswrath/Documents/IA2/PDFClassificator/PDFs");
        
        //System.out.println(u.convertData(new double[]{0, 0, 0, 1, 0, 1, 1, 0, 1, 1}));

    }
}
