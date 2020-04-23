package com.beernetwork.web.app.controllers;

import com.beernetwork.web.app.api.request.AdminSearchUserByEmailRequest;
import com.beernetwork.web.app.api.request.UserByIdRequest;
import com.beernetwork.web.app.dao.AdministrationDAO;
import com.beernetwork.web.app.model.NewsPost;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
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
        @RequestMapping(value = "delete/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Boolean> deleteUser (@RequestBody UserByIdRequest id) {

            Boolean bool = ADAO.deleteUserFromDB(id.getId());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(bool, headers, HttpStatus.OK);
        }

        @ApiOperation(value = "Поиск юзверя в базе данных по почте (от лица администратора)")
        @RequestMapping(value = "search/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
            public ResponseEntity<UserByIdRequest> searchUser (@NotNull @RequestBody AdminSearchUserByEmailRequest adminSearchUserByEmailRequest) {
            UserByIdRequest id = ADAO.findUserInDBByEmail(adminSearchUserByEmailRequest.getEmail());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(id, headers, HttpStatus.OK);
    }

        @ApiOperation(value = "Добавление новости (от лица администратора)")
        @RequestMapping(value = "create/news", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Boolean> createNews (@RequestBody NewsPost newsPost) {

            Boolean bool = ADAO.createNews(newsPost);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(bool, headers, HttpStatus.OK);
        }
        @ApiOperation(value = "сброс пароля пользователю (от лица администратора)")
        @RequestMapping(value = "restore/password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Boolean> deleteUserPassword (@NotNull @RequestBody AdminSearchUserByEmailRequest adminSearchUserByEmailRequest) {

            Boolean bool = ADAO.restoreUserPassword(adminSearchUserByEmailRequest.getEmail());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(bool, headers, HttpStatus.OK);
    }
}
