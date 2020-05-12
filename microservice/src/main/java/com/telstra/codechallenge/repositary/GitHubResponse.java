package com.telstra.codechallenge.repositary;

import lombok.Data;

import java.util.List;

@Data
class GitHubResponse<T> {
    private int total_count;
    private List<T> items;
}
