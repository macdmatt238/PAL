/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entry;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
import com.mycompany.aquaticlazer.GUI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author pickn
 */
public class RemoteEntry extends Entries{
    String repositoryID;
    String entryID;
    String path;
    public RemoteEntry(String repositoryID, String entryID) {
        
        this.repositoryID = repositoryID;
        this.entryID = entryID;
        this.path = JavierFunction(entryID); // this needs to be updated with javier's function
        
        
        file = new File(path);
        length = file.length();
        isDirectory = file.isDirectory();
        name = file.getName();
        
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
    //sf

    @Override
    public void print(GUI gui) {
        gui.GUIPrintln("Name: " + name);
        gui.GUIPrintln("Path: " + path); 
        gui.GUIPrintln("Size: " + length + "bytes");
        gui.GUIPrintln("EntryID: " + entryID);
        gui.GUIPrintln("RepoID: " + repositoryID);
    }
    
    
    private String JavierFunction(String entryID) {    //Javier Lazo-Arevalo Student ID: 1217200 made the following function

        String path = null;     //initalizes variable to store path
        int entryid = 0;        //initalizes variable to store entry id
        int idlength = entryID.length();   //gets the length of the repository id
        for (int i = 0; i < idlength; i++) {    //for loop to get the numberical value of repositoryID
            char character = entryID.charAt(i);
            entryid = (entryid * 10) - 48 + (int) character;
        }
        if (entryid == 4 || entryid == 15 || entryid == 18 || entryid == 23) {  //if strucure to run for id's of 4, 15, 48, and 23
            String servicePrincipalKey = "9udNhuNq85BwiQW7R_va";
            String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOWQwYWMwYTAtZGI4NC00N2RlLWIxZWYtMjI0ZDRiYzZkY2NhIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImFBU3NaSGFMX1M4bW1qbmVCTkktd3o2c0JBWHFTQmtPWE96V2ZtSjZQbGciLAoJCSJ4IjogImFfTHNINzBoNDVOM0dTUnV4d2RTMmhWSHFyMGcxdGdMVk9wRHV6MjFIRk0iLAoJCSJ5IjogIkpNT1AzQzV1UFNMSFBEUld2TTNTVlVrbjZfNXZkUTJWZTNaaUdTc3I4LTAiLAoJCSJkIjogIlJTS21FeGQxUFBSczBNbnBIUm5QaTNFY2x5VHpCUnJJUnhvVHlGQmhVVWMiLAoJCSJpYXQiOiAxNjc3Mjk3ODY3Cgl9Cn0";
            String repositoryId = "r-0001d410ba56";
            AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);

            RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey( //creates client to hadle repositiry fetching
                    servicePrincipalKey, accessKey);

            com.laserfiche.repository.api.clients.impl.model.Entry entry = client.getEntriesClient().getEntry(repositoryId, entryid, null).join();

            path = "src\\";   //sets path
            path = path + entry.getName();  //adds folder name to path
            File f1 = new File(path);   //creates new folder
            boolean bool = f1.mkdir();      //checks if folder was created
            if (bool) {
                System.out.println("Folder is created successfully");
            } else {
                System.out.println("Error Found!");
            }
            ODataValueContextOfIListOfEntry result = client //sets up to get data from repository
                    .getEntriesClient()
                    .getEntryListing(repositoryId, entryid, true, null, null, null, null, null, "name", null, null, null).join();

            List<com.laserfiche.repository.api.clients.impl.model.Entry> entries = result.getValue();   //creates a list of entries
            for (com.laserfiche.repository.api.clients.impl.model.Entry childEntry : entries) {     //for loop to down;oad entries
                int entryIdToDownload = childEntry.getId();     //gets entry to download
                com.laserfiche.repository.api.clients.impl.model.Entry entry1 = client.getEntriesClient().getEntry(repositoryId, entryIdToDownload, null).join();   //gets entry data

                final String FILE_NAME = entry1.getName() + ".txt";     //sets file name
                Consumer<InputStream> consumer = inputStream -> {       //gets file from repo
                    File exportedFile = new File(f1, FILE_NAME);    //creates file at location
                    try (FileOutputStream outputStream = new FileOutputStream(exportedFile)) {  //try catch to see if a file was created to write to
                        byte[] buffer = new byte[1024];
                        while (true) {
                            int length = inputStream.read(buffer);
                            if (length == -1) {
                                break;
                            }
                            outputStream.write(buffer, 0, length);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

                client.getEntriesClient() //gets data from repository and writes it
                        .exportDocument(repositoryId, entryIdToDownload, null, consumer)
                        .join();
            }
            client.close();     //closes API
            System.out.println("done");
        } else if (entryid == 3 || entryid == 16 || entryid == 17 || entryid == 27 || entryid == 28 || entryid == 30 || entryid == 31 || entryid == 7 || entryid == 8 || entryid == 9 || entryid == 6 || entryid == 5 || entryid == 13 || entryid == 10 || entryid == 11 || entryid == 12 || entryid == 14 || entryid == 24 || entryid == 26 || entryid == 53 || entryid == 54 || entryid == 25 || entryid == 51 || entryid == 52) {   //else if to handle all files in repository
            String servicePrincipalKey = "9udNhuNq85BwiQW7R_va";
            String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOWQwYWMwYTAtZGI4NC00N2RlLWIxZWYtMjI0ZDRiYzZkY2NhIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImFBU3NaSGFMX1M4bW1qbmVCTkktd3o2c0JBWHFTQmtPWE96V2ZtSjZQbGciLAoJCSJ4IjogImFfTHNINzBoNDVOM0dTUnV4d2RTMmhWSHFyMGcxdGdMVk9wRHV6MjFIRk0iLAoJCSJ5IjogIkpNT1AzQzV1UFNMSFBEUld2TTNTVlVrbjZfNXZkUTJWZTNaaUdTc3I4LTAiLAoJCSJkIjogIlJTS21FeGQxUFBSczBNbnBIUm5QaTNFY2x5VHpCUnJJUnhvVHlGQmhVVWMiLAoJCSJpYXQiOiAxNjc3Mjk3ODY3Cgl9Cn0";
            String repositoryId = "r-0001d410ba56";
            path = "src\\";   //sets path
            AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);

            RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey( //creates client to hadle repositiry fetching
                    servicePrincipalKey, accessKey);

            int entryIdToDownload = entryid;    //gets entry to download

            com.laserfiche.repository.api.clients.impl.model.Entry entry1 = client.getEntriesClient().getEntry(repositoryId, entryIdToDownload, null).join();   //gets entry data

            final String FILE_NAME = entry1.getName() + ".txt";     //sets file name
            Consumer<InputStream> consumer = inputStream -> {       //gets file from repo
                File exportedFile = new File(FILE_NAME);     //creates file
                try (FileOutputStream outputStream = new FileOutputStream(exportedFile)) {  //try catch to see if a file was created to write to
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int length = inputStream.read(buffer);
                        if (length == -1) {
                            break;
                        }
                        outputStream.write(buffer, 0, length);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            client.getEntriesClient() //gets data from repository and writes it
                    .exportDocument(repositoryId, entryIdToDownload, null, consumer)
                    .join();

            client.close();     //closes API
            System.out.println("done");
        }
        return path;    //returns path
    }
    
}