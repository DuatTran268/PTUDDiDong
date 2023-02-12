package com.example.movie_ui;

public class Slide {
     private int Image;
     private String Title;

//  constructor
    public Slide(int image, String title) {
        Image = image;
        Title = title;
    }

//    getter
    public int getImage() {
        return Image;
    }

    public String getTitle() {
        return Title;
    }

//    setter
    public void setImage(int image) {
        Image = image;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
