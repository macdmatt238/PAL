/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.aquaticlazer;
import Entry.LocalEntry;
import Entry.RemoteEntry;
import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
import com.laserfiche.repository.api.clients.EntriesClient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import java.io.InputStream;
import java.util.Scanner;
import java.io.File;
import java.util.function.Consumer;
/**
 *
 * @author macdm
 */
public class AquaticLazer {

    public static void main(String[] args) {
        GUI GUI = new GUI();	//runs get file path class
		
		while(GUI.IfFileGot() == 1) {	//stops the program till a file is selected
			System.out.print("");
		}
		
                
                
		JsonFileReader JFR = new JsonFileReader(GUI.GetFile()); //creates a JsonFileReader class
                
                //GUI.GUIPrintln("Scenario name: " + JFR.GetName());      //start of an example on how to find the length and varibles of the json file arrays
                //GUI.GUIPrintln("Processing elements are: ");
                
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
                    String holder = "", holder2 = "";
                    for(int j = 0; j < JFR.GetINType().get(i).size(); j++){     //use .size() to get the length of the array. .get(i).size() gives the length of row and use .size() to get the length of the column
                         
                        //GUI.GUIPrintln("file type: " + JFR.GetINType().get(i).get(j));  //use two .get methouds to select varibles in 2d arraylists
                        
                        type[j] = JFR.GetINType().get(i).get(j).replace(holder, JFR.GetINType().get(i).get(j));
                        
                        if(JFR.GetINType().get(i).get(j).equals("local") == true){
                            
                          localCount++;
                            
                           // GUI.GUIPrintln("file path: " + JFR.GetPath().get(i).get(j));
                            
                            path[j] = JFR.GetPath().get(i).get(j).replace(holder, JFR.GetPath().get(i).get(j));
                        }else{
                            
                            repoCount++;
                            
                           // GUI.GUIPrintln("file EntryId: " + JFR.GetEntryId().get(i).get(j));
                           // GUI.GUIPrintln("file RepositoryId: " + JFR.GetRepositoryId().get(i).get(j));
                            
                            EntryId[j] = JFR.GetEntryId().get(i).get(j).replace(holder, JFR.GetEntryId().get(i).get(j));
                            RepositoryId[j] = JFR.GetRepositoryId().get(i).get(j).replace(holder, JFR.GetRepositoryId().get(i).get(j));
                        }
                        
                        
                    }
                    GUI.GUIPrintln("parameters:");
                    for(int j = 0; j < JFR.GetParName().get(i).size(); j++){
                            
                            //GUI.GUIPrintln("parameters name: " + JFR.GetParName().get(i).get(j));
                           // GUI.GUIPrintln("parameters value: " + JFR.GetParValue().get(i).get(j));
                            
                            name[j] = JFR.GetINType().get(i).get(j).replace(holder, JFR.GetINType().get(i).get(j));
                            value[j] = JFR.GetINType().get(i).get(j).replace(holder, JFR.GetINType().get(i).get(j));
                        }
                    
                    Entry[] entry = new Entry[JFR.GetINType().get(i).size()];
                    RemoteEntry[] repoEntry = new RemoteEntry[repoCount];
                    LocalEntry[] localEntry = new LocalEntry[localCount];
                    
                    for(int j = 0; j < JFR.GetINType().get(i).size(); j++){     //use .size() to get the length of the array. .get(i).size() gives the length of row and use .size() to get the length of the column
                        
                    
                        
                        if(JFR.GetINType().get(i).get(j).equals("local") == true){      //fill out all the entrys
                            
                            holder = path[j].replace(holder, path[j]);
                            
                            while(holder.equals("")){
                                
                            holder = path[j-1].replace(holder, path[j-1]);
                                 
                            }
                        
                          localEntry[j] = new LocalEntry(JFR.GetName(), holder);
                        
                        }else{
                            
                            holder = RepositoryId[j].replace(holder, RepositoryId[j]);
                            
                            while(holder.equals("")){
                                
                            holder = RepositoryId[j-1].replace(holder, RepositoryId[j-1]);
                                 
                            }
                            
                            holder2 = EntryId[j].replace(holder2, EntryId[j]);
                            
                            while(holder2.equals("")){
                                
                            holder2 = EntryId[j-1].replace(holder2, EntryId[j-1]);
                                 
                            }
                            
                            repoEntry[j] = new RemoteEntry(JFR.GetName(), holder, holder2);
                            
                        }
                    }
                    
                    System.arraycopy(repoEntry, 0, entry, 0, repoEntry.length);
                    System.arraycopy(localEntry, 0, entry, repoEntry.length, entry.length);
                    
                    //TO DO call filters with a switch
                    
                    Filter filter = new Filter();
                    
                    switch(JFR.GetPEType().get(i)){
                        case "List":
                            
                            break;
                        
                        case "LengthFilter":
                            
                            if(name[0].equalsIgnoreCase("Length")){
                            filter.LengthFilter(entry, Long.parseLong(value[0]), value[1]);
                            }else{
                                filter.LengthFilter(entry, Long.parseLong(value[1]), value[0]);
                            }
                            break;
                        
                            case "Print":
                            
                            break;
                            
                            case "NameFilter":
                            
                                filter.NameFilter(entry, value[0]);
                                
                            break;
                            
                            case "ContentFilter":
                            
                                filter.ContentFilter(entry, value[0]);
                                
                            break;
                            
                            case "CountFilter":
                            if(name[0].equalsIgnoreCase("Key")){
                            filter.CountFilter(entry, value[0],Integer.parseInt(value[1]));
                            }else{
                                filter.CountFilter(entry, value[1],Integer.parseInt(value[0]));
                            }
                                
                                
                            break;
                    }
                    
                
          
                
                
        }                
    }
}
