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
        //sfs
        int newLineLocation;
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
            newLineLocation = sb.indexOf("\n", 1);
            
            if (newLineLocation == -1) {
                tempAL.add(sb.delete(0, sb.length()).toString());
            } else {
                tempAL.add(sb.delete(1, sb.indexOf("\n", 1)).toString()); // slowly empties the string builder and adds it to the array list one element at a time
                // deletes the elements from the second character to the element before the \n character and then converts it to a string
                sb.deleteCharAt(0); // needs to delete the first element
            }
        }
        contents = new String[tempAL.size()];
        for (int i = 0; i < tempAL.size(); i++) {
            contents[i] = tempAL.get(i);
        }
        
        reader.close();
        } catch (IOException e) {
            
        }
        
    }
    
    public void print(GUI gui){};



    
    
}
