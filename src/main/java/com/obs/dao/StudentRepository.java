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
import com.obs.model.Student;
import com.obs.util.AllStaticQueries;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class StudentRepository extends BaseRepository implements StudentRepositoryLocal {

    @Resource(lookup = "jdbc/obsDS")
    private DataSource dataSource;

    public void save(Student student) throws SQLException {
        try (Connection conn = this.getConnectionDebugOrRelease(dataSource);
                PreparedStatement ps = conn.prepareStatement(AllStaticQueries.SQL_INSERT_STUDENT)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.executeUpdate();
        }
    }

    public List<Student> findAll() throws SQLException {
        List<Student> list = new ArrayList<>();
        try (Connection conn = this.getConnectionDebugOrRelease(dataSource);
                PreparedStatement ps = conn.prepareStatement(AllStaticQueries.SQL_SELECT_STUDENT_ALL_LIST);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setSurname(rs.getString("surname"));
                s.setEmail(rs.getString("email"));
                list.add(s);
            }
        }
        return list;
    }

    public List<Student> findByNameOrSurname(String searchKeyword) throws SQLException {
        List<Student> list = new ArrayList<>();
        try (Connection conn = this.getConnectionDebugOrRelease(dataSource);
                PreparedStatement ps = conn.prepareStatement(AllStaticQueries.SQL_SELECT_STUDENT_FIND_BY_NAME_OR_SURNAME);) {
            ps.setString(1, searchKeyword);
            ps.setString(2, searchKeyword);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student s = new Student();
                    s.setId(rs.getInt("id"));
                    s.setName(rs.getString("name"));
                    s.setSurname(rs.getString("surname"));
                    s.setEmail(rs.getString("email"));
                    list.add(s);
                }
            }
        }
        return list;
    }
}
