package com.rohini.crawler.service.impl;

import com.rohini.crawler.exceptions.CrawlerException;
import com.rohini.crawler.model.PageInfo;
import com.rohini.crawler.model.PageTreeInfo;
import com.rohini.crawler.service.CrawlerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;


/**
 * @author rohini
 *
 */

@Named
public class CrawlerServiceImpl implements CrawlerService {

    int MAX_COUNT=0;

    public void setMAX_COUNT( int depth) throws CrawlerException
       {
           if(depth < 0)
               throw new CrawlerException("depth should be greater than or equal to zeo");
              MAX_COUNT = depth+1;
       }


    public PageTreeInfo crawl(final String URL, int depth){

        PageTreeInfo pageTreeInfo = new PageTreeInfo();
        List<PageInfo> pageInfos = new ArrayList<>();
        HashSet<String> links = new HashSet<>();

        pageTreeInfo = deepCrawl(URL,depth, pageTreeInfo, pageInfos, links);
         return pageTreeInfo;
    }

    static int calculateImageSize(PageTreeInfo ptI)
    {
        int image_size = 0;
        for(PageInfo p : ptI.getNodes())
        {
           image_size = image_size + Integer.parseInt(p.getImage_count());
        }
        return image_size;
    }

    public PageTreeInfo deepCrawl(final String URL, int depth, PageTreeInfo pageTreeInfo, List<PageInfo> pageInfos, HashSet<String> links){


                if ((!links.contains(URL) && (depth<MAX_COUNT))) {
                    System.out.println(">> Depth: " + depth + " [" + URL + "]");
                    try {
                        links.add(URL);

                        Document document = Jsoup.connect(URL).get();

                        Elements linksOnPage = document.select("a[href]");
                        final Elements imgs = document.select("img[alt]");

                        String size = String.valueOf(imgs.size());
                        final String title = document.title();
                        depth++;

                        PageInfo pi = new PageInfo(title, URL, String.valueOf(imgs.size()));
                        pageInfos.add(pi);

                        int finalDepth = depth;
                        linksOnPage.parallelStream().forEach(
                                        page -> {
                                            deepCrawl(page.attr("abs:href"), finalDepth, pageTreeInfo, pageInfos, links );
                                        });

                        pageTreeInfo.setNodes(pageInfos);
                        pageTreeInfo.setTotal_links(String.valueOf(links.size()));
                        pageTreeInfo.setTotal_images(String.valueOf(calculateImageSize(pageTreeInfo)));
                    } catch (IOException e ) {
                        System.err.println("For '" + URL + "': " + e.getMessage());
                    }
                    catch (IllegalArgumentException e ) {
                        System.err.println("For '" + URL + "': " + e.getMessage());
                    }
                    catch (ConcurrentModificationException e ) {
                        System.err.println("For '" + URL + "': " + e.getMessage());
                    }
                }

            return pageTreeInfo;
        }

}
