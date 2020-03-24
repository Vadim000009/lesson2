package com.beernetwork.web.app.dao;

import com.beernetwork.web.app.model.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.sql.*;
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

    /*
    * Пользователь просматривает других пользователей
    * На основе нажатия кнопки, пользователь получает информацию о том или ином пользователе. Шаг всегда +-1.
    */
    public User getUserFromDB(int id) {
        String query = "select fstName, secName, patronymic, gender, dateBirthday, info from USER where id = " + id;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stat = conn.createStatement()) {
            ResultSet resultSet = stat.executeQuery(query);
            User user = new User();
            user.setFstName(resultSet.getString("fstName"));
            user.setSecName(resultSet.getString("SecName"));
            user.setPatronymic(resultSet.getString("patronymic"));
            user.setGender(resultSet.getString("gender"));
            user.setDateBirthday(resultSet.getDate("birthday"));
            user.setInfo(resultSet.getString("info"));
            System.out.println(query);
            return user;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос", ex);
            return new User();
        }
    }

    /*
    * Создание нового пользователя
    * На основе запроса вводит данные в БД. Некоторые поля могут быть необязательными. См. register.html
    * */
    public Boolean createNewUser (User user){
        String query = "insert into USER (fstName, secName, patronymic, gender, dateBirthday, email, telephone, password, info ) values ('"
               + user.getFstName() + "','" + user.getSecName() + "','" + user.getPatronymic() + "','" + user.getGender() + "','" +
               user.getDateBirthday() + "','" + user.getEmail() + "','" + user.getTelephone() + "','" + user.getPassword() + "','" +
               user.getInfo() + "');";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
            Statement stat = conn.createStatement();
            return stat.execute(query);
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Ошибка выполнения запроса. Вставка в бд. Причина: ", ex);
            return false;
        }
    }
}
