/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author arif.erol
 */
public class BaseRepository {

    private static Boolean ifdebug = null;
    private String connStrURL = "jdbc:mysql://localhost:3306/obs?useSSL=false";
    private String user = "obsuser";
    private String pass = "obspassword";
    private String driverClassName = "com.mysql.jdbc.Driver";

    protected Connection getTestConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(connStrURL, user, pass);
            connection.setAutoCommit(true);
//            PreparedStatement ps = connection.prepareStatement("ALTER SESSION SET CURRENT_SCHEMA=obs");
//            ps.execute();
//            ps.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    protected Connection getTestConnection(String schema) throws SQLException {
        Connection connection = null;
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(connStrURL, user, pass);
            connection.setAutoCommit(true);
            //todo : semayi degistirmek icin.
            PreparedStatement ps = connection.prepareStatement("ALTER SESSION SET CURRENT_SCHEMA=" + schema);
            ps.execute();
            ps.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    protected Connection getConnectionDebugOrRelease(DataSource dataSource) throws SQLException {
        Connection con = null;
        if (this.debugging()) {
            con = this.getTestConnection();
        } else {
            con = dataSource.getConnection();
        }
        return con;
    }

    protected void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
                String clsName = this.getClass().getName();
            } catch (Exception ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
                try {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                } catch (Exception exLog) {
                    exLog.printStackTrace();
                }
            }
        }
    }

    protected void closePrepStatement(PreparedStatement ps) {
        try {
            if (ps != null && ps.isClosed() == false) {
                ps.close();
            }
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            try {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } catch (Exception exLog) {
                exLog.printStackTrace();
            }
        }
    }

    public boolean isThere(ResultSet rs, String column) {
        try {
            rs.findColumn(column);
            return true;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        return false;
    }

    public boolean isThereAndNN(ResultSet rs, String columnName) { //if isthere and not null
        try {
            rs.findColumn(columnName);
            Object colValue = rs.getObject(columnName);
            return rs.wasNull() == false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    protected Integer getNullableInt(String colName, ResultSet rs) throws SQLException {
        int colValue = rs.getInt(colName);
        if (rs.wasNull()) {
            return null;
        }
        return colValue;
    }

    protected Long getNullableLong(String colName, ResultSet rs) throws SQLException {
        Long colValue = rs.getLong(colName);
        if (rs.wasNull()) {
            return null;
        }
        return colValue;
    }

    protected Double getNullableDouble(String colName, ResultSet rs) throws SQLException {
        Double colValue = rs.getDouble(colName);
        if (rs.wasNull()) {
            return null;
        }
        return colValue;
    }

    public Connection preparedConnection(DataSource dataSource) throws SQLException {
        Connection con = null;
        if (dataSource == null && this.debugging()) {
            con = this.getTestConnection();
        } else {
            con = dataSource.getConnection();
        }
        return con;
    }

    public static boolean debugging() {
        if (ifdebug != null) {
            return ifdebug;
        }
        String arguments = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString();
        boolean ret = arguments.indexOf("-Xdebug") > 0 || arguments.indexOf("address=javadebug") > 0;
        if (ret) {
            Logger.getLogger(BaseRepository.class.getName()).log(Level.SEVERE, "DEBUG MODE!!!!!");
        } else {
            Logger.getLogger(BaseRepository.class.getName()).log(Level.SEVERE, "RELEASE MODE!!!!!");
            Logger.getLogger(BaseRepository.class.getName()).log(Level.SEVERE, "LAST DEPLOY DATE : {0}", new Date(new java.io.File(BaseRepository.class.getProtectionDomain().getCodeSource()
                    .getLocation().getPath()).lastModified()).toString());

        }
        ifdebug = ret;
        return ifdebug;
    }

}
