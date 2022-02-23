package com.quickwait.news.repositories.rest;

import com.quickwait.news.dto.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Repository
@Lazy
public class NewsApiRepository {

    @Value("${uri.newsapi.api}")
    private String server;

    @Value("${uri.newsapi.endpoint}")
    private String endpoint;

    @Value("${uri.newsapi.apikey}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public NewsResponse getNews() {
        String apiUrl = server + endpoint + "?q=Saúde&apiKey=" + apiKey;

        try {
            return restTemplate.getForObject(apiUrl, NewsResponse.class);
        } catch (HttpClientErrorException e) {
            throw new HttpClientErrorException(e.getStatusCode(), e.getMessage());
        }

    }

}
