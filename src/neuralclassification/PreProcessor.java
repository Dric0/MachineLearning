/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralclassification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author lionswrath
 */
public class PreProcessor {

    private String originalText;
    private String processedText;
    private ArrayList<String> separatedText;
    private ArrayList<String> stopWords;
    private Map<String, Integer> frequency;
    
    private WordChecker WC;
    
    public PreProcessor() throws IOException {
        frequency = new HashMap<>();
        stopWords = new ArrayList<>();
        
        WC = new WordChecker();
        
        loadStopWords();
    }
    
    public PreProcessor(String text) throws IOException {
        originalText = text.toLowerCase();
        stopWords = new ArrayList<>();
        
        WC = new WordChecker();
        
        loadStopWords();
    }
    
    public ArrayList<String> process(String text) {
        originalText = text.toLowerCase();
        removeAllSymbols();
        splitAllWords();
        removeStopWords();
        removeEmptyWords();
        removeNonLanguageWord();
        processFrequency();
        removeLowFrequency();
        
        return separatedText;
    }
    
    public ArrayList<String> process() {    
        removeAllSymbols();
        splitAllWords();
        removeStopWords();
        removeEmptyWords();
        removeNonLanguageWord();
        processFrequency();
        removeLowFrequency();
        
        return separatedText;
    } 
    
    void addFrequency(String word) {
        int count = frequency.containsKey(word) ? frequency.get(word) : 0;
        frequency.put(word, count + 1);
    }
    
    void processFrequency() {
        for (String word: separatedText) {
            addFrequency(word);
        }
    }

    public Map<String, Integer> getFrequency() {
        return frequency;
    }
    
    public String getProcessedText() {
        return processedText;
    }

    public ArrayList<String> getSeparatedText() {
        return separatedText;
    }

    public String getOriginalText() {
        return originalText;
    }

    public ArrayList<String> getStopWords() {
        return stopWords;
    }
    
    void removeAllSymbols() {
        processedText = originalText.replaceAll("[^\\p{L} ]+", "");
    }
    
    void splitAllWords() {
        String[] temporaryText = processedText.split("\\s+");
        separatedText = new ArrayList( Arrays.asList(temporaryText) );
    }
    
    void removeNonLanguageWord() {
        Iterator<String> it = separatedText.iterator();
        while (it.hasNext()) {
            if (WC.checkAll(it.next())) {
                it.remove();
            }
        }
    }
    
    void removeWord(String word) {
        Iterator<String> it = separatedText.iterator();
        while (it.hasNext()) {
            if (word.equals(it.next())) {
                it.remove();
            }
        }
    }
    
    void removeEmptyWords() {
        Iterator<String> it = separatedText.iterator();
        while (it.hasNext()) {
            if (it.next().length() == 0) {
                it.remove();
            }
        }
    }
    
    void removeStopWords() {
        for (String word : stopWords) {
            if (separatedText.contains(word)) {
                removeWord(word);
            }
        }
    }
    
    void removeLowFrequency() {
        frequency.values().removeAll(Collections.singleton(1));
    }
    
    private void loadStopWords() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("/home/lionswrath/Documents/IA2/stopwords.txt"))) {
            String line = br.readLine();

            while (line != null) {
                stopWords.add(line);
                line = br.readLine();
            }
        }
    }
 }
