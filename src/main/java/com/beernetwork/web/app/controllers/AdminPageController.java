package com.beernetwork.web.app.controllers;

import com.beernetwork.web.app.api.request.UserByIdRequest;
import com.beernetwork.web.app.dao.AdministrationDAO;
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
@RequestMapping(value = "/api/select/admin")
public class AdminPageController {
        private final AdministrationDAO ADAO;

        public AdminPageController(AdministrationDAO ADAO) {
            this.ADAO = ADAO;
        }

        @ApiOperation(value = "Удаление пользователя (от лица администратора)")
        @RequestMapping(value = "delete/User", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Boolean> deleteUser (@RequestBody UserByIdRequest id) {

            Boolean bool = ADAO.deleteUserFromDB(id.getId());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(bool, headers, HttpStatus.OK);
        }

        @ApiOperation(value = "Добавление новости (от лица администратора)")
        @RequestMapping(value = "create/news", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Boolean> createNews (@RequestBody NewsPost newsPost) {

            Boolean bool = ADAO.createNews(newsPost);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(bool, headers, HttpStatus.OK);
        }
}
