package com.yang.person.service;

import com.yang.entities.Progress;
import com.yang.person.dao.UserSelectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private UserSelectDao userSelectDao;

    /**
     * 查询用户项目进度
     * @param userId
     * @param state
     * @param page
     * @return
     */
    public List<Progress> UserProgress(String userId,String state,int page){
        System.out.println(state);
        if(null == userId){
            return null;
        }
        if(null == state){
            userSelectDao.UserProgress(userId,page*5);
        }else
        if(state.equals("进行中")){
            System.out.println(userSelectDao.UserPassProgress(userId, page));
            return userSelectDao.UserPassProgress(userId,page*5);
        }else if(state.equals("所有")){
            return userSelectDao.UserProgress(userId,page*5);
        }else {
            return userSelectDao.fitProgress(userId,page*5);
        }
        return null;
    }

    /**
     * 查询单个项目进程
     * @param userId
     * @param policyId
     * @return
     */
    public Progress ProgressQuery(String userId,String policyId){
        if(policyId == null || userId == null){
            return null;
        }
        return userSelectDao.progressQuery(userId, policyId);
    }


    /**
     * 申请项目
     * @param userId
     * @param policyId
     * @return
     */
    public boolean applyProgress(String userId,String policyId){
        try{
            userSelectDao.addProgress(userId,policyId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
