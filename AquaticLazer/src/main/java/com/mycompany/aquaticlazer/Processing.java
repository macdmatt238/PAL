/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aquaticlazer;

import Entry.Entries;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author macdm
 */
public class Processing {
    
  void Split(Entries [] ListofEntries,int lines) throws IOException{
       int count = 1;
       int files = ListofEntries.length/lines;
       int c = 0;
       int d = lines;
       if (lines > 1){
         for(int i = 0;i < files;i++){ 
           try{
               File myObj = new File ("file"+count+".txt");
               if (myObj.createNewFile()){
                   System.out.print("File created: "  + myObj.getName());
                   for(int j = c; j < d; j++){
                       try (FileWriter mywrite = new FileWriter(myObj.getName())) {
                           mywrite.write(ListofEntries[j].contents[j]);
                           mywrite.close();
                       }
                   }
               }
               else{
                   System.out.println("File already exists.");
               }
           }
           catch(IOException e){
               System.out.println("an error occurred.");
           }
           c = c + lines;
           d = d + lines;
           count++;
       }
       }
       else{
           System.out.println("No Lines in the file");
       }
   }
   Entries[] List(Entries Entries[],int max){
       Entries[] a = null;
       Entries Directories[] = null;
       for(int i = 0; i < Entries.length;i++){
           if(Entries[i].isDirectory == true){
               Directories[i] = Entries[i];
           }
       }
       for(int j = 0; j <Directories.length;j++){
           for(int k = 0; k < Directories[j].directoryContents.length;k++){
               System.out.println(Directories[j].directoryContents[k]);
               a[k] = Directories[j].directoryContents[k];
           }
       }
       return a;
   }
   Entries [] Rename(Entries[] Entries,String suffix){
       Entries renamed [] =  null;
       for(int j = 0; j < Entries.length;j++){
            Entries filename = Entries[j];
            Entries [] array= filename.split(".");
            renamed[j] = array[0] + suffix + array[1];  
       }
       return Entries[];
   }
   void Print(Entries [] Entries){
       for(int i = 0; i < Entries.length;i++){
           System.out.println(Entries[i].name+" "+Entries[i].path+" "+Entries[i].length);
       }
    }
   void PrintOverride(Entries [] Entries){
       for(int i = 0; i < Entries.length;i++){
           System.out.println(Entries[i].name+" "+Entries[i].path+" "+Entries[i].length + " "Entries[i].repositoryID);
       }
   }
}
