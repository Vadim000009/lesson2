package com.beernetwork.web.app.controllers;

import com.beernetwork.web.app.api.request.NewsById;
import com.beernetwork.web.app.dao.NewsDAO;
import com.beernetwork.web.app.model.NewsPost;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/select/user")
public class NewsController {
    private final NewsDAO newsDAO;

    public NewsController (NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @ApiOperation(value = "Получение новости на странице новостей")
    @RequestMapping(value = "get/news", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewsPost> getNews (@RequestBody NewsById newsById) {

        NewsPost newsPost = newsDAO.getNewsFromDB(newsById.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newsPost, headers, HttpStatus.OK);
    }
}
