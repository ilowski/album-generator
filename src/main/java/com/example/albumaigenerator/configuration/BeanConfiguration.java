package com.example.albumaigenerator.configuration;

import com.example.albumaigenerator.service.ImageDownloadService;
import com.example.albumaigenerator.service.OpenAIService;
import com.example.albumaigenerator.service.rest.OpenAIRestService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(OpenAIConfigurationProperties.class)
public class BeanConfiguration {

    @Bean
    OpenAIService openAIService(OpenAIRestService openAIRestService) {
        return new OpenAIService(openAIRestService, new ImageDownloadService());
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

}
