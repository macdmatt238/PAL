/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entry;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author pickn
 */
public class Entries {
    public File file;
    public File[] directoryContents;
    public String name;
    public Long length;
    public String path;
    public boolean isDirectory;
    public String[] contents;
    public int repcount = 0;
    
    Entries(String name) {
        this.name = name;
    }

    void readFileContents() {
        
        
        ArrayList<String> tempAL = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        sb.append('\n'); // this is done because then everytime each line starts with a \n character which matters when i begin emptying the string builder
        String temp;
        try {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        while ((temp = reader.readLine()) != null) {
            sb.append(temp);

        }
        
        while (sb.length() != 0) {
            tempAL.add(sb.delete(1, sb.indexOf("\n")).toString()); // slowly empties the string builder and adds it to the array list one element at a time
            // deletes the elements from the second character to the element before the \n character and then converts it to a string
            sb.deleteCharAt(0); // needs to delete the first element
        }
        contents = (String[]) tempAL.toArray();
        reader.close();
        } catch (IOException e) {
            
        }
        
    }



    
    
}
