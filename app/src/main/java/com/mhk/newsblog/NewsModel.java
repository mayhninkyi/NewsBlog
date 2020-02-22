package com.mhk.newsblog;

public class NewsModel {
    public String newsUrl,imageUrl,title,author,date;

    public NewsModel() {
    }

    public NewsModel(String newsUrl, String imageUrl, String title, String author, String date) {
        this.newsUrl = newsUrl;
        this.imageUrl = imageUrl;
        this.title = title;
        this.author = author;
        this.date = date;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getimageUrl() {
        return imageUrl;
    }

    public void setimageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
