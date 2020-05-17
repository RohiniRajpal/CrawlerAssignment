/**
 *
 */
package com.telstra.codechallenge.crawler.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author buddy
 *
 */
@Data
@AllArgsConstructor
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 1993875051659981029L;

    private String page_title;

    private String page_link;

    private String image_count;

}
