package com.quickwait.news.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class NewsArticleResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private NewsArticleSourceResponse source;

    private String author;

    private String title;

    private String description;

    private String url;

    private LocalDateTime publishedAt;

    private String content;

}
