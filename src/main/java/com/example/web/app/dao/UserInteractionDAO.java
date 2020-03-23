package com.example.web.app.dao;

import com.example.web.app.model.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserInteractionDAO implements InitializingBean {
    private Logger log = Logger.getLogger(getClass().getName());
//    private int id = 0;

    private static String dbPath = "webappp-example.db";    // переименовать
//    private static final Map<Integer, User> USERS_MAP = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        initDb();
    }

    public void initDb() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
            }
        } catch (ClassNotFoundException | SQLException ex) {
            log.log(Level.WARNING, "База не подключена. Причина: ", ex);
        }
    }

    public Boolean execute(String query) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stat = conn.createStatement()) {
            return stat.execute(query);
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос", ex);
            return false;
        }
    }

    //Пользователь вводит данные
//    public static User createUser(UserForm form) {
//        Logger logStat = null;
//        User user = new User(form.getName(), form.getSurname(), form.getMiddlename(), form.getGender(), form.getInfo());
//        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
//             Statement stat = conn.createStatement()) {
//            stat.execute("INSERT USER(NAME,SURNAME,LASTNAME, GENDER, INFORMATION) VALUES('" + user.getName() +
//                    "','" + user.getSurname() + "','" + user.getLastname() + "','" + user.getGender() + "','" + user.getInfo() + "')");
//        } catch (SQLException ex) {
//            logStat.log(Level.WARNING, "Не удалось выполнить запрос", ex);
//        }
//        return user;
//    }

    //Пользователь смотрит данные
//    public static List<UserForm> getUserFromDB(int id) {
//        Logger logStat = null;
//        String query = "select * from USER where id = " + id;
//        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
//             Statement stat = conn.createStatement()) {
//            ResultSet resultSet = stat.executeQuery(query);
//            User user = new User(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("lastname"), resultSet.getString("gender"), resultSet.getString("info"));
//            USERS_MAP.put(id, user);
//            return null;
//        } catch (SQLException ex) {
//            logStat.log(Level.WARNING, "Не удалось выполнить запрос", ex);
//        }
//        return null;
//    }

    //Пользователь нажимает вперёд назад
//    public List<UserForm> pressButton(boolean where) {
//        if (where == true) {
//            id--;
//            getUserFromDB(id);
//        } else {
//            id++;
//            getUserFromDB(id);
//        }
//        return null;
//    }

    /*
    * Пользователь жмёт кнопку следующий или предыдущий. Сервис делает инкремент числа.
    * */

    public Boolean createNewUser(User user) {
        String query = "insert into USER (fstName, secName, patronymic, gender, dateBirthday, email, telephone, password, info ) values ('"
                + user.getFstName() + "','" + user.getSecName() + "','" + user.getPatronymic() + "','" + user.getGender() + "','" +
                user.getDateBirthday() + "','" + user.getEmail() + "','" + user.getTelephone() + "','" + user.getPassword() + "','" +
                user.getInfo() + "');";
        System.out.println(query);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
            Statement stat = conn.createStatement();
            return stat.execute(query);
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Ошибка выполнения запроса. Вставка в бд. Причина: ", ex);
            return false;
        }
    }
}

