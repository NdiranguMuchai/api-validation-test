package com.ndirangu.apivalidationtest.service;

import com.ndirangu.apivalidationtest.model.Payload;
import reactor.core.publisher.Mono;

public interface CallApiService {
    Mono<Payload> getInfo();
}
