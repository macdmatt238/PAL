package com.mycompany.aquaticlazer;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author macdm
 */
public class JsonFileReader {
//sfe
    private String name;
    private ArrayList<String> PEType = new ArrayList<>();               //made varibles
    private ArrayList<ArrayList<String>> InputType = new ArrayList();
    private ArrayList<ArrayList<String>> InputRepositoryid = new ArrayList();
    private ArrayList<ArrayList<String>> InputEntryid = new ArrayList();
    private ArrayList<ArrayList<String>> InputPath = new ArrayList();
    private ArrayList<ArrayList<String>> parametersName = new ArrayList();
    private ArrayList<ArrayList<String>> parametersValue = new ArrayList();
    private int loopIndex = 0;
    
   JsonFileReader(File file) throws ParseException, IOException{
       
       JSONParser Jparser = new JSONParser();   //makes a jason parser 
       
       
       try{
           
           Object obj = Jparser.parse(new FileReader(file));    //makes a object out of the jason file
           JSONObject jobj = (JSONObject) obj;                  //turns the object into a json object
           this.name = (String) jobj.get("name");           //takes what ever name is equal to in the json file and sets the varible name equal to it
           
           
           JSONArray processingElements = (JSONArray) jobj.get("processing_elements");  //makes a array of each type
           
          // System.out.println(processingElements);
           
           processingElements.forEach(emp -> parseprocessingElementsInput((JSONObject)emp));    //loops and treats each type as its own json file.
           
           
           
           
       }
       catch(FileNotFoundException e){}
       catch(ParseException ex) {
               Logger.getLogger(JsonFileReader.class.getName()).log(Level.SEVERE, null, ex);
           }
   }
   
   public ArrayList<ArrayList<String>> GetPath(){   //gets all the private varibles a A rraylLists
       
       
    
        return InputPath;
       
   }
   
   public String GetName(){
       
        return this.name;
       
   }
   
   public ArrayList<String> GetPEType(){
       
        return PEType;
       
   }
   
   public ArrayList<ArrayList<String>> GetINType(){
      
      
        return InputType;
       
       
   }
   
   public ArrayList<ArrayList<String>> GetRepositoryId(){
       
        return InputRepositoryid;
       
   }
   
   public ArrayList<ArrayList<String>> GetEntryId(){
       
        return InputEntryid;
       
   }
   
   public ArrayList<ArrayList<String>> GetParName(){
       
        return parametersName;
       
   }
   
   public ArrayList<ArrayList<String>> GetParValue(){
       
        return parametersValue;
       
   }
   
   private void parseprocessingElementsInput(JSONObject emp){   //parses all the type files
       
     ArrayList<String> INpath = new ArrayList<>();          
     ArrayList<String> repositoryid = new ArrayList<>();
     ArrayList<String> entryid = new ArrayList<>();
     ArrayList<String> INType = new ArrayList<>();
     ArrayList<String> ParName = new ArrayList<>();
     ArrayList<String> ParValue = new ArrayList<>();
       
       PEType.add((String) emp.get("type"));    //saves the type varible in the jason file to the PEType varible
       
       JSONArray jINArray = (JSONArray) emp.get("input_entries");   //makes a array of each input entries
       
      // System.out.println(jINArray);
       
      jINArray.forEach(empIN -> Inputs((JSONObject)empIN, INpath, repositoryid, entryid, INType));  //teats each input entrie as it's own json file and loops.
      
      loopIndex = 0;
      
      InputType.add(INType);                //adds the array of a input types to a 2d array
      InputRepositoryid.add(repositoryid);  //adds the array of a Repositoryids values to a 2d array
      InputEntryid.add(entryid);            //adds the array of a input Entryids to a 2d array
      InputPath.add(INpath);                //adds the array of a input paths to a 2d array
      
      JSONArray jParArray = (JSONArray) emp.get("parameters");  //makes a array of each parameters
       
      // System.out.println(jParArray);
       
      jParArray.forEach(empPar -> parameters((JSONObject)empPar, ParName, ParValue));   //teats each parameter as it's own json file and loops.
      
      parametersName.add(ParName);  //adds the array of a paramater names to a 2d array
     // System.out.println("what the fuck is here pt1: "+ParName);
     //  System.out.println("what the fuck is here pt2: "+parametersName);
      
      parametersValue.add(ParValue);    //adds the array of a paramater values to a 2d array
      
     

   }
   private void parameters(JSONObject emp, ArrayList<String> ParName, ArrayList<String> ParValue){  //the function that loops for each peramater
         
       //System.out.println(emp.get("parameters"));
       
       
       ParName.add((String) emp.get("name"));   //saves the name varible in the jason file to the ParName varible
       ParValue.add((String) emp.get("value")); //saves the value varible in the jason file to the ParValue varible
   
   }
     private void Inputs(JSONObject empIN, ArrayList<String> INpath, ArrayList<String> repositoryid, ArrayList<String> entryid, ArrayList<String> INType){  //the function that loops for each input entrie
       

     INType.add((String) empIN.get("type"));                         //saves the type varible in the jason file to the INType varible
       if(INType.get(loopIndex).equals("local") == true){
               repositoryid.add("null");                              //saves the null varible in the jason file to the repositoryid varible
               entryid.add("null");                                   //saves the null varible in the jason file to the entryid varible
               
               INpath.add((String) empIN.get("path"));               //saves the path varible in the jason file to the INpath varible
           }else{
               INpath.add("null");                                    //saves the null varible in the jason file to the INpath varible
               repositoryid.add((String) empIN.get("repositoryId")); //saves the repositoryid varible in the jason file to the repositoryid varible
                                                               //^Javier fixed minor case error
               entryid.add((String) empIN.get("entryId"));           //saves the entryid varible in the jason file to the entryid varible
                                                     //^Javier fixed minor case error
           }
       loopIndex++;
    }   
     
     
}