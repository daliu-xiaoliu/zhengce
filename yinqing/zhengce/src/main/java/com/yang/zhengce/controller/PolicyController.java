package com.yang.zhengce.controller;

import com.yang.entities.Restful;
import com.yang.zhengce.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService service;


    @GetMapping("/findByCity")
    public Restful findByCity(String city,int page,int size){
        System.out.println(city+"  " + page+"  " +size);
        if(city==null){
            return new Restful("ok",service.findByCity("北京",page-1,size));
        }
        return new Restful("ok",service.findByCity(city,page-1,size));
    }

    @GetMapping("/findByName")
    public Restful findByName(String target,String name,int page, int size){
        if(null != target){
            if(service.findFitPeople(target,page-1,size).size()!=0){
                return new Restful("用户未登录或信息不完善",service.findFitPeople(target,page-1,size));
            }
        }
        if(null == name){
            return new Restful("name is null", service.findByName("测试",page-1,size));
        }
        return new Restful("200",name,size,service.findByName(name,page-1,size));
    }

    @GetMapping("/findByLogin")
    public Restful findByLogin(String userId,int page, int size){
        if(null == userId){
            return new Restful("ok",service.findByCity("太原",page-1,size));
        }
        return new Restful("ok",service.findByLogin(userId, page-1, size));

    }


}
