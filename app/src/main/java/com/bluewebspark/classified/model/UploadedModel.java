package com.bluewebspark.classified.model;

import android.graphics.Bitmap;

public class UploadedModel {

    Bitmap image;

    public UploadedModel(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
