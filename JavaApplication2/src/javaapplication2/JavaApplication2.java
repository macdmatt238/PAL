/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;

import java.util.Arrays;

/**
 *
 * @author macdm
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        	FilePicker getpath = new FilePicker();	//runs get file path class
		
		while(getpath.IfFileGot() == 1) {	//stops the program till a file is selected
			System.out.print("");
		}
		
                
                
		JsonFileReader JFR = new JsonFileReader(getpath.GetFile()); //creates a JsonFileReader class
                
                System.out.println("Scenario name: " + JFR.GetName());      //start of an example on how to find the length and varibles of the json file arrays
                System.out.println("Prcessing elements are: ");
                
                for(int i = 0; i < JFR.GetPEType().size(); i++){
                    
                    
                    System.out.println("\nPrcessing element type: " + JFR.GetPEType().get(i));
                    System.out.println("Imput entries:");
                    
                    for(int j = 0; j < JFR.GetINType().get(i).size(); j++){     //use .size() to get the length of the array. .get(i).size() gives the length of row and use .size() to get the length of the column
                         
                        System.out.println("file type: " + JFR.GetINType().get(i).get(j));  //use two .get methouds to select varibles in 2d arraylists
                        
                        
                        if(JFR.GetINType().get(i).get(j).equals("local") == true){
                            
                            System.out.println("file path: " + JFR.GetPath().get(i));
                        }else{
                            
                            System.out.println("file EntryId: " + JFR.GetEntryId().get(i));
                            System.out.println("file RepositoryId: " + JFR.GetRepositoryId().get(i));
                        }
                        
                        
                    }
                    System.out.println("parameters:");
                    for(int j = 0; j < JFR.GetParName().get(i).size(); j++){
                            
                            System.out.println("parameters name: " + JFR.GetParName().get(i).get(j));
                            System.out.println("parameters value: " + JFR.GetParValue().get(i).get(j));
                        }
                }
              
	}
    }
    

