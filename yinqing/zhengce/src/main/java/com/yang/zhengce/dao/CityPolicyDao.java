package com.yang.zhengce.dao;

import com.yang.entities.City;
import com.yang.entities.CityPolicy;

import java.util.List;

public interface CityPolicyDao {

    public CityPolicy findCity();

    public List<City> findAllCity();

}
