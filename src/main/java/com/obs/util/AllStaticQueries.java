/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obs.util;

/**
 * Db query leri kolay yonetebilmek icin bir yerde toplaniyor
 *
 * @author arif.erol
 */
public class AllStaticQueries {

    public static final String SQL_SELECT_USER_INFO = "SELECT username, role FROM users WHERE username = ? AND password = ?";

    public static final String SQL_INSERT_STUDENT = "INSERT INTO student (name, surname, email) VALUES (?, ?, ?)";

    public static final String SQL_SELECT_STUDENT_ALL_LIST = "SELECT id, name, surname, email FROM student";
    
    public static final String SQL_SELECT_STUDENT_FIND_BY_NAME_OR_SURNAME = "SELECT id, name, surname, email FROM student WHERE name = ? OR surname = ?";
    
    public static final String SQL_SELECT_STUDENTGRADES_FIND_BY_SEMESTER = "SELECT student_id, student_name, grade FROM student_grades WHERE semester = ?";

}
