/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entry;

import com.mycompany.aquaticlazer.GUI;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author pickn
 */
public class Entries {
    public File file;
    public Entries[] directoryContents;
    public String name;
    public Long length;
    public String path;
    public boolean isDirectory;
    public String[] contents;
    public int repcount = 0;
    
    Entries() {
        
    }

    void readFileContents() {
       ArrayList<String> tempAL = new ArrayList<>();
        String temp;
        try {
        BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));

        while ((temp = reader.readLine()) != null) { // reads everything into the arraylist one line at a time
            tempAL.add(temp);
        }

        String[] contents = new String[tempAL.size()];
        for (int i = 0; i < tempAL.size(); i++) {
            contents[i] = tempAL.get(i); // adds everything in the array list to the contents array
        }
        
        reader.close();

        this.contents = contents;
    } catch (IOException e) {
        System.out.println("BROKEN");
    } 
        
    }
    
    public void print(GUI gui){};



    
    
}
