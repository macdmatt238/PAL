/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.aquaticlazer;
import Entry.LocalEntry;
import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
import com.laserfiche.repository.api.clients.EntriesClient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import java.io.InputStream;
import java.util.Scanner;
import java.io.File;
import java.util.function.Consumer;
/**
 *
 * @author macdm
 */
public class AquaticLazer {

    public static void main(String[] args) {
        GUI GUI = new GUI();	//runs get file path class
		
		while(GUI.IfFileGot() == 1) {	//stops the program till a file is selected
			System.out.print("");
		}
		
                
                
		JsonFileReader JFR = new JsonFileReader(GUI.GetFile()); //creates a JsonFileReader class
                
                GUI.GUIPrintln("Scenario name: " + JFR.GetName());      //start of an example on how to find the length and varibles of the json file arrays
                GUI.GUIPrintln("Processing elements are: ");
                
                for(int i = 0; i < JFR.GetPEType().size(); i++){
                    
                    
                    GUI.GUIPrintln("\nProcessing element type: " + JFR.GetPEType().get(i));
                    GUI.GUIPrintln("Imput entries:");
                    
                    
                    
                    for(int j = 0; j < JFR.GetINType().get(i).size(); j++){     //use .size() to get the length of the array. .get(i).size() gives the length of row and use .size() to get the length of the column
                         
                        GUI.GUIPrintln("file type: " + JFR.GetINType().get(i).get(j));  //use two .get methouds to select varibles in 2d arraylists
                        
                        
                        
                        if(JFR.GetINType().get(i).get(j).equals("local") == true){
                            
                            LocalEntry entry = new LocalEntry(JFR.GetName(), JFR.GetPath().get(i).get(j), false, 0);
                            
                            GUI.GUIPrintln("file path: " + JFR.GetPath().get(i).get(j));
                        }else{
                            
                            GUI.GUIPrintln("file EntryId: " + JFR.GetEntryId().get(i).get(j));
                            GUI.GUIPrintln("file RepositoryId: " + JFR.GetRepositoryId().get(i).get(j));
                        }
                        
                        
                    }
                    GUI.GUIPrintln("parameters:");
                    for(int j = 0; j < JFR.GetParName().get(i).size(); j++){
                            
                            GUI.GUIPrintln("parameters name: " + JFR.GetParName().get(i).get(j));
                            GUI.GUIPrintln("parameters value: " + JFR.GetParValue().get(i).get(j));
                        }
                }
          
                
                
                
                
                    
                    
                    
int entryid;
        Scanner input = new Scanner(System.in); //scanner to input number
        System.out.println("start");
        System.out.println("please enter the id you would like to download as its entry id");
        entryid = input.nextInt();
        download(entryid);
    }
    
     public static void download(int entryid) {
        
         if (entryid == 4 || entryid == 15 || entryid == 18 || entryid == 23) {
            String servicePrincipalKey = "9udNhuNq85BwiQW7R_va";
            String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOWQwYWMwYTAtZGI4NC00N2RlLWIxZWYtMjI0ZDRiYzZkY2NhIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImFBU3NaSGFMX1M4bW1qbmVCTkktd3o2c0JBWHFTQmtPWE96V2ZtSjZQbGciLAoJCSJ4IjogImFfTHNINzBoNDVOM0dTUnV4d2RTMmhWSHFyMGcxdGdMVk9wRHV6MjFIRk0iLAoJCSJ5IjogIkpNT1AzQzV1UFNMSFBEUld2TTNTVlVrbjZfNXZkUTJWZTNaaUdTc3I4LTAiLAoJCSJkIjogIlJTS21FeGQxUFBSczBNbnBIUm5QaTNFY2x5VHpCUnJJUnhvVHlGQmhVVWMiLAoJCSJpYXQiOiAxNjc3Mjk3ODY3Cgl9Cn0";
            String repositoryId = "r-0001d410ba56";
            AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);

            RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                    servicePrincipalKey, accessKey);

            // Get information about the ROOT entry, i.e. entry with ID=1
            Entry entry = client.getEntriesClient()
                    .getEntry(repositoryId, entryid, null).join();
            String path = "src\\";
            path = path + entry.getName();
            File f1 = new File(path);
            boolean bool = f1.mkdir();
            if (bool) {
                System.out.println("Folder is created successfully");       //you can choose to remove the test but not the if structure
            } else {
                System.out.println("Error Found!");
            }
            ODataValueContextOfIListOfEntry result = client
                    .getEntriesClient()
                    .getEntryListing(repositoryId, entryid, true, null, null, null, null, null, "name", null, null, null).join();
            List<Entry> entries = result.getValue();
            for (Entry childEntry : entries) {
                int entryIdToDownload = childEntry.getId();
                Entry entry1 = client.getEntriesClient()
                        .getEntry(repositoryId, entryIdToDownload, null).join();
                final String FILE_NAME = entry1.getName() + ".txt";
                Consumer<InputStream> consumer = inputStream -> {
                    File exportedFile = new File(f1, FILE_NAME);
                    try (FileOutputStream outputStream = new FileOutputStream(exportedFile)) {
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

                client.getEntriesClient()
                        .exportDocument(repositoryId, entryIdToDownload, null, consumer)
                        .join();
            }
            client.close();
            System.out.println("done");
        } else {
            String servicePrincipalKey = "9udNhuNq85BwiQW7R_va";
            String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOWQwYWMwYTAtZGI4NC00N2RlLWIxZWYtMjI0ZDRiYzZkY2NhIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImFBU3NaSGFMX1M4bW1qbmVCTkktd3o2c0JBWHFTQmtPWE96V2ZtSjZQbGciLAoJCSJ4IjogImFfTHNINzBoNDVOM0dTUnV4d2RTMmhWSHFyMGcxdGdMVk9wRHV6MjFIRk0iLAoJCSJ5IjogIkpNT1AzQzV1UFNMSFBEUld2TTNTVlVrbjZfNXZkUTJWZTNaaUdTc3I4LTAiLAoJCSJkIjogIlJTS21FeGQxUFBSczBNbnBIUm5QaTNFY2x5VHpCUnJJUnhvVHlGQmhVVWMiLAoJCSJpYXQiOiAxNjc3Mjk3ODY3Cgl9Cn0";
            String repositoryId = "r-0001d410ba56";
            AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);

            RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                    servicePrincipalKey, accessKey);

            int entryIdToDownload = entryid;
            Entry entry1 = client.getEntriesClient()
                    .getEntry(repositoryId, entryIdToDownload, null).join();
            final String FILE_NAME = entry1.getName() + ".txt";
            Consumer<InputStream> consumer = inputStream -> {
                File exportedFile = new File(FILE_NAME);
                try (FileOutputStream outputStream = new FileOutputStream(exportedFile)) {
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

            client.getEntriesClient()
                    .exportDocument(repositoryId, entryIdToDownload, null, consumer)
                    .join();

            client.close();
            System.out.println("done");
        }
    }
}
