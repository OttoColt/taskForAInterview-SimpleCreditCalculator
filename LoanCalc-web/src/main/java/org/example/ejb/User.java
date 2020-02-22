package org.example.ejb;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Named
@Table(name = "Registrant")
public class User {
    @Id
    @GeneratedValue
    private int id;


    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}