package javaapplication2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
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
    private ArrayList<String> INpath = new ArrayList<>();
    private ArrayList<String> repositoryid = new ArrayList<>();
    private ArrayList<String> entryid = new ArrayList<>();
    private ArrayList<String> PEType = new ArrayList<>();
    private ArrayList<String> INType = new ArrayList<>();
    private ArrayList<String> ParName = new ArrayList<>();
    private ArrayList<String> ParValue = new ArrayList<>();
    
   JsonFileReader(File file){
       
       JSONParser Jparser = new JSONParser();
       
       
       try{
           
           Object obj = Jparser.parse(new FileReader(file));
           JSONObject jobj = (JSONObject) obj;
           this.name = (String) jobj.get("name");
           
           
           JSONArray processingElements = (JSONArray) jobj.get("processing_elements");
           
           processingElements.forEach(emp -> parseprocessingElements((JSONObject)emp));
           
           
           
           
           
       }
       catch(FileNotFoundException e){}
       catch(IOException | org.json.simple.parser.ParseException e){}
   }
   
   public Object[] GetPath(){
       
        return INpath.toArray();
       
   }
   
   public String GetName(){
       
        return this.name;
       
   }
   
   public Object[] GetPEType(){
       
        return PEType.toArray();
       
   }
   
   public Object[] GetINType(){
       
        return INType.toArray();
       
   }
   
   public Object[] GetRepositoryId(){
       
        return repositoryid.toArray();
       
   }
   
   public Object[] GetEntryId(){
       
        return entryid.toArray();
       
   }
   
   public Object[] GetParName(){
       
        return ParName.toArray();
       
   }
   
   public Object[] GetParValue(){
       
        return ParValue.toArray();
       
   }
   
   private void parseprocessingElements(JSONObject emp){
       
       PEType.add((String) emp.get("type"));
       
       JSONObject jINobj = (JSONObject) emp.get("input_entries");
       
      INType.add((String) jINobj.get("type"));
       if(INType.get(0).equals("local") == true){
               repositoryid.add("null");
               entryid.add("null");
               INpath.add((String) jINobj.get("path"));
           }else{
               INpath.add("null");
               repositoryid.add((String) jINobj.get("repositoryid"));
               entryid.add((String) jINobj.get("entryid"));
             
           }
       
       JSONObject jPARobj = (JSONObject) emp.get("parameters");
       
       ParName.add((String) jPARobj.get("name"));
       ParValue.add((String) jPARobj.get("value"));
   }
}