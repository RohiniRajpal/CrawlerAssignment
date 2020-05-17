package com.rohini.crawler.rest;


import com.rohini.crawler.exceptions.CrawlerException;
import com.rohini.crawler.model.PageTreeInfo;
import com.rohini.crawler.service.CrawlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;


@RestController
@Slf4j
public class CrawlerApiController{

    @Inject
    private CrawlerService crawlerService;


  @GetMapping(value = "/crawler", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PageTreeInfo> getWebPageTreeInfo(
            @NotNull @RequestParam(value = "url", required = true) final String url,
            @RequestParam(value = "depth", required = true) final Integer depth) throws CrawlerException {

        log.info("Request for deep crawling received for url: {}, depth: {}", url, depth);

        crawlerService.setMAX_COUNT(depth);
        return new ResponseEntity<>(crawlerService.crawl(url, 0), HttpStatus.OK);
    }

}
