/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entry;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
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
    public RemoteEntry(String name, String repositoryID, String entryID) {
        super(name);
        this.repositoryID = repositoryID;
        this.entryID = entryID;
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

        String path=null;

        int entryid=-48;
        int idlength=repositoryID.length();
        for (int i = 0; i < idlength; i++) {
            char character=repositoryID.charAt(i);
            entryid+=(int)character;
        }
        if (entryid == 1) {
        } else if (entryid == 4 || entryid == 15 || entryid == 18 || entryid == 23) {
            String servicePrincipalKey = "9udNhuNq85BwiQW7R_va";
            String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOWQwYWMwYTAtZGI4NC00N2RlLWIxZWYtMjI0ZDRiYzZkY2NhIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImFBU3NaSGFMX1M4bW1qbmVCTkktd3o2c0JBWHFTQmtPWE96V2ZtSjZQbGciLAoJCSJ4IjogImFfTHNINzBoNDVOM0dTUnV4d2RTMmhWSHFyMGcxdGdMVk9wRHV6MjFIRk0iLAoJCSJ5IjogIkpNT1AzQzV1UFNMSFBEUld2TTNTVlVrbjZfNXZkUTJWZTNaaUdTc3I4LTAiLAoJCSJkIjogIlJTS21FeGQxUFBSczBNbnBIUm5QaTNFY2x5VHpCUnJJUnhvVHlGQmhVVWMiLAoJCSJpYXQiOiAxNjc3Mjk3ODY3Cgl9Cn0";
            String repositoryId = "r-0001d410ba56";
            AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);

            RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                    servicePrincipalKey, accessKey);


             com.laserfiche.repository.api.clients.impl.model.Entry entry = client.getEntriesClient().getEntry(repositoryId, entryid, null).join();

            path = "C:\\Users\\javie\\Documents\\NetBeansProjects\\Final Project\\newprojecttest\\";
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

            List<com.laserfiche.repository.api.clients.impl.model.Entry> entries = result.getValue();
            for (com.laserfiche.repository.api.clients.impl.model.Entry childEntry : entries) {
                int entryIdToDownload = childEntry.getId();
                com.laserfiche.repository.api.clients.impl.model.Entry entry1 = client.getEntriesClient().getEntry(repositoryId, entryIdToDownload, null).join();

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
            return path;
        } else if (entryid==3||entryid==16||entryid==17||entryid==27||entryid==28||entryid==30||entryid==31||entryid==7||entryid==8||entryid==9||entryid==6||entryid==5||entryid==13||entryid==10||entryid==11||entryid==12||entryid==14||entryid==24||entryid==26||entryid==53||entryid==54||entryid==25||entryid==51||entryid==52){
            String servicePrincipalKey = "9udNhuNq85BwiQW7R_va";
            String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiOWQwYWMwYTAtZGI4NC00N2RlLWIxZWYtMjI0ZDRiYzZkY2NhIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImFBU3NaSGFMX1M4bW1qbmVCTkktd3o2c0JBWHFTQmtPWE96V2ZtSjZQbGciLAoJCSJ4IjogImFfTHNINzBoNDVOM0dTUnV4d2RTMmhWSHFyMGcxdGdMVk9wRHV6MjFIRk0iLAoJCSJ5IjogIkpNT1AzQzV1UFNMSFBEUld2TTNTVlVrbjZfNXZkUTJWZTNaaUdTc3I4LTAiLAoJCSJkIjogIlJTS21FeGQxUFBSczBNbnBIUm5QaTNFY2x5VHpCUnJJUnhvVHlGQmhVVWMiLAoJCSJpYXQiOiAxNjc3Mjk3ODY3Cgl9Cn0";
            String repositoryId = "r-0001d410ba56";
            path = "C:\\Users\\javie\\Documents\\NetBeansProjects\\Final Project\\newprojecttest";
            AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);

            RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                    servicePrincipalKey, accessKey);

            int entryIdToDownload = entryid;

            com.laserfiche.repository.api.clients.impl.model.Entry entry1 = client.getEntriesClient().getEntry(repositoryId, entryIdToDownload, null).join();

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
        else{
        }
        return path;
    }
    
}
