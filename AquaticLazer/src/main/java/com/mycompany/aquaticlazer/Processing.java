/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aquaticlazer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

/**
 *
 * @author macdm
 */
public class Processing {
    
    
    int lines_num;
    
   void Split(String Entry[],int lines) throws IOException{
       int count = 1;
       int files = lines_num/lines;
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
                           mywrite.write(Entry[j]);
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
               e.printStackTrace();
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
   void List(File Entry[],int max,int id[]){
       File Directories[] = null;
       for(int i = 0; i < Entry.length;i++){
           if(id[i]==0){
               Directories[i] = Entry[i];
           }
       }
       for(int j = 0; j <Directories.length;j++){
           File a[];
           a= Directories[j].listFiles();
           for(int k = 0; k < max;k++){
               System.out.println(a[k]);
           }
       }
   }
   void Rename(String Entry[],String suffix){
       for(int j = 0; j < Entry.length;j++){
            String filename = Entry[j];
            String [] array= filename.split(".");
            Entry[j] = array[0] + suffix + array[1];  
       }
   }
   void Print(String Entry_repo[],String Entry_repo1[],String Entry_repo2[],String Entry_repo3[],int id, String Entry[],String Entry1[],String Entry2[]){
      if(id==0){
       for(int i = 0; i < Entry.length;i++){
           System.out.println(Entry[i]+" "+Entry1[i]+" "+Entry2[i]);
       }
      }
      if(id==1){
          for(int j = 0; j< Entry_repo.length; j++){
              System.out.println(Entry_repo[j]+" "+Entry_repo1[j]+" "+Entry_repo2[j]+" "+Entry_repo3[j]);
          }
      }
   }
   
   
}
