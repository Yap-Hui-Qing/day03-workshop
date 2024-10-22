package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

// remove the stop words
// https://gist.github.com/sebleier/554280

public class UniqueWordMain {
    public static void main(String[] args) 
    throws FileNotFoundException, IOException{
        
        String inputFile = args[0];

        // open input file for reading
        FileReader reader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(reader);


        // create a set of strings
        // <> generics
        Set<String> uniqueWords = new HashSet<>();


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
                uniqueWords.add(word);
            }
        }

        //close the file
        reader.close();

        System.out.printf("Unique words in %s: %d\n", inputFile, uniqueWords.size());

        for (String word : uniqueWords){
            System.out.printf("%s, ", word);
        }
        
        System.out.println();
    }
    
}
