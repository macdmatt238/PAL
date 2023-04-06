/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aquaticlazer;

//sfe
import Entry.Entries;
import Entry.LocalEntry;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author macdm
 */   
public class Processing {
   Entries[] Split(Entries list[], int lines) throws IOException {
        ArrayList<Entries> output = new ArrayList<Entries>();
        File outputFile;
        String outputPath;
        for (Entries i : list) { // goes through everything in the list
            try {
                int fileNum = 1;
                int lineNum = 0;
                while (lineNum < i.contents.length) { // only goes until the end of the file
                // create a new file with a unique name
                outputPath = i.path.substring(i.path.lastIndexOf(".")); // finds the .txt at the end of the file name
                outputFile = new File(outputPath + fileNum + ".txt"); // inserts the number in front of the .txt
                outputFile.createNewFile();

                // write the lines to the file
                try (FileWriter writer = new FileWriter(outputFile)) {
                    int linesWritten = 0;
                    while (lineNum < i.contents.length && linesWritten < lines) { // goes until the end of the number of lines listed or the end of the file
                        writer.write(i.contents[lineNum] + "\n"); // writes it to the file
                        lineNum++;
                        linesWritten++;
                    }
                }
                output.add(new LocalEntry(outputFile.getPath())); // adds the completed file to the output
                fileNum++;
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
           
        }
        return output.toArray(new Entries[0]); 
    }

   
   Entries[] List(Entries[] Entries,long max){
       
       
       ArrayList<Entries> output = new ArrayList<Entries>();
       Entries[] outputList;
       int Directorysize = 0;
       for(int i = 0; i < Entries.length;i++){
           if(Entries[i].isDirectory == true){
               Directorysize++;
           }
       }
       Entries[] Directories =  new Entries[Directorysize];
       long a_size = Directorysize;
       for(int i = 0; i < Entries.length;i++){
           if(Entries[i].isDirectory == true){
               Directories[i] = Entries[i];
           }
       }
       
       for (int i = 0; i < Directories.length; i++) {
           for (int j = 1; j < max+1 && j < Directories[i].contents.length; j++) {
               output.add(Directories[i].directoryContents[j]);
           }
       }
       outputList = new Entries[output.size()];
       
       for (int i = 0; i < output.size(); i++) {
           outputList[i] = output.get(i);
       }
       return outputList;
       
       
   }
  Entries[] Rename(Entries list[], String Suffix) {
         ArrayList<Entries> output = new ArrayList<Entries>();
        File newFile;
        String newFileName;
        for (Entries i: list) { // goes through everything in the list
            String fileName = i.file.getName();
            if (fileName.endsWith(".txt")) { // if the file ends in .txt then it has to be handled appropriatley
                newFileName = fileName.substring(0,fileName.lastIndexOf(".txt")) + Suffix + ".txt"; // creates the new file name
                newFile = new File(i.file.getParentFile() + newFileName); // makes the file object needed to rename the old file with the new name
            } else { // this is if there is no .txt file at the end
                newFileName = fileName + Suffix + ".txt";
                newFile = new File(i.file.getParentFile() + newFileName); // makes the new file object since the one up there didn't run since this is the else
            }
            
            if (!i.file.renameTo(newFile)) { // shouldn't run but it tries to rename the file and if it fails it will enter this if statement
                System.out.println("Failed to rename " + i.file.getName()); // tells the console it failed
                output.add(new LocalEntry(i.path)); // adds the old object back
            } else {
                output.add(new LocalEntry(i.file.getParentFile() + newFileName)); // adds the renamed file
            }
        }
        return output.toArray(new Entries[0]);
    }

         
      }
