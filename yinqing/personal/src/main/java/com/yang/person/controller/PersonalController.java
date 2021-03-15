package com.yang.person.controller;

import com.yang.entities.RelateUser;
import com.yang.entities.Restful;
import com.yang.entities.User;
import com.yang.entities.UserInfo;
import com.yang.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PersonalController {

    @Autowired
    private PersonService  service;

    /**
     * 查询用户全部信息
     * @param userId
     * @return
     */
    @GetMapping("/showInfo")
    public Restful showInfo(String userId){
        if(null == userId){
            return new Restful("401","用户名为空",0);
        }
        RelateUser relateUser = service.relateUser(userId);
        System.out.println(relateUser);
        if(null !=relateUser){
            return new Restful("ok",relateUser);
        }
        return new Restful("402","未检测到用户登录状态",0);
    }


    @GetMapping("/showSimpleInfo/{userId}")
    public Restful showSimpleInfo(@PathVariable("userId") String userId){
        User user = service.findUser(userId);
        return new Restful("200","ok",0,user);
    }


    /**
     * 上传图片，完善用户信息
     * @param multipartFile 解析图片
     * @param userInfo 用户信息
     * @return
     */
    @PostMapping("/upload")
    public Restful upload(MultipartFile multipartFile, UserInfo userInfo){
        System.out.println(multipartFile);
        System.out.println(userInfo);
        String image = service.uploadImage(multipartFile, userInfo);
        if(image.equals("上传失败")){
            return new Restful("500","false",image);
        }
        return new Restful("ok",image);
    }

    /**
     * 删除用户信息
     * @param userId
     * @param password
     * @return
     */
    @DeleteMapping("/deleteInfo")
    public Restful deleteInfo(String userId,String password){

        if(service.delete(userId,password)){
            return new Restful("删除成功",service.relateUser(userId));
        }
        return new Restful("密码错误，删除失败",0);
    }

    @RequestMapping("/addMore")
    public Restful addMore(String userId,String age,String sex){
        int info=service.addMore(userId, age, sex);
        if(info==1){
            return new Restful("ok",service.relateUser(userId));
        }else if(info==-1){
            return new Restful("401","账号为空");
        }else {
            return new Restful("500","修改失败");
        }
    }

}
