package org.ac.cst8277.mignardi.eric.messageservice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class CustomWebClient {

    private String umsHost = "http://localhost";
    private String umsPort = "8080";

    public Mono<Object> retrieveUmsData(String uri) {
        WebClient client = WebClient.builder().baseUrl(umsHost + ":" + umsPort)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
        return client.method(HttpMethod.GET).uri(uri).accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8).retrieve().bodyToMono(Object.class);
    }

}