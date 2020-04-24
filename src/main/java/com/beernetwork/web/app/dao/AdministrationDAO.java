package com.beernetwork.web.app.dao;

import com.beernetwork.web.app.api.request.UserByIdRequest;
import com.beernetwork.web.app.model.NewsPost;
import com.beernetwork.web.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.beernetwork.web.app.dao.UserInteractionDAO.dbPath;

@Service
public class AdministrationDAO {
    public Logger log = Logger.getLogger(getClass().getName());
    private final UserInteractionDAO userInteractionDAO;

    @Autowired
    private JavaMailSender javaMailSender;

    public AdministrationDAO(UserInteractionDAO UserInteractionDAO) {
        this.userInteractionDAO = UserInteractionDAO;
    }

    public boolean deleteUserFromDB(int id) {
        if (id == 1) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Удаление пользователя из БД. Власть администратора является непоколебимой на этом сервере и ваша ничтожная команда будет использована разработчиком против вас самого");
            return false;
        }
        StringBuilder queryDelete = new StringBuilder();
        queryDelete.append("delete from USER where id = ").append(id);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
            PreparedStatement stat = conn.prepareStatement(String.valueOf(queryDelete));
            stat.execute();
            return true;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Удаление пользователя из БД. Причина: ", ex);
            return false;
        }
    }
    public UserByIdRequest findUserInDBByEmail(String email) {
        StringBuilder queryFind = new StringBuilder();
        queryFind.append("select id from USER where email = '").append(email).append("'");
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath); Statement stat = conn.createStatement()) {
            ResultSet resultSet = stat.executeQuery(String.valueOf(queryFind));
            UserByIdRequest userByIdRequest = new UserByIdRequest();
            userByIdRequest.setId(resultSet.getInt("id"));
            return userByIdRequest;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Поиск пользователя в БД. Причина: ", ex);
            return new UserByIdRequest();
        }
    }

    public boolean createNews(NewsPost newsPost) {
        String nameOfNews = newsPost.getArticle(), textNews = newsPost.getTextNews();
        long datePosting = System.currentTimeMillis();
        StringBuilder queryNews = new StringBuilder();
        queryNews.append("insert into newsOnSite (article, textNews, datePosting) values ('").append(nameOfNews).append("','")
        .append(textNews).append("','").append(datePosting).append("');");
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
            PreparedStatement stat = conn.prepareStatement(String.valueOf(queryNews));
            stat.execute();
            return true;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Создание новости. Причина: ", ex);
            return false;
        }
    }

    public Boolean restoreUserPassword(String email) {
        StringBuilder queryFindEmail = new StringBuilder();
        queryFindEmail.append("select email from USER where email = ").append(email);
        User user = new User();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath); Statement stat = conn.createStatement()) {
            ResultSet resultSet = stat.executeQuery(String.valueOf(queryFindEmail));
            user.setEmail(resultSet.getString("email"));
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Поиск почты для сброса пароля. Причина: ", ex);
            return false;
        }
        StringBuilder newPasword = new StringBuilder();
        StringBuilder queryPasword = new StringBuilder();
        for (int i = 0; i <= 8; i++) {
            if (i == 0 || i == 3 || i == 6 || i == 8) {
                int a = 97 + (int) (Math.random() * 122);
                newPasword.append((char) a);
                a = 0;
            } else if (i == 1 || i == 4 || i == 7) {
                int A = 65 + (int) (Math.random() * 90);
                newPasword.append((char) A);
                A = 0;
            } else if (i == 2 || i == 5) {
                int numeric = 48 + (int) (Math.random() * 57);
                newPasword.append((char) numeric);
                numeric = 0;
            }
        }
        // Пароль отправить на почту, а сам пароль зашифровать
        // генерируем пароль из 8 символов рандомных a-z 97-122 A-Z 65-90 цифры 48-57

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("Восстановление пароля | Beer Network");
        msg.setText("Админу сегодян кинули на пиво, и он решил восстановить твой пароль)\nВот он, кстати:" + String.valueOf(newPasword));
        javaMailSender.send(msg);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(String.valueOf(newPasword));
        queryPasword.append("UPDATE USER set password=").append(hashedPassword).append("where email =").append(email);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath); Statement stat = conn.createStatement()) {
            ResultSet resultSet = stat.executeQuery(String.valueOf(queryPasword));
            return true;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Смена пароля после отправки сообщения. Причина: ", ex);
            return false;
        }
    }


}
