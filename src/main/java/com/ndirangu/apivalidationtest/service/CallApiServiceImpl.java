package com.ndirangu.apivalidationtest.service;

import com.ndirangu.apivalidationtest.config.ClientUrls;
import com.ndirangu.apivalidationtest.model.Payload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CallApiServiceImpl implements CallApiService{
    private final WebClient webClient;
    private final ClientUrls clientUrls;
    @Override
    public Mono<Payload> getInfo() {

        return webClient.get()
                .uri(clientUrls.getSampleGetUrl())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Payload.class);

    }
}
