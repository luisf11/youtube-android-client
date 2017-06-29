package com.example.luisf11.youtubeclient.models;

/**
 * Created by Luis Fernando Pena on 6/29/2017.
 */

public class Advertisement {

    private String imageUrl;

    public Advertisement(){}

    public Advertisement(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
