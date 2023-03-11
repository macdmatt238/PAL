/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;

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
		
		JsonFileReader JFR = new JsonFileReader(getpath.GetFile());
		
	}
    }
    

