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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    private LinkedHashMap<String, Integer> sortedFrequency;
    
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
        removeSmallWords();
        sort();
        
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
        removeSmallWords();
        sort();
        
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
    
    public Map<String, Integer> getFrequency(float porcentage) {
        Map<String, Integer> slice = new HashMap<>();
        
        porcentage = porcentage < 0 ? 0 : porcentage;
        porcentage = porcentage > 1 ? 1 : porcentage;
        
        int quantity = (int) (sortedFrequency.size()*porcentage);
        for(Iterator<Map.Entry<String, Integer>> it = 
                sortedFrequency.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            if (slice.size() == quantity) break; //lol
            slice.put(it.next().getKey(), it.next().getValue());
        }
        
        return slice;
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
    
    void removeSmallWords() {
        for(Iterator<Map.Entry<String, Integer>> it = frequency.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getKey().length() <= 2) {
                it.remove();
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
    
    public void sort() {
        List<String> mapKeys = new ArrayList<>(frequency.keySet());
        List<Integer> mapValues = new ArrayList<>(frequency.values());
        
        Collections.sort(mapValues, Collections.reverseOrder());
        Collections.sort(mapKeys,   Collections.reverseOrder());

        sortedFrequency = new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            int val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                int comp1 = frequency.get(key);
                int comp2 = val;

                if (comp1 == comp2) {
                    keyIt.remove();
                    sortedFrequency.put(key, val);
                    break;
                }
            }
        }
        //System.out.println(sortedFrequency);
    }
 }
