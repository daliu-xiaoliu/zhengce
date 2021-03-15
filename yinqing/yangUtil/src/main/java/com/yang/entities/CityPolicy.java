package com.yang.entities;

import java.io.Serializable;

public class CityPolicy implements Serializable {
    private String num;
    private String cityId;
    private String policyId;
    private int cityHot;

    public CityPolicy() {
    }

    public CityPolicy(String num, String cityId, String policyId, int cityHot) {
        this.num = num;
        this.cityId = cityId;
        this.policyId = policyId;
        this.cityHot = cityHot;
    }

    public CityPolicy(String cityId, String policyId, int cityHot) {
        this.cityId = cityId;
        this.policyId = policyId;
        this.cityHot = cityHot;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public int getCityHot() {
        return cityHot;
    }

    public void setCityHot(int cityHot) {
        this.cityHot = cityHot;
    }

    @Override
    public String toString() {
        return "CityPolicy{" +
                "cityId='" + cityId + '\'' +
                ", policyId='" + policyId + '\'' +
                ", cityHot=" + cityHot +
                '}';
    }
}
