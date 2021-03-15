package com.yang.controller;

import com.yang.entities.Restful;
import com.yang.service.GuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Admin")
public class AdController {

    @Autowired
    private GuanService service;

    @GetMapping("/add")
    public Restful addPolicy(@RequestParam("name") String name){
        int size=service.add(name);
        return new Restful("ok",size);
    }

    @GetMapping("/updateState")
    public Restful policyState(String userId,String state,String policyId){
        boolean b = service.updateState(userId, state, policyId);
        if(b){
            return new Restful("ok",1);
        }
        return new Restful("修改失败",0);

    }


    @GetMapping("/findAll")
    public Restful findAll(int page){
        return new Restful("ok",service.findAll(page));
    }


}
