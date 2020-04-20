package com.beernetwork.web.app.api.request;

public class MessageByUserSendRequest {
    private Integer id;
    private String text;
    private Integer whoSend;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getWhoSend() {
        return whoSend;
    }

    public void setWhoSend(Integer whoSend) {
        this.whoSend = whoSend;
    }
}
