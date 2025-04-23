/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obs.dao;

import com.obs.model.User;
import javax.ejb.Local;

/**
 * Local EJB bean ler web application layerdan cagirilacak
 * @author arif.erol
 */
@Local
public interface LoginRepositoryLocal {
    User findByUsernameAndPassword(String username, String password);
}
