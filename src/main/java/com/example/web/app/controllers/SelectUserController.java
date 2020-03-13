package com.example.web.app.controllers;

import com.example.web.app.api.request.UserForm;
import com.example.web.app.dao.DbSqlite;
import com.example.web.app.dao.UserInteractionDAO;
import com.example.web.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SelectUserController {
    @Autowired
    private UserInteractionDAO UserInteractionDAO;

    private DbSqlite dbSqlite = new DbSqlite();
    private int id = 0;

 //   public SelectUserController(DbSqlite dbSqlite) {
 //       this.dbSqlite = dbSqlite;
 //   }

    @RequestMapping(value = "/webapp-example/register", method = RequestMethod.POST)
    public String saveRegister(Model model, //
                               @ModelAttribute("UserForm") @Validated UserForm UserForm, BindingResult result,
                               final RedirectAttributes redirectAttributes) {
        User newUser= UserInteractionDAO.createUser(UserForm);
        return "redirect:/webapp-example/users";
    }

    @RequestMapping(value = "/webapp-example/register", method = RequestMethod.GET)
    public String viewRegister(Model model) {
        UserForm form = new UserForm();
        model.addAttribute("UserForm", form);
        return "/webapp-example/register";
    }

    @RequestMapping("/webapp-example/users")
    public String viewMembers(Model model) {
        List<UserForm> list = UserInteractionDAO.getUserFromDB(id);
        model.addAttribute("users", list);
        return "/webapp-example/users";
    }

    @RequestMapping("/web-ausers")
    public void pressKey (Model model) {
        HttpServletRequest req = null;
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/webapp-example/users.html");
        if(requestDispatcher.equals(req) == true) {
            List<UserForm> list = UserInteractionDAO.pressButton(true);
        } else {
            List<UserForm> list = UserInteractionDAO.pressButton(false);
        }
    }

}
