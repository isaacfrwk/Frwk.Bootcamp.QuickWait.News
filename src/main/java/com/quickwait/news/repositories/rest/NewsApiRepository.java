package com.quickwait.news.repositories.rest;

import com.quickwait.news.dto.NewsResponse;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
@Lazy
@RequiredArgsConstructor
public class NewsApiRepository {

    @Value("${uri.newsapi.api}")
    private String server;

    @Value("${uri.newsapi.apikey}")
    private String apiKey;

    private final RestTemplate restTemplate;

    private HttpHeaders createHttpHeaders() {
        return new HttpHeaders();
    }

    public NewsResponse getNews() {
        String apiUrl = server +
                "/top-headlines?category=health&country=br&language=pt&apiKey=" + apiKey;

        try {
            HttpHeaders headers = createHttpHeaders();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl);
            HttpEntity<?> entity = new HttpEntity<>(headers);

            final ResponseEntity<NewsResponse> responseEntity =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            entity,
                            NewsResponse.class);
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            Sentry.captureException(e);
            throw new HttpClientErrorException(e.getStatusCode());
        }
    }

}
