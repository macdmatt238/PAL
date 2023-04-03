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
import java.util.Arrays;


/**
 *
 * @author pickn
 */
public class LocalEntry extends Entry {
    
    public LocalEntry(String name, String path, boolean isDirectory, int type) throws FileNotFoundException {
        super(name, path, isDirectory, type);
        file = new File(path);
        length = file.length();
       
        if (isDirectory) {
            contents.addAll(Arrays.asList(file.list()));
        } else {
            readFileContents();
        }
    }

    private void readFileContents() {
        
        
        
        StringBuilder sb = new StringBuilder();
        sb.append('\n'); // this is done because then everytime each line starts with a \n character which matters when i begin emptying the string builder
        String temp;
        try {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        while ((temp = reader.readLine()) != null) {
            sb.append(temp);

        }
        
        while (!sb.isEmpty()) {
            contents.add(sb.delete(1, sb.indexOf("\n")).toString()); // slowly empties the string builder and adds it to the array list one element at a time
            // deletes the elements from the second character to the element before the \n character and then converts it to a string
        }
        
        reader.close();
        } catch (IOException e) {
            
        }
        
    }
}
