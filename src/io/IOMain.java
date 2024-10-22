package io;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


public class IOMain{
    public static void main(String[] args)
     throws FileNotFoundException, IOException{

        File a = new File(args[0]);
        FileReader file = new FileReader(a);
        BufferedReader in = new BufferedReader(file);

        BufferedWriter out = new BufferedWriter(new FileWriter(args[1]));
        System.out.println("Buffered Writer start writing");

        while (true){
            String line = in.readLine();
            if (line == null){
                break;
            }
            out.write(line + "\n");
        }
        
        out.flush();
        out.close();
        System.out.println("Written successfully");
    }

}