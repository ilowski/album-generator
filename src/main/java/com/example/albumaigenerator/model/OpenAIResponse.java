package com.example.albumaigenerator.model;

import java.util.List;

public class OpenAIResponse {
    private List<Choice> choices;

    public OpenAIResponse() {
    }

    public OpenAIResponse(List<Choice> choices) {
        this.choices = choices;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {

        public Choice() {
        }

        private int index;
        private Message message;

        public Choice(int index, Message message) {
            this.index = index;
            this.message = message;
        }

        public int getIndex() {
            return index;
        }

        public Message getMessage() {
            return message;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void setMessage(Message message) {
            this.message = message;
        }
    }
}
