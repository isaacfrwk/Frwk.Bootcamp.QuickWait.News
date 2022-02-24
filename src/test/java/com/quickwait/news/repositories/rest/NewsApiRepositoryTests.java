package com.quickwait.news.repositories.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.quickwait.news.dto.NewsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWireMock(port = 1234)
class NewsApiRepositoryTests {

    private static final String NEWS_API = "/top-headlines";

    @Autowired
    private NewsApiRepository newsApiRepository;

    private final ObjectMapper jsonMapper = new ObjectMapper();

    private final NewsResponse newsResponse = NewsResponse.builder()
            .totalResults(0)
            .build();

    @Test
    final void shouldGetNewsSuccess() throws Exception {
        WireMock.stubFor(WireMock.get(urlPathEqualTo(NEWS_API))
                .willReturn(aResponse().withStatus(200).withHeader("apikey", "")
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(jsonMapper.writeValueAsString(newsResponse))));
        NewsResponse resp = newsApiRepository.getNews();
        assertNotNull(resp);
    }

    @Test
    final void shouldGetNewsInternalServerException() {
        WireMock.stubFor(WireMock.get(urlPathEqualTo(NEWS_API))
                .willReturn(aResponse().withStatus(500)));
        assertThrows(HttpServerErrorException.class, () -> newsApiRepository.getNews());
    }

    @Test
    final void shouldGetNewsNotFoundException() {
        WireMock.stubFor(WireMock.get(urlPathEqualTo(NEWS_API))
                .willReturn(aResponse().withStatus(404)));
        assertThrows(HttpClientErrorException.class, () -> newsApiRepository.getNews());
    }

}
