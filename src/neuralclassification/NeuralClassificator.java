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
//        String pdfpath = "/home/lionswrath/Documents/IA2/PDFClassificator/PDFs";
//        
//        Trainer t = new Trainer(pdfpath,"trainingfiles.txt", "pdf_classificator2.nnet");
//        t.calculateNeuralNetwork((float) 0.2);
//        t.configureNeuralNetwork((float)0.001, (float)0.3, 100000);
//        t.trainNeuralNetwork();
//        t.finishNeuralNetwork();
//        
//        Utils u = new Utils(pdfpath);
//        Classificator c = new Classificator(pdfpath, "pdf_classificator2.nnet");
//        
//        System.out.println(u.convertData(c.classify(pdfpath, "92.pdf")));
//        System.out.println(u.convertData(c.classify(pdfpath, "95.pdf")));
//        System.out.println(u.convertData(c.classify(pdfpath, "102.pdf")));
//        System.out.println(u.convertData(c.classify(pdfpath, "118.pdf")));
//        System.out.println(u.convertData(c.classify(pdfpath, "122.pdf")));
//        System.out.println(u.convertData(c.classify(pdfpath, "170.pdf")));
//        System.out.println(u.convertData(c.classify(pdfpath, "194.pdf")));
//        System.out.println(u.convertData(c.classify(pdfpath, "205.pdf")));
//        System.out.println(u.convertData(c.classify(pdfpath, "227.pdf")));
//        System.out.println(u.convertData(c.classify(pdfpath, "257.pdf")));
//        System.out.println(u.convertData(c.classify(pdfpath, "276.pdf")));
//        System.out.println(u.convertData(c.classify(pdfpath, "323.pdf")));
        
        
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }
}
