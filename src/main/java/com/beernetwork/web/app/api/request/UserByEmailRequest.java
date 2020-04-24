package com.beernetwork.web.app.api.request;

import com.beernetwork.web.app.dao.UserInteractionDAO;
import com.beernetwork.web.app.model.AuthorizationUser;

import java.sql.*;

public class UserByEmailRequest {

    public static AuthorizationUser selectUserByEmail(String email) {
        StringBuilder query = new StringBuilder();
        query.append("select email, password from USER where email = \"").append(email).append("\"");
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:" + UserInteractionDAO.dbPath);
            Statement stat = conn.createStatement()) {
            ResultSet resultSet = stat.executeQuery(String.valueOf(query));
            AuthorizationUser user = new AuthorizationUser();
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            if(user.getEmail().equals("tuukvadim@live.com")) {
                user.setRole("ADMIN");
            } else {
                user.setRole("USER");
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Ошибка получения пользователя из БД при аутентификации" + e.getMessage());
            return new AuthorizationUser();
        }
    }
}
