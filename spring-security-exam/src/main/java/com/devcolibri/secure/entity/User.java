package com.devcolibri.secure.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    private String login;
    private String password;


    public User(long id,String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User() {

    }
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == User.class){

            User user = (User) obj;
            if(user.id == id && user.login.equals(login)&user.password.equals(password))
                return  true;
        }

        return false;
    }
}
