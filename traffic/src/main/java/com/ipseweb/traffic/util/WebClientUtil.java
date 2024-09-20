package com.ipseweb.traffic.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Consumer;

@Slf4j
@Component
public class WebClientUtil {

    WebClient webClient;

    public WebClientUtil(WebClient webClient) {
        this.webClient = webClient;
    }

    public String get(String url) {
        String response;

        response = webClient.get()
                .uri(url)
                .retrieve().bodyToMono(String.class).subscribe().toString();

        log.info("response: {}", response);

        return response;
    }

    public String get(String url, Consumer<HttpHeaders> headers) {
        String response;

        response = webClient.get()
                .uri(url)
                .headers(httpHeaders -> headers.accept(httpHeaders))
                .retrieve().bodyToMono(String.class).subscribe().toString();

        log.info("response: {}", response.getBytes());

        return response;
    }

    public String post() {return "";}

}
