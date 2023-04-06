/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.aquaticlazer;
import Entry.Entries;
import Entry.LocalEntry;
import Entry.RemoteEntry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;
/**
 *
 * @author macdm
 */
public class AquaticLazer {

    public static void main(String[] args) {
        try {
            GUI gui = new GUI();	//runs get file path class
            
            while(gui.IfFileGot() == 1) {	//stops the program till a file is selected
                System.out.print("");
            }
            
            
            
            JsonFileReader JFR = new JsonFileReader(gui.GetFile()); //creates a JsonFileReader class
            
            //GUI.GUIPrintln("Scenario name: " + JFR.GetName());      //start of an example on how to find the length and varibles of the json file arrays
            //GUI.GUIPrintln("Processing elements are: ");
            ArrayList<Entries> entHolder = new ArrayList<Entries>();
            
            for(int i = 0; i < JFR.GetPEType().size(); i++){
                
                
                //GUI.GUIPrintln("\nProcessing element type: " + JFR.GetPEType().get(i));
                //GUI.GUIPrintln("Imput entries:");
                
                int localCount = 0, repoCount = 0;
                String[] type = new String[JFR.GetINType().get(i).size()];
                String[] path = new String[JFR.GetINType().get(i).size()];
                String[] EntryId = new String[JFR.GetINType().get(i).size()];
                String[] RepositoryId = new String[JFR.GetINType().get(i).size()];
                String[] name = new String[JFR.GetParName().get(i).size()];
                
                String[] value = new String[JFR.GetParName().get(i).size()];
                String holder = "";
                
                
                for(int j = 0; j < JFR.GetINType().get(i).size(); j++){     //use .size() to get the length of the array. .get(i).size() gives the length of row and use .size() to get the length of the column
                    
                    //GUI.GUIPrintln("file type: " + JFR.GetINType().get(i).get(j));  //use two .get methouds to select varibles in 2d arraylists
                    
                    type[j] = JFR.GetINType().get(i).get(j);
                    
                    if(JFR.GetINType().get(i).get(j).equals("local") == true){
                        
                        localCount++;
                        
                        // GUI.GUIPrintln("file path: " + JFR.GetPath().get(i).get(j));
                        
                        
                        
                        path[j] = JFR.GetPath().get(i).get(j);
                    }else{
                        
                        repoCount++;
                        
                        // GUI.GUIPrintln("file EntryId: " + JFR.GetEntryId().get(i).get(j));
                        // GUI.GUIPrintln("file RepositoryId: " + JFR.GetRepositoryId().get(i).get(j));
                        RepositoryId[j] = JFR.GetRepositoryId().get(i).get(j);
                            //Javier flipped the the two lines here to work with repository calls
                        EntryId[j] = JFR.GetEntryId().get(i).get(j);
                        
                    }
                    
                    
                }
                //GUI.GUIPrintln("parameters:");
                for(int j = 0; j < JFR.GetParName().get(i).size(); j++){
                    
                    //GUI.GUIPrintln("parameters name: " + JFR.GetParName().get(i).get(j));
                    // GUI.GUIPrintln("parameters value: " + JFR.GetParValue().get(i).get(j));
                    
                    name[j] = JFR.GetParName().get(i).get(j);
                    value[j] = JFR.GetParValue().get(i).get(j);
                }
                
                Entries[] entry = new Entries[JFR.GetINType().get(i).size()];
                
                for(int j = 0; j < JFR.GetINType().get(i).size(); j++){     //use .size() to get the length of the array. .get(i).size() gives the length of row and use .size() to get the length of the column
                    
                    
                    
                    if(JFR.GetINType().get(i).get(j).equals("local") == true){      //fill out all the entrys
                        
                        entry[j] = new LocalEntry( path[j]);                            
                        
                    }else{
                        
                        
                        System.out.println(EntryId[j]);
                        entry[j] = new RemoteEntry(RepositoryId[j], EntryId[j]);
                        
                    }
                }
                
                
                Filter filter = new Filter();           //makes a instance of the filter class
                Processing processing = new Processing();//makes a instance of the processing class
                
                
                
                switch(JFR.GetPEType().get(i)){     //if the type of processing element is 
                    
                    case "LengthFilter":
                        
                        if(i == 0){     //if it is the first processing element
                            
                            if(name[0].equalsIgnoreCase("Length")){       //if the name 0 is equal to length then put value 0 in the length peramater
                                Entries[] temp = filter.LengthFilter(entry, Long.parseLong(value[0]), value[1]);    //save all the files that pass into temp
                                entHolder.clear();      //clear everything out of entholder
                                for (Entries temp2: temp) {     //add temp to entholder so it does not get erased for the next loop
                                    entHolder.add(temp2);
                                }
                                
                            }else{          //if the name 0 is not equal to length then put value 1 in the length peramater
                                Entries[] temp = filter.LengthFilter(entry, Long.parseLong(value[1]), value[0]);
                                entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                                
                            }
                            
                        }else{      //if this is not the first loop then put entholder into the processing element 
                            
                            if(name[0].equalsIgnoreCase("Length")){
                                
                                Entries[] temp = filter.LengthFilter(entHolder.toArray(new Entries[0]), Long.parseLong(value[0]), value[1]);
                                entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                            }else{
                                Entries[] temp = filter.LengthFilter(entHolder.toArray(new Entries[0]), Long.parseLong(value[1]), value[0]);
                                entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                            }
                            
                        }
                        
                        break;
                        
                    case "Print":
                        //sf
                        
                        for(int o = 0; o < entHolder.size(); o++){  //loop for eatch file
                            
                            if(i == 0){                 //if it is the first processing element
                                entry[o].print(gui);    //print to the gui
                            }else{
                                
                                entHolder.toArray(new Entries[0])[o].print(gui);
                                
                            }
                            
                        }
                        
                        break;
                        
                    case "NameFilter":
                        
                        if(i == 0){         //if it is the first processing element
                            
                            Entries[] temp = filter.NameFilter(entry, value[0]);
                            entHolder.clear();
                            for (Entries temp2: temp) {
                                entHolder.add(temp2);
                            }
                        }else{
                            
                            Entries[] temp = filter.NameFilter(entHolder.toArray(new Entries[0]), value[0]);
                            entHolder.clear();
                            for (Entries temp2: temp) {
                                entHolder.add(temp2);
                            }
                        }
                        break;
                        
                    case "ContentFilter":
                        if(i == 0){         //if it is the first processing element
                            Entries[] temp = filter.ContentFilter(entry, value[0]);
                            entHolder.clear();
                            for (Entries temp2: temp) {
                                entHolder.add(temp2);
                            }
                        }else{
                            Entries[] temp = filter.ContentFilter(entHolder.toArray(new Entries[0]), value[0]);
                            entHolder.clear();
                            for (Entries temp2: temp) {
                                entHolder.add(temp2);
                            }
                        }
                        break;
                        
                    case "CountFilter":
                        if(i == 0){             //if it is the first processing element
                            if(name[0].equalsIgnoreCase("Key")){
                                Entries[] temp = filter.CountFilter(entry, value[0],Integer.parseInt(value[1]));
                                entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                            }else{
                                Entries[] temp = filter.CountFilter(entry, value[1],Integer.parseInt(value[0]));
                                entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                            }
                        }else{
                            if(name[0].equalsIgnoreCase("Key")){
                                Entries[] temp = filter.CountFilter(entHolder.toArray(new Entries[0]), value[0],Integer.parseInt(value[1]));
                                entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                            }else{
                                Entries[] temp = filter.CountFilter(entHolder.toArray(new Entries[0]), value[1],Integer.parseInt(value[0]));
                                entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                            }
                        }
                        break;
                        
                    case "List":
                        if(i == 0){                         //if it is the first processing element
                          Entries[] temp = processing.List(entry,Long.parseLong(value[0]));
                          entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                        }else{
                            Entries[] temp = processing.List(entHolder.toArray(new Entries[0]),Long.parseLong(value[0]));
                            entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                        }
                        break;
                        
                    case "Rename":
                        
                        if(i == 0){                 //if it is the first processing element
                          Entries[] temp = processing.Rename(entry,value[0]);
                          entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                        }else{
                            Entries[] temp = processing.Rename(entHolder.toArray(new Entries[0]),value[0]);
                            entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                        }
                        break;
                        
                    case "Split":
                        
                        if(i == 0){                 //if it is the first processing element
                          Entries[] temp = processing.Split(entry,Integer.parseInt(value[0]));
                          entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                        }else{
                            Entries[] temp = processing.Split(entry,Integer.parseInt(value[0]));
                            entHolder.clear();
                                for (Entries temp2: temp) {
                                    entHolder.add(temp2);
                                }
                        }
                        break;
                        
                }
                
            }                
        } catch (ParseException ex) {
            Logger.getLogger(AquaticLazer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AquaticLazer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}   