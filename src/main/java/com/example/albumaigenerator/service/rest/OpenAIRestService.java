package com.example.albumaigenerator.service.rest;

import com.example.albumaigenerator.configuration.OpenAIConfigurationProperties;
import com.example.albumaigenerator.model.OpenAIContentRequest;
import com.example.albumaigenerator.model.OpenAIImageRequest;
import com.example.albumaigenerator.model.GenerateImageResponse;
import com.example.albumaigenerator.model.OpenAIResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OpenAIRestService {


    private final OpenAIConfigurationProperties configurationProperties;

    private final RestTemplate restTemplate;

    public OpenAIRestService(OpenAIConfigurationProperties configurationProperties, RestTemplate restTemplate) {
        this.configurationProperties = configurationProperties;
        this.restTemplate = restTemplate;
    }

    public OpenAIResponse generateContent(final String prompt) {
        String url = configurationProperties.getUrlContent();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(configurationProperties.getApikey());

        OpenAIContentRequest bodyRequest = new OpenAIContentRequest(configurationProperties.getModelContent(), prompt);

        HttpEntity<OpenAIContentRequest> request = new HttpEntity<>(bodyRequest, headers);

        ResponseEntity<OpenAIResponse> response = restTemplate.postForEntity(url, request, OpenAIResponse.class);

        return response.getBody();
    }

    public GenerateImageResponse generateImage(final String prompt) {
            String url = configurationProperties.getUrlImage();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(configurationProperties.getApikey());

        final String model = configurationProperties.getModelImage() != null ? configurationProperties.getModelImage() : null;
        OpenAIImageRequest bodyRequest = new OpenAIImageRequest(prompt, model);

        HttpEntity<OpenAIImageRequest> request = new HttpEntity<>(bodyRequest, headers);

        ResponseEntity<GenerateImageResponse> response = restTemplate.postForEntity(url, request, GenerateImageResponse.class);

        return response.getBody();
    }
}