package edu.hw6;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

class HackerNewsTest {
    @Test
    void testHackerNewsTopStories() throws Exception {
        // Arrange
        HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

        Mockito.when(mockResponse.body()).thenReturn("[777, 888]");
        Mockito.when(mockHttpClient.send(
                Mockito.any(HttpRequest.class),
                any(HttpResponse.BodyHandlers.ofString().getClass())
            ))
            .thenReturn(mockResponse);

        HackerNews hackerNews = new HackerNews(mockHttpClient);

        // Act
        long[] actual = hackerNews.topStories();

        // Assert
        assertThat(actual).containsExactly(777, 888);
    }

    @Test
    void testNews() throws Exception {
        // Arrange
        HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

        Mockito.when(mockResponse.body()).thenReturn("{\"title\":\"Test News Title\"}");
        Mockito.when(mockHttpClient.send(
                Mockito.any(HttpRequest.class),
                any(HttpResponse.BodyHandlers.ofString().getClass())
            ))
            .thenReturn(mockResponse);

        HackerNews hackerNews = new HackerNews(mockHttpClient);

        // Act
        String actual = hackerNews.news(777);

        // Assert
        assertThat(actual).isEqualTo("Test News Title");
    }
}
