package com.bonappetit.util;

import com.bonappetit.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    private Long id;

    private String username;

    private boolean isLogged;

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }
    public boolean isLogged() {
        return isLogged;
    }

    public void login(User user) {
        this.username = user.getUserName();
        this.isLogged = true;
        this.id = user.getId();
    }

    public void logout() {
        this.username = null;
        this.isLogged = false;
        this.id = null;
    }

}
