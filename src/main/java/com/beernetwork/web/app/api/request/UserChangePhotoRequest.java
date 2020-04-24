package com.beernetwork.web.app.api.request;

import org.springframework.web.multipart.MultipartFile;

public class UserChangePhotoRequest {
    private Integer id;

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    private MultipartFile image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
