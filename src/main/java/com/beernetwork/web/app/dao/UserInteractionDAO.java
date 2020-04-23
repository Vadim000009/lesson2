package com.beernetwork.web.app.dao;

import com.beernetwork.web.app.api.request.UserByIdRequest;
import com.beernetwork.web.app.api.request.UserChangeInfoRequest;
import com.beernetwork.web.app.api.request.UserChangePasswordRequest;
import com.beernetwork.web.app.api.request.UserChangePhotoRequest;
import com.beernetwork.web.app.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserInteractionDAO implements InitializingBean {
    public Logger log = Logger.getLogger(getClass().getName());
    public static String dbPath = "wholovebeer.db";
    private int ID = 0;
    private int MAXID = 0;

    @Autowired
    private JavaMailSender javaMailSender;

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
        howManyUsers();
        if(id == 0) {
            ID--;
            if(ID == -1) {
                ID = 0;
                return null;
            }
        } else if (id == 1) {
            ID++;
        }
        String query = "select fstName, secName, patronymic, gender, dateBirthday, info from USER where ID = " + ID;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stat = conn.createStatement()) {
            ResultSet resultSet = stat.executeQuery(query);
            User user = new User();
            user.setFstName(resultSet.getString("fstName"));
            user.setSecName(resultSet.getString("SecName"));
            user.setPatronymic(resultSet.getString("patronymic"));
            user.setGender(resultSet.getString("gender"));
            user.setDateBirthday(resultSet.getDate("dateBirthday"));
            user.setInfo(resultSet.getString("info"));
            // Добавить информацию про фото. Если нет, то pizdos.jpg
            return user;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Получение пользователя из БД.");
            if (ID >= MAXID) {
                return null;
            } else {
                return getUserFromDB(1);
            }
        }
    }

    private void howManyUsers() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stat = conn.createStatement()) {
            ResultSet resultSet = stat.executeQuery("SELECT max(ID) FROM USER");
            MAXID = resultSet.getInt(1);
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Получение максимального пользователя. Причина:", ex);
        }
    }

    /*
    * Создание нового пользователя
    * На основе запроса вводит данные в БД. Некоторые поля могут быть необязательными. См. register.html
    * */
    public Boolean createNewUser (User user){
        howManyUsers();
        String fstName = user.getFstName(), secName = user.getSecName(), patronymic = user.getPatronymic(), gender = user.getGender(),
                email = user.getEmail(), telephone = user.getTelephone(), password = user.getPassword(), info = user.getInfo();
        long dateBirthday = user.getDateBirthday().getTime();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        StringBuilder query = new StringBuilder();
        System.out.println(email);
        sendEmail(email);
        query.append("insert into USER (fstName, secName, patronymic, gender, dateBirthday, email, telephone, password, info) values ('")
                .append(fstName).append("','").append(secName).append("','").append(patronymic).append("','").append(gender)
                .append("','").append(dateBirthday).append("','").append(email).append("','").append(telephone).append("','")
                .append(hashedPassword).append("','").append(info).append("');");
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
            PreparedStatement stat = conn.prepareStatement(String.valueOf(query));
            stat.execute();
            return true;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Ошибка выполнения запроса. Вставка в бд. Причина: ", ex);
            return false;
        }
    }

    public Boolean changePhotoUser (@NotNull UserChangePhotoRequest userChangePhotoRequest) {
        StringBuilder queryPhoto = new StringBuilder();
        queryPhoto.append("UPDATE USER set photo=").append(userChangePhotoRequest.getUrlPic()).append("where id =").append(userChangePhotoRequest.getId());
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             PreparedStatement stat = conn.prepareStatement(String.valueOf(queryPhoto))) {
            stat.execute();
            return true;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Смена пароля пользователя. Причина:" + ex);
            return false;
        }
    }

    public Boolean changeInfoUser (@NotNull UserChangeInfoRequest userChangeInfoRequest) {
        StringBuilder queryInfo = new StringBuilder();
        queryInfo.append("UPDATE USER set info=").append(userChangeInfoRequest.getInfo()).append("where id =").append(userChangeInfoRequest.getId());
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             PreparedStatement stat = conn.prepareStatement(String.valueOf(queryInfo))) {
            stat.execute();
            return true;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Смена пароля пользователя. Причина:" + ex);
            return false;
        }
    }

    public Boolean changePasswordUser (@NotNull UserChangePasswordRequest userChangePasswordRequest, String username) {
        StringBuilder queryPassword = new StringBuilder();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userChangePasswordRequest.getPassword());
        StringBuilder queryFind = new StringBuilder();
        queryFind.append("select * from USER where email = ").append(username);
        UserByIdRequest userByIdRequest = new UserByIdRequest();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             PreparedStatement stat = conn.prepareStatement(String.valueOf(queryFind))) {
            ResultSet resultSet = stat.executeQuery();
            userByIdRequest.setId(resultSet.getInt("id"));
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Смена пароля пользователя. Получение ID пользователя. Причина:" + ex);
            return false;
        }
        queryPassword.append("UPDATE USER set password=").append(hashedPassword).append("where id =").append(userByIdRequest.getId());
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             PreparedStatement stat = conn.prepareStatement(String.valueOf(queryPassword))) {
            stat.execute();
            return true;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Смена пароля пользователя. Причина:" + ex);
            return false;
        }
    }

    public void sendEmail(String emailRegisteredNow) {
        System.out.println(emailRegisteredNow);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailRegisteredNow);
        msg.setSubject("Добро пожаловать. Снова.");
        msg.setText("Прздравляю тебя с успешной регистрацией на этом сайте.\nНе забудь почитать новости и накинуть певка перед заходои на сайт :)");
        javaMailSender.send(msg);
    }

    //Если пользователь зашёл на страницу с сообщениями - загрузить все
    //Если пользователь нажал на отправить - кинут ьв сообщения и создать таблицу
}

