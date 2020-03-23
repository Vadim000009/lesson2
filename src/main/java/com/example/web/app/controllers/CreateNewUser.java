package com.example.web.app.controllers;

import com.example.web.app.dao.UserInteractionDAO;
import com.example.web.app.model.User;
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
@RequestMapping(value = "/api")
public class CreateNewUser {
    private final UserInteractionDAO UIDAO;

    public CreateNewUser(UserInteractionDAO UIDao) {
        this.UIDAO = UIDao;
    }

    @ApiOperation(value = "Создание нового User")
    @RequestMapping(value = "/create/new/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createNewUser (@RequestBody User user) {

        Boolean bool = UIDAO.createNewUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(bool, headers, HttpStatus.OK);
    }

}
