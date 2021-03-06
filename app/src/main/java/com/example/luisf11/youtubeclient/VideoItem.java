package com.example.luisf11.youtubeclient;

/**
 * Created by luisf11 on 28/05/17.
 */

public class VideoItem {

    private String title;
    private String description;
    private String thumbnailURL;
    private String id;

    public VideoItem(){}

    public VideoItem(String title, String description, String thumbnailURL, String id) {
        this.title = title;
        this.description = description;
        this.thumbnailURL = thumbnailURL;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
