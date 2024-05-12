package com.example.albumaigenerator.model;

import java.util.List;

public class GenerateImageResponse {

    private List<GeneratedImage> data;


    public List<GeneratedImage> getData() {
        return data;
    }

    public void setData(List<GeneratedImage> data) {
        this.data = data;
    }
}
