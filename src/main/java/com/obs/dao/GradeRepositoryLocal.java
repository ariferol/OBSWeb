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
import com.obs.model.StudentGrade;
import java.util.List;
import javax.ejb.Local;

@Local
public interface GradeRepositoryLocal {

    public List<StudentGrade> findGradesForSemester(String semester);
}
