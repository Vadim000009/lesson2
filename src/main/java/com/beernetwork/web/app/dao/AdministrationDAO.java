package com.beernetwork.web.app.dao;

import com.beernetwork.web.app.model.NewsPost;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public boolean createNews(NewsPost newsPost) {
        String nameOfNews = newsPost.getNameOfNews(), textNews = newsPost.getTextNews();
        long datePosting = newsPost.getDatePosting().getTime();
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
