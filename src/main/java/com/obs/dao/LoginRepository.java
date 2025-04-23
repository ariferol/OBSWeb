/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obs.dao;

/**
 * Stateless EJB bean lerden Db ye gidilip islemler yapiliyor
 * @author arif.erol
 */

import com.obs.model.User;
import com.obs.util.AllStaticQueries;

import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;

@Stateless
public class LoginRepository extends BaseRepository implements LoginRepositoryLocal{

    @Resource(lookup = "jdbc/obsDS")
    private DataSource dataSource;

    public User findByUsernameAndPassword(String username, String password) {
        System.out.println("test");
        try (Connection conn = this.getConnectionDebugOrRelease(dataSource);
             PreparedStatement ps = conn.prepareStatement(AllStaticQueries.SQL_SELECT_USER_INFO)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
            Logger.getLogger(LoginRepository.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }
}

