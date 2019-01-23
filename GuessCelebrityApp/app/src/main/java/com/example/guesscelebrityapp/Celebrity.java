package com.example.guesscelebrityapp;

public class Celebrity {
    private String name;
    private String imgSrc;

    public Celebrity(String name, String imgSrc) {
        this.name = name;
        this.imgSrc = imgSrc;
    }

    public String getName() {
        return name;
    }

    public String getImgSrc() {
        return imgSrc;
    }
}
