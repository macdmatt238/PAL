/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entry;

import java.io.File;

/**
 *
 * @author pickn
 */
public class RemoteEntry extends Entry{
    String repositoryID;
    public RemoteEntry(String name, String repositoryID, boolean isDirectory) {
        super(name);
        this.repositoryID = repositoryID;
        
        this.path = JavierFunction(repositoryID); // this needs to be updated with javier's function
        
        
        file = new File(path);
        length = file.length();
        isDirectory = file.isDirectory();
        
        if (isDirectory) {
            contents = file.list();
            directoryContents = new File[contents.length];
            for (int i = 0; i < contents.length; i++) {
                directoryContents[i] = new File(path + "/" + contents[i]); // this should go through every item inside of the directory and open the contents as a file
            }
        } else {
            super.readFileContents();
        }
        
    }
    
    
    private String JavierFunction(String repositoryID) {
        return "1";
    }
    
}
