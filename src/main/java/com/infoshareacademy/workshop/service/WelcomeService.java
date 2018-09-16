package com.infoshareacademy.workshop.service;

import com.infoshareacademy.workshop.utils.HibernateUtil;
import com.infoshareacademy.workshop.model.Welcome;

public class WelcomeService {
    public String getWelcomeMessage(String name, int id) {
        var welcomeGot = HibernateUtil.getSessionFactory().openSession().get(Welcome.class, id);
        return welcomeGot + name + "!";
    }
}
