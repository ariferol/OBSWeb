/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obs.dao;

/**
 * Local EJB bean ler web application layerdan cagirilacak
 * @author arif.erol
 */
import com.obs.model.Student;
import java.sql.*;
import java.util.List;
import javax.ejb.Local;

@Local
public interface StudentRepositoryLocal {

    void save(Student student) throws SQLException;

    List<Student> findAll() throws SQLException;

    List<Student> findByNameOrSurname(String searchKeyword) throws SQLException;
}
