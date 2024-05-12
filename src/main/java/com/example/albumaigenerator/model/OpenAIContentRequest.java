package com.example.albumaigenerator.model;

import java.util.ArrayList;
import java.util.List;

public class OpenAIContentRequest {

    private final String model;
    private final List<Message> messages;

    public OpenAIContentRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }

    public String getModel() {
        return model;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
