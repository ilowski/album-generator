package com.example.albumaigenerator.controller;

import com.example.albumaigenerator.service.OpenAIService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class WebController {

    private final OpenAIService openAIService;

    public WebController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/generate")
    public String generateAlbum(@RequestParam("mood") String mood, Model model) {
        try {
            Pair<byte[], List<String>> songsAndCover = openAIService.generateListOfSongsBasedOnMood(mood);
            model.addAttribute("songs", songsAndCover.getValue());
            model.addAttribute("image", Base64.getEncoder().encodeToString(songsAndCover.getKey()));

            return "index";
        } catch (IOException | InterruptedException e ) {
            model.addAttribute("songs", "Problem during processing request");

            return "index";
        }
    }
}
