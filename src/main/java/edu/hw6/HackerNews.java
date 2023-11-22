package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private HttpClient client;

    public HackerNews() {
        this.client = HttpClient.newHttpClient();
    }

    public HackerNews(HttpClient mockClient) {
        this.client = mockClient;
    }

    public long[] topStories() {
        String url = "https://hacker-news.firebaseio.com/v0/topstories.json";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            responseBody = responseBody.substring(1, responseBody.length() - 1);
            String[] ids = responseBody.split(",");
            long[] longIds = new long[ids.length];
            for (int i = 0; i < ids.length; i++) {
                longIds[i] = Long.parseLong(ids[i].trim());
            }
            return longIds;
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
    }

    public String news(long id) {
        String url = "https://hacker-news.firebaseio.com/v0/item/" + id + ".json";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(responseBody);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return "Название не найдено";
        } catch (IOException | InterruptedException e) {
            return "Ошибка ввода-вывода";
        }
    }
}
