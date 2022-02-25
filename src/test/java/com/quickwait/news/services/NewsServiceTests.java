package com.quickwait.news.services;

import com.quickwait.news.dto.NewsResponse;
import com.quickwait.news.repositories.rest.NewsApiRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class NewsServiceTests {

    @InjectMocks
    private NewsService newsService;

    @Mock
    private NewsApiRepository newsApiRepository;

    private NewsResponse newsResponse = NewsResponse.builder()
            .totalResults(0)
            .build();

    @Test
    final void shouldGetOrderTypeByIdSuccess() {
        when(newsApiRepository.getNews()).thenReturn(newsResponse);
        NewsResponse resp = newsService.getNews();
        assertNotNull(resp);
    }

    @Test
    void shouldGetOrderTypeByIdNotFoundException() {
        when(newsApiRepository.getNews()).thenReturn(null);
        NewsResponse resp = newsService.getNews();
        assertNull(resp);
    }

}
