/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;

import java.io.*;
import org.apache.pdfbox.pdmodel.*;
//import org.apache.pdfbox.util.*;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author dric0
 */
public class MachineLearning {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        PDDocument pdfDocument = null;
        String paper = null;
        try {
            pdfDocument = PDDocument.load(new File("/home/dric0/UEM/IA2/Trabalho2/1.pdf"));
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
        System.out.println(paper);
    }
}
