package com.yang.entities;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private String userNum;
    private String userPhoto;//用户照片
    private String userIdent;//身份证号
    private String userLocate;//用户住址
    private String userEducation;//学历
    private String userUniversity;//大学

    public UserInfo() {
    }

    public UserInfo(String userNum, String userPhoto, String userIdent, String userLocate, String userEducation, String userUniversity) {
        this.userNum = userNum;
        this.userPhoto = userPhoto;
        this.userIdent = userIdent;
        this.userLocate = userLocate;
        this.userEducation = userEducation;
        this.userUniversity = userUniversity;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserIdent() {
        return userIdent;
    }

    public void setUserIdent(String userIdent) {
        this.userIdent = userIdent;
    }

    public String getUserLocate() {
        return userLocate;
    }

    public void setUserLocate(String userLocate) {
        this.userLocate = userLocate;
    }

    public String getUserEducation() {
        return userEducation;
    }

    public void setUserEducation(String userEducation) {
        this.userEducation = userEducation;
    }

    public String getUserUniversity() {
        return userUniversity;
    }

    public void setUserUniversity(String userUniversity) {
        this.userUniversity = userUniversity;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userNum='" + userNum + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", userIdent='" + userIdent + '\'' +
                ", userLocate='" + userLocate + '\'' +
                ", userEducation='" + userEducation + '\'' +
                ", userUniversity='" + userUniversity + '\'' +
                '}';
    }
}
