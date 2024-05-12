package com.example.albumaigenerator.service;

import com.example.albumaigenerator.model.GenerateImageResponse;
import com.example.albumaigenerator.model.OpenAIResponse;
import com.example.albumaigenerator.service.rest.OpenAIRestService;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class OpenAIService {

    private final OpenAIRestService openAIRestService;
    private final ImageDownloadService imageDownloadService;
    public OpenAIService(OpenAIRestService openAIRestService,
                         ImageDownloadService imageDownloadService) {
        this.openAIRestService = openAIRestService;
        this.imageDownloadService = imageDownloadService;
    }

    public Pair<byte[], List<String>> generateListOfSongsBasedOnMood(final String userMood) throws IOException, InterruptedException {
        String rewrittenMood = rewriteMood(userMood);
        List<String> songs = prepareAlbumSongs(rewrittenMood);
        final String imageUrl = generateImageBasedOnDescription(rewrittenMood, Arrays.toString(songs.toArray()));
        byte[] image = imageDownloadService.downloadImageAsByteArray(imageUrl);

        return Pair.of(image, songs);
    }

    public String generateImageBasedOnDescription(final String mood, final String songs) {
        final String prompt = String.format("Please generate album cover that fit songs: %s and mood user %s", songs, mood);
        final GenerateImageResponse albumCover = openAIRestService.generateImage(prompt);
        return albumCover.getData().get(0).getUrl();
    }

    private String rewriteMood(final String mood) {
        final String prompt = "Please provide a music type description using only adjectives and the category of music that fits given text. Write only adjectives and category. Text: " + mood;
        final OpenAIResponse rewrittenMood = openAIRestService.generateContent(prompt);
        return rewrittenMood.getChoices().get(0).getMessage().getContent();
    }

    private List<String> prepareAlbumSongs(final String mood) {
        final String prompt = "please provide album songs idea based on this given adjectives that describe user mood and music category. The list should be formatted with each song on a new line, beginning with a dash. The format should be 'Artist: Song Title'. I need a minimum of 10 song ideas: " + mood;
        final OpenAIResponse rewrittenMood = openAIRestService.generateContent(prompt);
        return Arrays.asList(rewrittenMood.getChoices().get(0).getMessage().getContent().substring(1).split("-"));
    }
}
