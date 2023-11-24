package com.ndirangu.apivalidationtest.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebclientConfig {

    @Bean
    public WebClient webClient(){
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                .responseTimeout(Duration.ofSeconds(10))
                .doOnConnected(connection -> {
                    connection.addHandlerLast( new ReadTimeoutHandler(10, TimeUnit.SECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(10, TimeUnit.SECONDS));
                });

        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }
}
