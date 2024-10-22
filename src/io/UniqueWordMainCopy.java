package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// remove the stop words 
// https://gist.github.com/sebleier/554280
// then sort the list of unique words in alphabetical order

public class UniqueWordMainCopy {
    public static void main(String[] args) 
    throws FileNotFoundException, IOException{
        
        String inputFile = args[0];

        String stopFile = args[1];

        // open input file for reading
        FileReader reader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // open stopwords file for reading
        FileReader readerStop = new FileReader(stopFile);
        BufferedReader bufferedReaderStop = new BufferedReader(readerStop);

        // create a set of strings
        // <> generics
        Set<String> uniqueWords = new HashSet<>();
        Set<String> stopWords = new HashSet<>();

        // read stopwords.txt
        String stopLine = "x";
        while (stopLine != null){
            // read a line 
            stopLine = bufferedReaderStop.readLine();
            if (stopLine == null){
                break;
            }

            String transformedString = stopLine.toLowerCase().trim();
            stopWords.add(transformedString);

        }

        // read catinthehat.txt
        String line = "x";
        while (null != line){
            //read a line
            line = bufferedReader.readLine();

            //if line is null, we have reach the end 
            if (line == null){
                break;
            }

            //System.out.printf(">>> line: %s\n", line.toUpperCase());
            String transformed = line.replaceAll("\\p{Punct}", "").toLowerCase().trim();

            for (String word : transformed.split(" ")){
                uniqueWords.add(word);
            }
        }

        //close the file
        reader.close();
        readerStop.close();

        System.out.printf("Unique words in %s before removal of stop words: %d\n", inputFile, uniqueWords.size());

        System.out.println("Set before removing stop words: \n");

        for (String word : uniqueWords){
            System.out.printf("%s, ", word);
        }

        System.out.println("\nWords to be removed: \n");

        for (String word : stopWords){
            System.out.printf("%s, ", word);
        }

        // remove stop words
        uniqueWords.removeAll(stopWords);

        System.out.printf("\nUnique words in %s after removal of stop words: %d\n", inputFile, uniqueWords.size());

        System.out.println("\nSet after removing stop words: \n");

        for (String word : uniqueWords){
            System.out.printf("%s, ", word);
        }


        List<String> sortedUniqueWords = new ArrayList<>();
        sortedUniqueWords = uniqueWords.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("\nUnique words sorted in alphabetical order: \n"); 
        
        for (String word : sortedUniqueWords){
            System.out.printf("%s, ", word);
        }
        
    }
    
}
