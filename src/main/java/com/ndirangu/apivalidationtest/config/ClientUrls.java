package com.ndirangu.apivalidationtest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sample")
@Data
public class ClientUrls {

   private String sampleGetUrl = "";
}
