package com.beernetwork.web.app.api.request;

import java.net.URL;

public class UserChangePhotoRequest {
    private Integer id;
    private URL urlPic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public URL getUrlPic() {
        return urlPic;
    }

    public void setUrlPic(URL urlPic) {
        this.urlPic = urlPic;
    }
}
