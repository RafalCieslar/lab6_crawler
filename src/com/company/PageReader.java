package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 3p0 on 2016-01-23.
 */
public class PageReader {

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private Properties properties;
    private HashSet<String> linksHS = new HashSet<>();

    public void getStartingLinksList()
    {
        Document doc = null;
        try {
            doc = Jsoup.connect(properties.getBasicUrl()).get();
            Elements links = doc.select("a");
            for (Element e : links)
            {
               String [] baseLink = e.attr("abs:href").split("/");
                String [] baseLink2 = baseLink[2].split("#");
                String toHS = baseLink[0] + "//" + baseLink2[0];
               linksHS.add(toHS);
            }
            //zapis znalezionych linkow

            PrintWriter print = new PrintWriter("baseUrls.txt");
            for (String s: linksHS)
            {
                print.println(s);
            }
            print.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    PageReader(){
    }
}
