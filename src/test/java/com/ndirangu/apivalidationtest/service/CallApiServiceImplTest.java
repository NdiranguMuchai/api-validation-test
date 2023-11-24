package com.ndirangu.apivalidationtest.service;

import com.ndirangu.apivalidationtest.model.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class CallApiServiceImplTest {
@InjectMocks
CallApiServiceImpl service;

    MockMvc mockMvc;

    @InjectMocks
    WebClient webClient;

    private final String SAMPLE_URL = "some-random-url.com";
Payload payload;
    @BeforeEach
    void setUp() {
        payload = new Payload();
        payload.setId(1L);
        payload.setData("some data");

        mockMvc = MockMvcBuilders.standaloneSetup(webClient).build();
    }

    @Test
    void getInfo_returns_200_onSuccess() throws Exception{

        Mockito.when(service.getInfo()).thenReturn(Mono.just(payload));

        mockMvc.perform(MockMvcRequestBuilders.get(SAMPLE_URL))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void getInfo_contains_expected_data() throws Exception{
        Mockito.when(service.getInfo()).thenReturn(Mono.just(payload));
        Payload toCheck = new Payload();
        toCheck.setId(1L);
        toCheck.setData("some data");

        mockMvc.perform(MockMvcRequestBuilders.get(SAMPLE_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("id"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("data"));

        assertEquals(Mono.just(toCheck), service.getInfo());
    }

    @Test
    void getInfo_returns_4xx_onError() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get(SAMPLE_URL+"xcsa"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}