package com.beernetwork.web.app.dao;

import com.beernetwork.web.app.model.NewsPost;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NewsDAO {
    public Logger log = Logger.getLogger(getClass().getName());
    private int ID = 0;
    private int MAXID = 0;

    public NewsPost getNewsFromDB(int id) {
        howManyNews();
        if(id == 0) {
            ID--;
            if(ID == -1) {
                ID = 0;
                return null;
            }
        } else if (id == 1) {
            ID++;
        }
        String query = "select article, textNews, datePosting from newsOnSite where ID = " + ID;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + UserInteractionDAO.dbPath);
             Statement stat = conn.createStatement()) {
            ResultSet resultSet = stat.executeQuery(query);
            NewsPost newsPost = new NewsPost();
            newsPost.setArticle(resultSet.getString("article"));
            newsPost.setTextNews(resultSet.getString("textNews"));
            newsPost.setDatePosting(resultSet.getDate("datePosting"));
            return newsPost;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Получение новости из БД. Причина:", ex);
            if (ID >= MAXID) {
                return null;
            } else {
                return getNewsFromDB(1);
            }
        }
    }
    private void howManyNews() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + UserInteractionDAO.dbPath);
             Statement stat = conn.createStatement()) {
            ResultSet resultSet = stat.executeQuery("SELECT max(ID) FROM newsOnSite");
            MAXID = resultSet.getInt(1);
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос. Получение максимального количества новостей. Причина:", ex);
        }
    }
}
