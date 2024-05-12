package com.example.albumaigenerator.model;

public class OpenAIImageRequest {

    private final String prompt;

    private final String model;

    public OpenAIImageRequest(String prompt, String model) {
        this.prompt = prompt;
        this.model = model;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getModel() {
        return model;
    }
}
