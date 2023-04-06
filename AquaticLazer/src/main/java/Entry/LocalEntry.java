/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entry;


import java.io.File;
import com.mycompany.aquaticlazer.GUI;


/**
 *
 * @author pickn
 */
public class LocalEntry extends Entries {
    
    public LocalEntry(String path){
        //sfe
        this.path = path;
        file = new File(path);
        System.out.println(file.exists());  
        name = file.getName();
        length = file.length();
        System.out.println(length);
        System.out.println(path);
        isDirectory = file.isDirectory();
        
        if (isDirectory) {
            contents = file.list();
            directoryContents = new Entries[contents.length];
            for (int i = 0; i < contents.length; i++) {
                directoryContents[i] = new LocalEntry(path + "/" + contents[i]); // this should go throw every item inside of the directory and open the contents as an LocalEntry
            }
        } else {
            super.readFileContents();
        }
    }


    public void print(GUI gui) {
        
        
        gui.GUIPrintln("Name: " + name);
        gui.GUIPrintln("Path: " + path); 
        gui.GUIPrintln("Size: " + length + "bytes");
        gui.GUIPrintln("");
        
    }
    
}
