package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by 3p0 on 2016-01-24.
 */
public class FileManager {

    private ArrayList<String> linksArray;

    public ArrayList<String> getLinksFromFile()
    {
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("baseUrls.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            while ((strLine = br.readLine()) != null)   {
                linksArray.add(strLine);
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linksArray;    }

    public void saveLinksToFile(HashSet<String> newUrlsBase)
    {

        //zapis znalezionych linkow
        PrintWriter print = null;
        try {
            print = new PrintWriter("baseUrls.txt");

            for (String s: newUrlsBase)
            {
                print.println(s);
            }
            print.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FileManager() {
       linksArray = new ArrayList<>();
    }
}
