package com.sougata.bookstore.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Component
@Data
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;
    private String username;
    private String password;
    private long enabled;

    public User(String username, String password, int contact) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User() {
    }

    /*public User(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.contact = user.getContact();
        this.userid = user.getUserid();
    }*/

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getEnabled() {
        return enabled;
    }

    public void setEnabled(long enabled) {
        this.enabled = enabled;
    }
}
