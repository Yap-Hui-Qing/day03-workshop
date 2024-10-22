package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

public class CapitaliseMain {
    public static void main(String[] args) 
    throws FileNotFoundException, IOException{
        
        String inputFile = args[0];
        String outputFile = args[1];

        // open input file for reading
        FileReader reader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // open output file for writing
        FileWriter writer = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        String line = "x";
        while (null != line){
            //read a line
            line = bufferedReader.readLine();

            //if line is null, we have reach the EOF
            if (line == null){
                break;
            }

            //System.out.printf(">>> line: %s\n", line.toUpperCase());
            String transformed = line.toUpperCase();

            //write to file
            bufferedWriter.write(transformed + "\n");
        }

        // flush remaining data to file
        bufferedWriter.flush();
        writer.flush();

        //close the files
        bufferedWriter.close();
        writer.close();
        reader.close();       
    }
}
