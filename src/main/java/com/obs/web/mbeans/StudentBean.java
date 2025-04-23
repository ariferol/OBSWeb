/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obs.web.mbeans;

/**
 *
 * @author arif.erol
 */
import com.obs.dao.StudentRepositoryLocal;
import com.obs.model.Student;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "studentBean")
@SessionScoped
@Getter
@Setter
public class StudentBean implements Serializable {

    private Student student;
    private List<Student> studentList;
    private String searchKeyword;

    @EJB
    private StudentRepositoryLocal studentRepository;

    @PostConstruct
    public void init() {
        student = new Student();
        studentList = new ArrayList<>();
        loadStudents();
    }

    public void saveStudent() {
        try {
            studentRepository.save(student);
            loadStudents(); // Listeyi güncelle
            student = new Student(); // formu sıfırla
            // Kayıt işlemi sonrasında
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Başarılı", "Öğrenci başarıyla kaydedildi."));
        } catch (Exception e) {
            e.printStackTrace();
            // UI mesajı loglanabilir
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Kayıt işlemi yapılamadı!"));
        }
    }

    public void loadStudents() {
        try {
            studentList = studentRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchStudents() throws SQLException {
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            studentList = studentRepository.findAll();
        } else {
            studentList = studentRepository.findByNameOrSurname(searchKeyword);
        }
    }
}
