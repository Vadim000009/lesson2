package com.beernetwork.web.app.dao;

import com.beernetwork.web.app.api.request.UserByIdRequest;
import com.beernetwork.web.app.model.NewsPost;
import org.springframework.stereotype.Service;

import java.sql.*;

import static com.beernetwork.web.app.dao.UserInteractionDAO.dbPath;
//import static com.beernetwork.web.app.dao.UserInteractionDAO.log;

@Service
public class AdministrationDAO {
    private final UserInteractionDAO userInteractionDAO;

    public AdministrationDAO(UserInteractionDAO UserInteractionDAO) {
        this.userInteractionDAO = UserInteractionDAO;
    }

    public boolean deleteUserFromDB(int id) {
        if (id == 1) {
//            log.log(Level.WARNING, "Не удалось выполнить запрос. Удаление пользователя из БД. Власть администратора является непоколебимой на этом сервере и ваша ничтожная команда будет использована разработчиком против вас самого");
            return false;
        }
        StringBuilder queryDelete = new StringBuilder();
        queryDelete.append("delete from USER where id = ").append(id);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
            PreparedStatement stat = conn.prepareStatement(String.valueOf(queryDelete));
            stat.execute();
            return true;
        } catch (SQLException ex) {
//            log.log(Level.WARNING, "Не удалось выполнить запрос. Удаление пользователя из БД. Причина: ", ex);
            return false;
        }
    }
    public UserByIdRequest findUserInDB(String email) {
        StringBuilder queryFind = new StringBuilder();
        queryFind.append("delete from USER where id = ").append(email);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath); Statement stat = conn.createStatement()) {
            ResultSet resultSet = stat.executeQuery(String.valueOf(queryFind));
            UserByIdRequest userByIdRequest = new UserByIdRequest();
            userByIdRequest.setId(resultSet.getInt("id"));
            return userByIdRequest;
        } catch (SQLException ex) {
//            log.log(Level.WARNING, "Не удалось выполнить запрос. Поиск пользователя в БД. Причина: ", ex);
            return new UserByIdRequest();
        }
    }

    public boolean createNews(NewsPost newsPost) {
        String nameOfNews = newsPost.getNameOfNews(), textNews = newsPost.getTextNews();
        long datePosting = System.currentTimeMillis();
        StringBuilder queryNews = new StringBuilder();
        queryNews.append("insert into newsOnSite (nameOfNews, textNews, datePosting) values ('").append(nameOfNews).append("','")
        .append(textNews).append("','").append(datePosting).append("');");
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
            PreparedStatement stat = conn.prepareStatement(String.valueOf(queryNews));
            stat.execute();
            return true;
        } catch (SQLException ex) {
//            log.log(Level.WARNING, "Не удалось выполнить запрос. Удаление пользователя из БД. Причина: ", ex);
            return false;
        }
    }

}
