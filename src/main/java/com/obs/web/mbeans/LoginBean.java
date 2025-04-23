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
import com.obs.dao.LoginRepositoryLocal;
import com.obs.model.User;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.NoArgsConstructor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "loginBean")
@SessionScoped
@Getter
@Setter
@NoArgsConstructor
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private boolean admin;
    private boolean authenticated;

//    private String currentPage = "studentForm.xhtml";

    @EJB
    private LoginRepositoryLocal loginRepository;

    @PostConstruct
    public void init() {
        authenticated = false;
        admin = false;
//        currentPage = "studentForm.xhtml";
    }

    public String login() throws IOException {
        System.out.println("Deneme");
        if (this.username != null && this.password != null) {
            User user = loginRepository.findByUsernameAndPassword(this.username, this.password);
            if (user != null) {
                authenticated = true;
                admin = "admin".equalsIgnoreCase(user.getRole());
//                this.currentPage = "studentForm.xhtml";
                //return "layout.xhtml?faces-redirect=true";
                return "studentForm.xhtml?faces-redirect=true";
//                String page = "layout.xhtml";
//                FacesContext.getCurrentInstance().getExternalContext().redirect(page);
//                return page;
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hatalı kullanıcı adı veya şifre!", null));
                return null;
            }
        } else {
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return authenticated;
    }

    public boolean isAdmin() {
        return admin;
    }

//    public String navigateTo(String page) {
////        this.currentPage = page;
//        return "studentForm.xhtml"; // sayfa yenilenmesin, ajax update yapsın
//    }

}
