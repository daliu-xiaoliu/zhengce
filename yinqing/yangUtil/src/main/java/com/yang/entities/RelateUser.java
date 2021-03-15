package com.yang.entities;

import java.io.Serializable;

public class RelateUser implements Serializable {

    private User user;
    private UserInfo userInfo;

    public RelateUser() {
    }

    public RelateUser(User user, UserInfo userInfo) {
        this.user = user;
        this.userInfo = userInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "RelateUser{" +
                "user=" + user +
                ", userInfo=" + userInfo +
                '}';
    }
}
