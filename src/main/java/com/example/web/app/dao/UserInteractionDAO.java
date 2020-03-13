package com.example.web.app.dao;

import com.example.web.app.api.request.UserForm;
import com.example.web.app.model.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service()
public class UserInteractionDAO {
    private Logger log = Logger.getLogger(getClass().getName());

    private static String dbPath = "webappp-example.db";

    public void initDb() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
            }
        } catch (ClassNotFoundException | SQLException ex) {
            log.log(Level.WARNING, "База не подключена", ex);
        }
    }

    //Инициализация пользовательского списка
    public List<User> Users() {
        List<User> list = new ArrayList<>();
        return list;
    }

    //Пользователь вводит данные
    public static User createUser(UserForm form) {
        Logger logStat = null;
        User user = new User(form.getName(), form.getSurname(), form.getMiddlename(), form.getGender(), form.getInfo());
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stat = conn.createStatement()) {
            stat.execute("INSERT USER(NAME,SURNAME,LASTNAME, GENDER, INFORMATION) VALUES('" + user.getName() +
                    "','" + user.getSurname() + "','" + user.getLastname() + "','" + user.getGender() + "','" + user.getInfo() + "')");
        } catch (SQLException ex) {
            logStat.log(Level.WARNING, "Не удалось выполнить запрос", ex);
        }
        return user;
    }

    //Пользователь смотрит данные
    private void getNewUserInDB(int id){
        Map<Integer, User> userList = new HashMap<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stat = conn.createStatement()) {



            //
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос", ex);
        }
        }
    }

    //Пользователь нажимает вперёд назад

  /*  public User selectUserById(int id) {
        String query = "select * from USER where id = " + id;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stat = conn.createStatement()) {
            ResultSet resultSet = stat.executeQuery(query);
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setLastname(resultSet.getString("lastname"));
            return user;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос", ex);
            return new User();
        }
    }*/

