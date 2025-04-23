/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obs.model;

/**
 *
 * @author arif.erol
 */

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private String surname;
    private String email;
}

