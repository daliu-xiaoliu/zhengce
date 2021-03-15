package com.yang.entities;

public class City {

    private int num;
    private String cityId;
    private String cityName;
    private String cityProvince;
    private String cityType;

    public City() {
    }

    public City(int num, String cityId, String cityName, String cityProvince, String cityType) {
        this.num = num;
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityProvince = cityProvince;
        this.cityType = cityType;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityProvince() {
        return cityProvince;
    }

    public void setCityProvince(String cityProvince) {
        this.cityProvince = cityProvince;
    }

    public String getCityType() {
        return cityType;
    }

    public void setCityType(String cityType) {
        this.cityType = cityType;
    }

    @Override
    public String toString() {
        return "City{" +
                "num=" + num +
                ", cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityProvince='" + cityProvince + '\'' +
                ", cityType='" + cityType + '\'' +
                '}';
    }
}
