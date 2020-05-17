package com.telstra.codechallenge.crawler.rest;

import au.com.rakesh.crawler.configuration.AppProperties.CrawlerProperties;
import au.com.rakesh.crawler.model.PageTreeInfo;
import au.com.rakesh.crawler.service.CrawlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Optional;


@RestController
@Slf4j
public class CrawlerApiController implements CrawlerApi {

    @Inject
    private CrawlerService crawlerService;

    @Value("#{appProperties.crawler}")
    private CrawlerProperties crawlerProperties;

  @GetMapping(value = "/crawler", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PageTreeInfo> getWebPageTreeInfo(
            @NotNull @RequestParam(value = "url", required = true) final String url,
            @RequestParam(value = "depth", required = true) final Integer depth) {

        log.info("Request for deep crawling received for url: {}, depth: {}", url, depth);
        final int newDepth = Integer.min(Optional.ofNullable(depth).orElse(crawlerProperties.getDefaultDepth()),
                crawlerProperties.getMaxDepthAllowed());
        log.info(
                "Depth might be optimized to go upto Max defined in property:'app.crawler.max-depth-allowed'. optimized depth: {}",
                newDepth);
        crawlerService.setMAX_COUNT(depth);
        return new ResponseEntity<>(crawlerService.deepCrawl(url, 0), HttpStatus.OK);
    }

}
