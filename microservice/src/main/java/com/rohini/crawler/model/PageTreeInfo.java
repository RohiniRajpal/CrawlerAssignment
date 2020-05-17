package com.rohini.crawler.model;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

/**
 * PageTreeInfo
 */


public class PageTreeInfo {


    @JsonProperty("total_links")
    private String total_links;

    @JsonProperty("total_images")
    private String total_images;

    @JsonProperty("details")
    private List<PageInfo> nodes;

    public String getTotal_links() {
        return total_links;
    }

    public void setTotal_links(String total_links) {
        this.total_links = total_links;
    }

    public String getTotal_images() {
        return total_images;
    }

    public void setTotal_images(String total_images) {
        this.total_images = total_images;
    }

    public List<PageInfo> getNodes() {
        return nodes;
    }

    public void setNodes(final List<PageInfo> nodes) {
        this.nodes = nodes;
    }


}
