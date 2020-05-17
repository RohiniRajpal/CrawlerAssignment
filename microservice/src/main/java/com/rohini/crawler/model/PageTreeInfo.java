package com.telstra.codechallenge.crawler.model;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

/**
 * PageTreeInfo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-02T22:39:21.740Z")

public class PageTreeInfo {

    private static final long serialVersionUID = -8520773347521909293L;

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
