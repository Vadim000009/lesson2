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
public class SelectUserController {
    private final UserInteractionDAO UIDAO;

    public SelectUserController(UserInteractionDAO UIDao) {
        this.UIDAO = UIDao;
    }

    @ApiOperation(value = "Получить данные пользователя")
    @RequestMapping(value = "/create/new/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> selectUser (@RequestBody User user) {

        Boolean bool = UIDAO.getUserFromDB(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(bool, headers, HttpStatus.OK);
    }
}
