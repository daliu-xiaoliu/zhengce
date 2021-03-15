package com.yang.person.controller;


import com.yang.entities.Progress;
import com.yang.entities.Restful;
import com.yang.person.service.PersonService;
import com.yang.person.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/self")
public class SelfController {

    @Autowired
    private ProgressService progressService;

    @Autowired
    private PersonService service;

    /**
     * 查询进程，查询正在进行中和已完成的项目
     * @param userId
     * @param state
     * @param page 默认五条一页
     * @return
     */
    @GetMapping("/myProgress")
    public Restful myProgress(@RequestParam("userId") String userId, String state, int page){
        System.out.println(userId);
        if(page==0) page=page+1;
        System.out.println(userId);
        List<Progress> progresses = progressService.UserProgress(userId, state, page-1);
        if(null == progresses){
            return new Restful("401","用户id为空","请检查用户是否登录");
        }
        return new Restful("ok",progresses);
    }


    /**
     * 通过用户id和政策id查询某一条政策的进度
     * @param userId
     * @param policyId
     * @return
     */
    @GetMapping("/oneProgress")
    public Restful ongoingProgress(String userId,String policyId){
       return new Restful("ok",progressService.ProgressQuery(userId, policyId));
    }

    /**
     * 申请政策提交给管理员
     * @param userId
     * @param policyId
     * @return
     */
    @GetMapping("/applyProgress")
    public Restful applyProgress(String userId,String policyId){
        System.out.println(userId+"  "+policyId);
        if(userId ==null || policyId == null){
            return new Restful("400","参数为空");
        }
        //判断用户是否存在
        if(service.isLogin(userId)){
            if(progressService.applyProgress(userId,policyId)){
                return new Restful("ok","success 1");
            }
            return new Restful("false","未知原因");
        }
        return new Restful("401","用户不存在或没有登陆");

    }



}
