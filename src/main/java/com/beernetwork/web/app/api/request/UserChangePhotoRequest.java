package com.beernetwork.web.app.api.request;

import java.awt.*;

public class UserChangePhotoRequest {
    private Integer id;
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
