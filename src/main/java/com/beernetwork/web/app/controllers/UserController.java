//package com.example.web.app.controllers;
//
//import com.example.web.app.dao.DbSqlite;
//import com.example.web.app.dao.UserInteractionDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class SelectUserController {
//    @Autowired
//    private UserInteractionDAO UserInteractionDAO;
//
//    private DbSqlite dbSqlite = new DbSqlite();
//    private int id = 0;
//
// //   public SelectUserController(DbSqlite dbSqlite) {
// //       this.dbSqlite = dbSqlite;
// //   }
//
////    @RequestMapping(value = "/webapp-example/register", method = RequestMethod.POST)
////    public String saveRegister(Model model, //
////                               @ModelAttribute("UserForm") @Validated UserForm UserForm, BindingResult result,
////                               final RedirectAttributes redirectAttributes) {
////        User newUser= UserInteractionDAO.createUser(UserForm);
////        return "redirect:/webapp-example/users";
////    }
////
////    @RequestMapping(value = "/webapp-example/register", method = RequestMethod.GET)
////    public String viewRegister(Model model) {
////        UserForm form = new UserForm();
////        model.addAttribute("UserForm", form);
////        return "/webapp-example/register";
////    }
////
////    @RequestMapping("/webapp-example/users")
////    public String viewMembers(Model model) {
////        List<UserForm> list = UserInteractionDAO.getUserFromDB(id);
////        model.addAttribute("users", list);
////        return "/webapp-example/users";
////    }
////
////    @RequestMapping("/web-ausers")
////    public void pressKey (Model model) {
////        HttpServletRequest req = null;
////        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/webapp-example/users.html");
////        if(requestDispatcher.equals(req) == true) {
////            List<UserForm> list = UserInteractionDAO.pressButton(true);
////        } else {
////            List<UserForm> list = UserInteractionDAO.pressButton(false);
////        }
////    }
//
//}
package com.beernetwork.web.app.controllers;

import com.beernetwork.web.app.api.request.UserByIdRequest;
import com.beernetwork.web.app.api.request.UserChangeInfoRequest;
import com.beernetwork.web.app.api.request.UserChangePasswordRequest;
import com.beernetwork.web.app.api.request.UserChangePhotoRequest;
import com.beernetwork.web.app.dao.UserInteractionDAO;
import com.beernetwork.web.app.model.User;
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
public class UserController {
    private final UserInteractionDAO UIDAO;

    public UserController(UserInteractionDAO UIDao) {
        this.UIDAO = UIDao;
    }

    @ApiOperation(value = "Получить данные пользователя")
    @RequestMapping(value = "by/SelectUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> selectUser (@RequestBody UserByIdRequest id) {

        User user = UIDAO.getUserFromDB(id.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "Создание нового User")
    @RequestMapping(value = "by/CreateNew", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createNewUser (@RequestBody User user) {

        Boolean bool = UIDAO.createNewUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(bool, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "Пользователь добавляеть свою фотографию")
    @RequestMapping(value = "change/Photo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> changePhotoUser (@RequestBody UserChangePhotoRequest userChangePhotoRequest) {

        Boolean bool = UIDAO.changePhotoUser(userChangePhotoRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(bool, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "Пользователь редактиркует информацию о себе")
    @RequestMapping(value = "change/info", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> changeInfoUser (@RequestBody UserChangeInfoRequest userChangeInfoRequest) {

        Boolean bool = UIDAO.changeInfoUser(userChangeInfoRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(bool, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "Пользователь редактирует свой пароль")
    @RequestMapping(value = "change/password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> changePasswordUser (@RequestBody UserChangePasswordRequest userChangePasswordRequest) {

        Boolean bool = UIDAO.changePasswordUser(userChangePasswordRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(bool, headers, HttpStatus.OK);
    }

//    @ApiOperation(value = "Пользователь нажимает написать сообщение")
//    @RequestMapping(value = "by/CreateNewDialog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Boolean> createNewDialog (@RequestBody UserByIdRequest userByIdRequest) {
//
//        Boolean bool = MDAO.createNewDialog(userByIdRequest);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return new ResponseEntity<>(bool, headers, HttpStatus.OK);
//    }


}
