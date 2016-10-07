package com.lecor.lecor.testrssdigischool.Model;



public class Item {
    private int id;
    private String link;
    private String title;
    private String description;
    private String pubDate;
    private String imageLink;
    private String imageStorage;

    public Item(int id, String link, String title, String description, String pubDate, String imageLink, String imageStorage) {
        this.id = id;
        this.link = link;
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.imageLink = imageLink;
        this.imageStorage = imageStorage;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageStorage() {
        return imageStorage;
    }

    public void setImageStorage(String imageStorage) {
        this.imageStorage = imageStorage;
    }
}
