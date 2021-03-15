package com.yang.zhengce.service;


import com.yang.entities.*;
import com.yang.zhengce.dao.CityPolicyDao;
import com.yang.zhengce.dao.PolicyInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private PolicyInfoDao policyInfoDao;

    @Autowired
    private CityPolicyDao cityPolicyDao;

    /**
     * 默认为五条一页
     * @param city
     * @param page
     * @return
     */
    public List<PolicyInfo> findByCity(String city,int page,int size){
        if(size == 0){
            return policyInfoDao.findPolicyByCity(city,page*5,5);
        }else {
            return policyInfoDao.findPolicyByCity(city,page*size,size);
        }
    }

    public List<PolicyInfo> findByName(String name,int page,int size){
        System.out.println(name+"  "+page+"  "+size);
       List<City> cities=cityPolicyDao.findAllCity();
        Iterator<City> iterator=cities.listIterator();
        while (iterator.hasNext()){
            City city=iterator.next();
            System.out.println(city.getCityName());
            if(city.getCityName().equals(name)){

                return findFuzzyCity(name,page*size,size);
            }
        }
        return findFuzzyName(name,page*size,size);
    }

    private List<PolicyInfo> findFuzzyCity(String name,int page,int size){
        String findName=like(name);
        return policyInfoDao.findFuzzyCity(findName,page,size);
    }

    private List<PolicyInfo> findFuzzyName(String name,int page,int size){
        String findName=like(name);
        return policyInfoDao.findFuzzy(findName,page, size);
    }

    public List<PolicyInfo> findFitPeople(String name,int page,int size){
        String findName=like(name);
        return policyInfoDao.findFitPeople(findName,page*size,size);
    }

    private String like(String name){
        return "%"+name+"%";
    }


    public List<PolicyInfo> findByLogin(String userId,int page,int size){
        UserInfo user= policyInfoDao.findUser(userId);
        List<PolicyInfo> policyInfos=new ArrayList<>();
        if(null == user){
            return findByCity("太原",page,size);
        }
        if(null != user.getUserLocate()){
            List<PolicyInfo> fuzzyCity = findFuzzyCity(user.getUserLocate(), page*size, size);
            System.out.println(fuzzyCity.size());
            if(fuzzyCity.size() != 0){
                policyInfos.addAll(fuzzyCity);
                return policyInfos;
            }
        }
        if(null != user.getUserEducation()){
            System.out.println(user.getUserEducation());
            policyInfos.addAll(findFitPeople(user.getUserEducation(),page*size,size));
        }
        return policyInfos;
    }


}
