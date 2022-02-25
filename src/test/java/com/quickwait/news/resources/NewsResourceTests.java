package com.quickwait.news.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickwait.news.dto.NewsResponse;
import com.quickwait.news.services.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class NewsResourceTests {

    private static final String NEWS_API = "/api/v1/news";

    private MockMvc mvc;

    @InjectMocks
    private NewsResource newsResource;

    @Mock
    private NewsService newsService;

    private NewsResponse newsResponse = NewsResponse.builder()
            .totalResults(0)
            .build();

    @BeforeEach
    void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(newsResource).build();
    }

    @Test
    final void shouldGetNewsIdSuccess() throws Exception {
        when(newsService.getNews()).thenReturn(newsResponse);

        mvc.perform(
                get(NEWS_API).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.totalResults").value(newsResponse.getTotalResults()));
    }

}
