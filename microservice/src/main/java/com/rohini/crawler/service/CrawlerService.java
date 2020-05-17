package com.telstra.codechallenge.crawler.service;

import au.com.rakesh.crawler.model.PageTreeInfo;

/**
 * @author buddy
 *
 */
public interface CrawlerService {

    /**
     * Deep crawl the page for provided depth but upto max
     *
     * @param url
     *            web page url to crawl
     * @param depth
     *            w.r.t base page url
     *
     *            already processed urls to avoid loops
     * @return page info upto desired or max depth
     */
    public PageTreeInfo deepCrawl(final String url, int depth);




    public void setMAX_COUNT(int depth);
}
