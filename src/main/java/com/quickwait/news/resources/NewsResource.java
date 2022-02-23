package com.quickwait.news.resources;

import com.quickwait.news.dto.NewsResponse;
import com.quickwait.news.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsResource {

    private final NewsService newsService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<NewsResponse> getNews() {
        return ResponseEntity.ok(newsService.getNews());
    }

    @GetMapping(value = "/exchange")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<NewsResponse> getTopHeadlinesNews() {
        return ResponseEntity.ok(newsService.getNewsByExchange());
    }

}
