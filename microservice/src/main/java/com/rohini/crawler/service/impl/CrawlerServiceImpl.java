package com.telstra.codechallenge.crawler.service.impl;

import au.com.rakesh.crawler.configuration.AppProperties.CrawlerProperties;
import au.com.rakesh.crawler.model.PageInfo;
import au.com.rakesh.crawler.model.PageTreeInfo;
import au.com.rakesh.crawler.service.CrawlerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author buddy
 *
 */

@Named
public class CrawlerServiceImpl implements CrawlerService {

    @Value("#{appProperties.crawler}")
    private CrawlerProperties crawlerProperties;

    /*
     * recursive crawler to fetch child pages upto desired depth / max depth
     * (non-Javadoc)
     *
     * @see au.com.rakesh.crawler.service.CrawlerService#deepCrawl(java.lang.String,
     * int)
     */
    /*@Override
    @CacheResult(cacheName = "web-crawler-service")*/
    /*public PageTreeInfo deepCrawl(final String url, final int depth, final List<String> processedUrls) {

        log.debug("Starting crawler for url {} for depth {}", url, depth);
        if (depth < 0) {
            log.info("Maximum depth reached, backing out for url {}", url);
            return null;
        } else {
            final List<String> updatedProcessedUrls = Optional.ofNullable(processedUrls).orElse(new ArrayList<>());
            if (!updatedProcessedUrls.contains(url)) {
                updatedProcessedUrls.add(url);
                final PageTreeInfo pageTreeInfo = new PageTreeInfo(,url,);
                crawl(url).ifPresent(pageInfo -> {
                    pageTreeInfo.title(pageInfo.getTitle()).valid(true);
                    log.info("Found {} links on the web page: {}", pageInfo.getLinks().size(), url);
                    pageInfo.getLinks().forEach(link -> {
                        pageTreeInfo.addNodesItem(deepCrawl(link.attr("abs:href"), depth - 1, updatedProcessedUrls));
                    });
                });
                return pageTreeInfo;
            } else {
                return null;
            }
        }

    }*/

   /* import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

    public class WebCrawlerWithDepth {
        private static final int MAX_DEPTH = 2;
        private HashSet<String> links;

        public WebCrawlerWithDepth() {
            links = new HashSet<>();
        }*/
        int MAX_COUNT=0;
       public void setMAX_COUNT( int depth)
       {
              MAX_COUNT = depth+1;
       }

        private HashSet<String> links = new HashSet<>();
        PageTreeInfo ptI = new PageTreeInfo();
        List<PageInfo> pil = new ArrayList<>();

        public PageTreeInfo deepCrawl(final String URL, int depth) {

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

                        PageInfo pi = new PageInfo(title, URL, size);
                        pil.add(pi);
                        for (Element page : linksOnPage) {
                            deepCrawl(page.attr("abs:href"), depth);

                        }
                        ptI.setNodes(pil);
                        ptI.setTotal_links(String.valueOf(linksOnPage.size()));
                        ptI.setTotal_images(size);
                    } catch (IOException e) {
                        System.err.println("For '" + URL + "': " + e.getMessage());
                    }
                }

            return ptI;
        }

       /* public static void main(String[] args) {
            new WebCrawlerWithDepth().getPageLinks("http://www.mkyong.com/", 0);
        }
    }*/
    /*
     * Method to fetch web page content. Cache is used for better performance
     *
     * @see au.com.rakesh.crawler.service.CrawlerService#crawl(java.lang.String)
     */
    /*@Override

    public Optional<PageInfo> crawl(final String url) {

        //log.info("Fetching contents for url: {}", url);
        try {
            final Document doc = Jsoup.connect(url).timeout(crawlerProperties.getTimeOut())
                    .followRedirects(crawlerProperties.isFollowRedirects()).get();

            *//** .select returns a list of links here **//*
            final Elements links = doc.select("a[href]");
            final Elements imgs = doc.select("img[alt]");
            String size = String.valueOf(imgs.size());
            final String title = doc.title();
            //log.debug("Fetched title: {}, links[{}], imgs{} for url: {}", title, links.nextAll(), size, url);
            return Optional.of(new PageInfo(title, url, size));
        } catch (final IOException | IllegalArgumentException e) {
            //log.error(String.format("Error getting contents of url %s", url), e);
            return Optional.empty();
        }

    }*/

}
