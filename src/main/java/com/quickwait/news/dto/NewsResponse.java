package com.quickwait.news.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class NewsResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status;

    private Integer totalResults;

    private List<NewsArticleResponse> articles;

}
