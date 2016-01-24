package com.company;

public class Main {

    public static void main(String[] args) {
     Properties properties = new Properties();

     PageReader initializing = new PageReader();
     initializing.setProperties(properties);
     initializing.getStartingLinksList();

        CrawlerManager crawlerManager = new CrawlerManager(properties);

       while(crawlerManager.getCurrentDeph()>0)
        {
            crawlerManager.crawl();
        }

        for(String s: crawlerManager.getGdzieZnaleziono())
        {
            System.out.println(s);
        }
    }
}
