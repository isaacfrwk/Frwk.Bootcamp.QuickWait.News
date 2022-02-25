package com.quickwait.news.services;

import com.quickwait.news.dto.NewsResponse;
import com.quickwait.news.repositories.rest.NewsApiRepository;
import io.sentry.spring.tracing.SentrySpan;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@RequiredArgsConstructor
@Service
public class NewsService {

    private final NewsApiRepository newsApiRepository;

    @SentrySpan
    public NewsResponse getNews() {
        return newsApiRepository.getNews();
    }

}
