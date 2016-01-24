package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

/**
 * Created by 3p0 on 2016-01-24.
 */
public class Crawler extends Thread{

    public String getUrl() {
        return url;
    }

    private String url;

    public boolean isBusy() {
        return isBusy;
    }

    private boolean isBusy;
    private HashSet<String> crawledUrls;
    private Properties properties;
    private boolean pageWasCrawled;


    public void run()
    {
        pageWasCrawled = true;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a");
            Parser parser = new Parser();

            for (Element e : links)
            {
                String replaced = e.text().replace(":","");
                if(parser.findWord(replaced,properties.getToFind()))
                {
                    crawledUrls.add(e.attr("abs:href"));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e)
        {
            System.out.println("dupa");
        }
    }

    Crawler()
    {
       crawledUrls = new HashSet<>();
        pageWasCrawled = true;
        isBusy = false;

    }
    Crawler(Properties p)
    {
        this.properties = p;
        crawledUrls = new HashSet<>();
        pageWasCrawled = true;
        isBusy = false;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    public void setUrl(String url) {
        this.isBusy = true;
        this.url = url;
        pageWasCrawled = false;
    }
    public HashSet<String> getCrawledUrls() {
        isBusy = false;
        return crawledUrls;
    }

    public boolean isPageWasCrawled() {
        return pageWasCrawled;
    }

}
