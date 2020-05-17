package com.rohini.crawler.service;

import com.rohini.crawler.exceptions.CrawlerException;
import com.rohini.crawler.model.PageTreeInfo;
import org.springframework.stereotype.Component;

/**
 * @author buddy
 *
 */
@Component
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
    public PageTreeInfo crawl(final String url, int depth);

    public void setMAX_COUNT(int depth) throws CrawlerException;
}
