package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// remove the stop words
// https://gist.github.com/sebleier/554280

public class WordCountMain {
    public static void main(String[] args) 
        throws FileNotFoundException, IOException{
        
        String inputFile = args[0];

        // open input file for reading
        FileReader reader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // create a map
        Map<String, Integer> uniqueWords = new HashMap<>();

        String line = "x";
        while (null != line){
            //read a line
            line = bufferedReader.readLine();

            //if line is null, we have reach the EOF 
            if (line == null){
                break;
            }

            //System.out.printf(">>> line: %s\n", line.toUpperCase());
            String transformed = line.replaceAll("\\p{Punct}", "").toLowerCase().trim();

            for (String word : transformed.split(" ")){

                int currentCount = 0;
                if (uniqueWords.containsKey(word))
                    currentCount = uniqueWords.get(word);
                
                currentCount += 1;

                uniqueWords.put(word, currentCount);

                // word is in the list
                // if (uniqueWords.containsKey(word)) {
                //     int currentCount = uniqueWords.get(word);
                //     currentCount += 1;
                //     uniqueWords.put(word, currentCount);
                // } else {
                //     // word is not in the list
                //     uniqueWords.put(word, 1);
                // }
            }
        }

        //close the file
        reader.close();

        System.out.println();

        // print the key set in alphabetical order
        for (String word : uniqueWords.keySet()){
            System.out.printf("%s\t\t %d\n", word, uniqueWords.get(word));
        }
    
    }
}
