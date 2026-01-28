package prolevexman.webapp.service.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import prolevexman.webapp.model.dto.mymemory.MyMemoryResponse;


import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Service
public class MyMemoryApiClient {
    private final RestClient restClient;
    private final String url;

    public MyMemoryApiClient(@Value("${mymemory.api.url}") String url) {
        this.restClient = RestClient.builder()
                .build();
        this.url = url;
    }

    @Async("translate")
    public CompletableFuture<String> translate(String input) {
        try {
            URI uri = UriComponentsBuilder
                    .fromUriString(url)
                    .queryParam("q", input)
                    .queryParam("langpair", "en|ru")
                    .build(true)
                    .toUri();

            MyMemoryResponse response = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(MyMemoryResponse.class);

            String result = response != null ? response.getResponseData().getTranslatedText() : "";

            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }
}
