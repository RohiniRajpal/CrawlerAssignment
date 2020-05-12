package com.telstra.codechallenge.repositary;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Repository extends ViewRepository {

     private ZonedDateTime created_at;
     private Integer stargazers_count;

}
