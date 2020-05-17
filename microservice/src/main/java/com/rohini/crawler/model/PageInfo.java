/**
 *
 */
package com.rohini.crawler.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author rohini
 *
 */
@Data
@AllArgsConstructor
public class PageInfo {

    private String page_title;

    private String page_link;

    private String image_count;

}
