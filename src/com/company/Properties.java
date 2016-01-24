package com.company;

import java.io.*;

/**
 * Created by 3p0 on 2016-01-23.
 */
public class Properties {
    private String basicUrl;
    private String propertiesFileName;
    private int crawlDeep;
    private String toFind;
    private File propertiesFile;


    public void getPropertiesFromFile()
    {
        try {
            propertiesFile = new File(propertiesFileName);
            FileReader fileReader = new FileReader(propertiesFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String [] splittedLine = line.split(" ");
                if(splittedLine[0].equals("startAt"))
                {
                    this.basicUrl = splittedLine[1];
                    System.out.println("we will start at: " + basicUrl);
                }
                if(splittedLine[0].equals("szukanyWyraz"))
                {
                    this.toFind = splittedLine[1];
                    System.out.println("poszukujemy: " + toFind);
                }
                if(splittedLine[0].equals("crawlDeep"))
                {
                    this.crawlDeep = Integer.parseInt(splittedLine[1]);
                }
            }
            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("nie ma pliku z propertiesami");
        }catch (IOException e)
        {
            System.out.println("IOException in class Properties - method getPropertiesFromFile");
        }
    }

    public Properties() {
        propertiesFileName = "properties.txt";
        getPropertiesFromFile();

    }
    public String getToFind() {
        return toFind;
    }
    public String getBasicUrl() {
        return basicUrl;
    }
    public int getCrawlDeep() {
        return crawlDeep;
    }
}
