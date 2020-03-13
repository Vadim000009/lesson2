package com.example.web.app.controllers;

import com.example.web.app.api.request.UserForm;
import com.example.web.app.dao.DbSqlite;
import com.example.web.app.dao.UserInteractionDAO;
import com.example.web.app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SelectUserController {
    private final DbSqlite dbSqlite;

    public SelectUserController(DbSqlite dbSqlite) {
        this.dbSqlite = dbSqlite;
    }

//    @ApiOperation(value = "Выборка User по id")
//    @RequestMapping(value = "by/id", method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> selectUserById(@RequestBody UserByIdRequest id) {
//        User user = dbSqlite.selectUserById(id.getId());
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return new ResponseEntity<>(user, headers, HttpStatus.OK);
//    }

//    @RequestMapping("/registerSuccessful")
//    public String viewRegisterSuccessful(Model model) {
//        return "users";
//    }

//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public String viewRegister(Model model) {
//        UserForm form = new UserForm();
//        model.addAttribute("userForm", form);
//        return "register";
//    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(Model model, //
                               @ModelAttribute("UserForm") @Validated UserForm UserForm, BindingResult result,
                               final RedirectAttributes redirectAttributes) {
        User newUser= UserInteractionDAO.createUser(UserForm);
        redirectAttributes.addFlashAttribute("flashUser", newUser);

        return "redirect:/users";
    }
}
