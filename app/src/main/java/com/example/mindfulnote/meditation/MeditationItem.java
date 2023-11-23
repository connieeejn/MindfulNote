package com.example.mindfulnote.meditation;

public class MeditationItem {
    private int imageResource;
    private String headline;
    private String description;

    public MeditationItem(int imageResource, String headline, String description) {
        this.imageResource = imageResource;
        this.headline = headline;
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getHeadline() {
        return headline;
    }

    public String getDescription() {
        return description;
    }
}
