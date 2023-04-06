/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aquaticlazer;


import Entry.Entries;
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
        Entries a[] = new Entries[list.length];
        Entries b[] = new Entries[list.length];
        int c = 0;
        int d = lines;
        int num;
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                if (list[i].isDirectory == false) {
                    a[i] = new Entries();
                    a[i].file = list[i].file;
                    a[i].contents = list[i].contents;
                    List<String> tmpList = new ArrayList<String>(Arrays.asList(a[i].contents));
                    if (a[i] != null) {
                        long file_num = tmpList.size() / lines;
                        if (tmpList.size()%lines > 0) 
                            file_num = file_num + 1;
                        for (int j = 0; j < file_num; j++) {
                            num = j + 1;
                            File file = new File(a[i].file.getName() + num + ".txt");
                            if (file.createNewFile()) {
                                try {
                                    String tmpStringAry[] = new String[lines];
                                    FileWriter mywriter = new FileWriter(file);
                                    for (int k = 0; k < lines && k < tmpList.size(); k++) {
                                        String curLine = tmpList.get(k);
                                        tmpStringAry[k] = curLine;
                                        mywriter.write(curLine);
                                    }
                                    mywriter.close();
                                    for (int k=0; k <tmpStringAry.length; k++) {
                                        tmpList.remove(tmpStringAry[k]);
                                    }
                                } catch (IOException ex) {
                                    System.out.println("an error occurred.");
                                    ex.printStackTrace();
                                }
                                //}
                                //}
                            } else {
                                System.out.println("File already exists");
                            }
                            b[j] = new Entries();
                            b[j].file = file;
                            
                        }
                    }
                } else {
                    System.out.println("This is a directory");
                }
            }
        }
        return b;
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
        String[] names = new String[list.length];
        File a[] = new File[list.length];
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                a[i] = list[i].file;
            }
        }
        for (int j = 0; j < a.length; j++) {
            if (a[j] != null) {
                list[j].file = new File(a[j].getName() + Suffix + ".txt");
            }
        }
        return list;
    }

         
      }
