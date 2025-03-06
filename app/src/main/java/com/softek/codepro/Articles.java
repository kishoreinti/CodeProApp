package com.softek.codepro;

public class Articles {
    private String title;
    private String description;
    private String image;  // Changed from "Image" to "image"
    private String url;
    private String content;

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

    public String getImage() {  // Updated method name
        return image;
    }

    public void setImage(String image) {  // Updated method name
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Articles(String title, String description, String image, String url, String content) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.url = url;
        this.content = content;
    }
}
