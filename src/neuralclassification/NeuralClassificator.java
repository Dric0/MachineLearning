/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralclassification;

import java.io.*;
import neuralinterface.MainMenu;
import neuralinterface.TrainMenu;
import neuralinterface.TrainMenu;

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
        /*Trainer t = new Trainer("/home/lionswrath/Documents/IA2/PDFClassificator/PDFs","trainingfiles.txt", "pdf_classificator2.nnet");
        t.calculateNeuralNetwork((float)0.1);
        t.configureNeuralNetwork((float)0.01, (float)0.4, 1000);
        t.trainNeuralNetwork();
        t.finishNeuralNetwork();
        
        //Classificator c = new Classificator("/home/lionswrath/Documents/IA2/PDFClassificator/PDFs", "pdf_classificator3.nnet");
        //Utils u = new Utils("/home/lionswrath/Documents/IA2/PDFClassificator/PDFs");
        
        //System.out.println(u.convertData(c.classify("/home/lionswrath/Documents/IA2/PDFClassificator/PDFs", "2.pdf")));
        */
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }
}
