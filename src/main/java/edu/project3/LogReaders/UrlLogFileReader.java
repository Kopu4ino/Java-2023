package edu.project3.LogReaders;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;
import static java.net.http.HttpClient.newHttpClient;

public class UrlLogFileReader {

    private static final int TIMEOUT = 15;

    public Stream<String> readLines(String stringUrl) throws IOException, URISyntaxException, InterruptedException {
        var request = HttpRequest.newBuilder()
            .uri(new URI(stringUrl))
            .GET()
            .header("AcceptEncoding", "gzip")
            .timeout(Duration.of(TIMEOUT, ChronoUnit.SECONDS))
            .build();

        return newHttpClient()
            .send(request, HttpResponse.BodyHandlers.ofString())
            .body().lines();
    }
}

