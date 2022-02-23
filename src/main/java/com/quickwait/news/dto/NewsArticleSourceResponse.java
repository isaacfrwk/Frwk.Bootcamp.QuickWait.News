package com.quickwait.news.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class NewsArticleSourceResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

}
