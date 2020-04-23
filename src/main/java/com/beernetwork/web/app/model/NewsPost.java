package com.beernetwork.web.app.model;

import java.util.Date;

public class NewsPost {
    private Integer id;
    private String article;
    private String textNews;
    private Date datePosting;

    public Date getDatePosting() {
        return datePosting;
    }

    public void setDatePosting(Date datePosting) {
        this.datePosting = datePosting;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextNews() {
        return textNews;
    }

    public void setTextNews(String textNews) {
        this.textNews = textNews;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
