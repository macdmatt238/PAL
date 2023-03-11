package javaapplication2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author macdm
 */
public class JsonFileReader {

    private String name;
    private String type;
    private String path;
    private String repositoryid;
    private String entryid;
    
   JsonFileReader(File file){
       
       JSONParser Jparser = new JSONParser();
       
       
       try{
           
           Object obj = Jparser.parse(new FileReader(file));
           JSONObject jobj = (JSONObject) obj;
           this.name = (String) jobj.get("name");
           this.type = (String) jobj.get("type");
           
           if(type.equals("local") == true){
               this.repositoryid = "null";
               this.entryid = "null";
               this.path = (String) jobj.get("path");
           }else{
               this.path = "null";
               this.repositoryid = (String) jobj.get("repositoryid");
               this.entryid = (String) jobj.get("entryid");
             
           }
           
           
       }
       catch(FileNotFoundException e){}
       catch(IOException | org.json.simple.parser.ParseException e){}
   }
   
   public String GetPath(){
       
        return this.path;
       
   }
   
   public String GetName(){
       
        return this.name;
       
   }
   
   public String GetType(){
       
        return this.type;
       
   }
   
   public String GetRepositoryId(){
       
        return this.repositoryid;
       
   }
   
   public String GetEntryId(){
       
        return this.entryid;
       
   }
   
}