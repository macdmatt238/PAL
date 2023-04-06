/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aquaticlazer;

import Entry.Entries;

/**
 *
 * @author arence
 */
public class Filter {
    
     public Entries[] NameFilter(Entries[] ListofEntries, String Key) {
//sefse
        int count = 0;
        for (int x = 0; x < ListofEntries.length; x++) {
            if (ListofEntries[x].name.contains(Key)) {  // Counts the number of entries with the key in its name
                count++; 
            }
        }
        Entries[] ReturnList = new Entries[count];
        int z = 0;
        for (int x = 0; x < ListofEntries.length; x++) {
            if (ListofEntries[x].name.contains(Key)) {  //Places all entries into return array
                ReturnList[z] = ListofEntries[x];
                z++;
            }
        }
        return ReturnList;
    }

    public Entries[] LengthFilter(Entries[] ListofEntries, Long Length, String Operator) {
        int count = 0;
        for (int x = 0; x < ListofEntries.length; x++) {  //counts the number of entries that are single files and not directories
            System.out.println(ListofEntries[x].path);
            if (!ListofEntries[x].isDirectory) {
                count++;
            }
        }
        Entries[] FileList = new Entries[count]; // creates FileList, an array filled with all the entries that are just files
        int z = 0;
        for (int i = 0; i < ListofEntries.length; i++) {
            if (!ListofEntries[i].isDirectory) {
                FileList[z] = ListofEntries[i]; //fills FileList
                z++;
            }
        }

        switch (Operator) { //Switch Case for Operator

            case "EQ": // "Equals to" case

                int EQcount = 0;
                for (int x = 0; x < FileList.length; x++) { //counts the number of entries in FileList which have an equal length to the length parameter
                    if (FileList[x].length == Length) {
                        EQcount++;
                    }
                }
                Entries[] EQFileList = new Entries[EQcount]; // creates an array to store the entries with equal length, EQFileList
                int a = 0;
                for (int i = 0; i < FileList.length; i++) {
                    if (FileList[i].length == Length) { // fills EQFileList
                        EQFileList[a] = FileList[i];
                        a++;
                    }
                }
                return EQFileList; //Returns EQFIleList

            case "NEQ":  //Everything else follows the same format...

                int NEQcount = 0;
                for (int x = 0; x < FileList.length; x++) {
                    if (FileList[x].length != Length) {
                        NEQcount++;
                    }
                }
                Entries[] NEQFileList = new Entries[NEQcount];
                int b = 0;
                for (int i = 0; i < FileList.length; i++) {
                    if (FileList[i].length != Length) {
                        NEQFileList[b] = FileList[i];
                        b++;
                    }
                }
                return NEQFileList;

            case "GT":

                int GTcount = 0;
                for (int x = 0; x < FileList.length; x++) {
                    if (FileList[x].length > Length) {
                        GTcount++;
                    }
                }
                Entries[] GTFileList = new Entries[GTcount];
                int c = 0;
                for (int i = 0; i < FileList.length; i++) {
                    if (FileList[i].length > Length) {
                        GTFileList[c] = FileList[i];
                        c++;
                    }
                }
                return GTFileList;

            case "GTE":

                int GTEcount = 0;
                for (int x = 0; x < FileList.length; x++) {
                    if (FileList[x].length >= Length) {
                        GTEcount++;
                    }
                }
                Entries[] GTEFileList = new Entries[GTEcount];
                int d = 0;
                for (int i = 0; i < FileList.length; i++) {
                    if (FileList[i].length >= Length) {
                        GTEFileList[d] = FileList[i];
                        d++;
                    }
                }
                return GTEFileList;

            case "LT":
                int LTcount = 0;
                for (int x = 0; x < FileList.length; x++) {
                    if (FileList[x].length < Length) {
                        LTcount++;
                    }
                }
                Entries[] LTFileList = new Entries[LTcount];
                int e = 0;
                for (int i = 0; i < FileList.length; i++) {
                    if (FileList[i].length < Length) {
                        LTFileList[e] = FileList[i];
                        e++;
                    }
                }
                return LTFileList;

            case "LTE":
                int LTEcount = 0;
                for (int x = 0; x < FileList.length; x++) {
                    if (FileList[x].length <= Length) {
                        LTEcount++;
                    }
                }
                Entries[] LTEFileList = new Entries[LTEcount];
                int f = 0;
                for (int i = 0; i < FileList.length; i++) {
                    if (FileList[i].length <= Length) {
                        LTEFileList[f] = FileList[i];
                        f++;
                    }
                }
                return LTEFileList;
            default:
                System.out.println("Invalid operator");
                return FileList;

        }

    }

    public Entries[] ContentFilter(Entries[] ListofEntries, String Key) {
        int count = 0;
        for (int x = 0; x < ListofEntries.length; x++) {  //counts the number of entries that are single files and not directories
            if (!ListofEntries[x].isDirectory) {
                count++;
            }
        }
        Entries[] FileList = new Entries[count]; // creates FileList, an array filled with all the entries that are just files
        int z = 0;
        for (int i = 0; i < ListofEntries.length; i++) {
            if (!ListofEntries[i].isDirectory) {
                FileList[z] = ListofEntries[i]; //fills FileList
                z++;
            }
        }
        int contentcount = 0;
        for (int y = 0; y < FileList.length; y++) {  // cycles through Filelist array
            for (int j = 0; j < FileList[y].contents.length; j++) { //cycles through the content array for every entry
                if (FileList[y].contents[j].contains(Key)) {
                    contentcount++;
                    break;
                }
            }
        }
        Entries[] ContentArray = new Entries[contentcount];
        int q = 0;
        for (int y = 0; y < FileList.length; y++) {
            for (int k = 0; k < FileList[y].contents.length; k++) {
                if (FileList[y].contents[k].contains(Key)) {
                    ContentArray[q] = FileList[y];
                    q++;
                    break;
                }
            }
        }
        return ContentArray;
    }

    public Entries[] CountFilter(Entries[] ListofEntries, String Key, int Min) {
        int count = 0;
        for (int x = 0; x < ListofEntries.length; x++) {  //counts the number of entries that are single files and not directories
            if (!ListofEntries[x].isDirectory) {
                count++;
            }
        }
        Entries[] FileList = new Entries[count]; // creates FileList, an array filled with all the entries that are just files
        int z = 0;
        for (int i = 0; i < ListofEntries.length; i++) {
            if (!ListofEntries[i].isDirectory) {
                FileList[z] = ListofEntries[i]; //fills FileList
                z++;
            }
        }

        for (int y = 0; y < FileList.length; y++) {  // cycles through Filelist array
            FileList[y].repcount = 0;  // sets repcount to zero for every element
            for (int j = 0; j < FileList[y].contents.length; j++) { //cycles through the content array for every entry
                if (FileList[y].contents[j].contains(Key)) {
                    FileList[y].repcount++;  // whenever "key" appears, it increments repcount
                }
            }
        }
        Entries[] ReturnArray = new Entries[count];
        int c = 0;
        for (int l = 0; l < FileList.length; l++) {
            if (FileList[l].repcount >= Min) {  // if the element's repcount is >= Min, it goes into the return array
                ReturnArray[c] = FileList[l];
                c++;
            }
        }

        return ReturnArray;
    }
    
}
