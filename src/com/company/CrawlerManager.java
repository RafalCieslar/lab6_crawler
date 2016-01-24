package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 3p0 on 2016-01-24.
 */
public class CrawlerManager {
    private Properties properties;
    private ArrayList<String> actualUrlBase;

    public HashSet<String> getGdzieZnaleziono() {
        return gdzieZnaleziono;
    }

    private HashSet<String> gdzieZnaleziono;
    private HashSet<String>nextUrlBase;
    private int currentDeph;
    private ArrayList<Crawler> crawlers;


    private void getLinksFromFile()
    {
       FileManager fileManager = new FileManager();

      if (currentDeph > 0)
      {
          actualUrlBase.addAll(fileManager.getLinksFromFile());
      }
      else
      {
          System.out.println("zakonczono crawling");
      }

    }

    public void crawl()
    {
        getLinksFromFile();
        if (actualUrlBase.size() == 4)
        {
            System.out.println();
        }
        // przeszukiwanie linkow z bazy


        while(!actualUrlBase.isEmpty())
        {
            //przypisanie crawlerom adresow url
            for (int i =0; i<crawlers.size();i++)
            {
                if(actualUrlBase.size() == 0) break;
                    if(!crawlers.get(i).isBusy() && actualUrlBase.get(0) != null)
                    {
                        crawlers.get(i).setUrl(actualUrlBase.get(0));
                    }

                actualUrlBase.remove(0);
            }
            //uruchomienie crawlerow w celu pobrania listy linkow
            for (int i =0; i<crawlers.size();i++)
            {
                if(!crawlers.get(i).isPageWasCrawled()) {
                    crawlers.get(i).run();
                }
                for(String j : crawlers.get(i).getCrawledUrls()) {
                       nextUrlBase.add(j);
                       if(!gdzieZnaleziono.contains(j)) gdzieZnaleziono.add(j);
                }
                crawlers.set(i,new Crawler(properties));
            }
        }
        //aktualna baza jest pusta wiec czas zejsc glebiej
        FileManager fileManager = new FileManager();
        fileManager.saveLinksToFile(nextUrlBase);
        nextUrlBase = new HashSet<>();
        this.currentDeph --;
        System.out.println(currentDeph);
    }

    CrawlerManager(Properties properties)
    {
        this.properties = properties;
        this.currentDeph = properties.getCrawlDeep();
        this.actualUrlBase = new ArrayList<>();
        this.gdzieZnaleziono = new HashSet<>();
        this.nextUrlBase = new HashSet<>();
        this.crawlers = new ArrayList<Crawler>();
        for (int i =0;i<10;i++) {
            crawlers.add(new Crawler());
            crawlers.get(i).setProperties(properties);
        }

    }

    public int getCurrentDeph() {
        return currentDeph;
    }
}
