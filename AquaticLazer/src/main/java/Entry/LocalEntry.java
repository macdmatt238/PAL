/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entry;


import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author pickn
 */
public class LocalEntry extends Entries {
    
    public LocalEntry(String name, String path){
        super(name);
        this.path = path;
        file = new File(path);
        System.out.println(file.exists());  
        length = file.length();
        isDirectory = file.isDirectory();
        
        if (isDirectory) {
            contents = file.list();
            directoryContents = new File[contents.length];
            for (int i = 0; i < contents.length; i++) {
                directoryContents[i] = new File(path + "/" + contents[i]); // this should go throw every item inside of the directory and open the contents as a file
            }
        } else {
            super.readFileContents();
        }
    }

    @Override
    public void print() {
        System.out.println("Name: " + name);
        System.out.println("Path: " + path); 
        System.out.println("Size: " + length + "bytes");
        
    }
    
}
