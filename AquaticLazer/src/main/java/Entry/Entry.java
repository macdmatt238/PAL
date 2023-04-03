/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entry;

/**
 *
 * @author macdm
 */
public class Entry {
    
        File file;
    File[] directoryContents;
    String name;
    Long length;
    String path;
    boolean isDirectory;
    ArrayList<String> contents = new ArrayList<String>();
    int repcount = 0;
    int type;
    
    Entry(String name, String path, boolean isDirectory, int type) {
        this.name = name;
        this.path = path;
        this.isDirectory = isDirectory;
        this.type = type;
    }

    public Object[] getContents() { // thing to have it passed as an array
        return contents.toArray();
    }

    public File getFile() {
        return file;
    }

    public File[] getDirectoryContents() {
        return directoryContents;
    }

    public String getName() {
        return name;
    }

    public Long getLength() {
        return length;
    }

    public String getPath() {
        return path;
    }

    public boolean isIsDirectory() {
        return isDirectory;
    }

    public int getRepcount() {
        return repcount;
    }

    public int getType() {
        return type;
    }
    
}
