package com.yang.entities;

import java.io.Serializable;

public class User implements Serializable {

    private Integer userNum;
    private String userId;
    private String password;
    private String userName;
    private String userEmail;
    private String sex;
    private int userAge;

    public User() {
    }

    public User(Integer userNum, String userId, String password, String userName, String userEmail, String sex, int userAge) {
        this.userNum = userNum;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userEmail = userEmail;
        this.sex = sex;
        this.userAge = userAge;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNum='" + userNum + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", userAge=" + userAge +
                '}';
    }

}
