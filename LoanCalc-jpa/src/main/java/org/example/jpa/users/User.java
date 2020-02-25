package org.example.jpa.users;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Named
@Table(name = "Registrant")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Size(min = 1, max = 25)
    @Column(nullable = false, unique = true)
    private String login;

    @Size(min = 1, max = 25)
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