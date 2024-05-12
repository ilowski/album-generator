package com.example.albumaigenerator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("rest.open-ai")
public class OpenAIConfigurationProperties {

    private final String apikey;
    private final String modelContent;
    private final String modelImage;
    private final String urlContent;
    private final String urlImage;

    public OpenAIConfigurationProperties(String apikey, String modelContent, String urlContent, String modelImage, String urlImage) {
        this.apikey = apikey;
        this.modelContent = modelContent;
        this.modelImage = modelImage;
        this.urlContent = urlContent;
        this.urlImage = urlImage;
    }

    public String getApikey() {
        return apikey;
    }

    public String getModelContent() {
        return modelContent;
    }

    public String getModelImage() {
        return modelImage;
    }

    public String getUrlContent() {
        return urlContent;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
