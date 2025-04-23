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
import com.obs.model.StudentGrade;
import com.obs.util.AllStaticQueries;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;

@Stateless
public class GradeRepository extends BaseRepository implements GradeRepositoryLocal {

    @Resource(lookup = "jdbc/obsDS")
    private DataSource dataSource;

    public List<StudentGrade> findGradesForSemester(String semester) {
        List<StudentGrade> grades = new ArrayList<>();
        try (Connection conn = this.getConnectionDebugOrRelease(dataSource);
                PreparedStatement ps = conn.prepareStatement(AllStaticQueries.SQL_SELECT_STUDENTGRADES_FIND_BY_SEMESTER)) {

            ps.setString(1, semester);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    StudentGrade grade = new StudentGrade();
                    grade.setStudentId(rs.getInt("student_id"));
                    grade.setStudentName(rs.getString("student_name"));
                    grade.setGrade(rs.getDouble("grade"));
                    grades.add(grade);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades;
    }
}
