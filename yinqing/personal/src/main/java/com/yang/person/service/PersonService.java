package com.yang.person.service;

import com.yang.entities.RelateUser;
import com.yang.entities.User;
import com.yang.entities.UserInfo;
import com.yang.person.dao.UserInfoDao;
import com.yang.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Service
public class PersonService {


    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${image.filePath}")
    private String filePath;

    @Value("${image.accessPath}")
    private String accessPath;


    public RelateUser relateUser(String id){
        String msg  = (String) redisUtil.get(id);
        if(null == msg){
            System.out.println(msg);
            return null;
        }
        RelateUser relateById=null;
        User user=null;
        if(null!=id){
            relateById= userInfoDao.findRelateById(id);
            if(relateById != null){
                return relateById;
            }
            user = findUser(id);
            System.out.println(user);
        }
        return new RelateUser(user,new UserInfo(user.getUserId(),null,null,null,null,null));
    }

    public UserInfo findUserInfo(String id){
        if(null!=id){
            return userInfoDao.findById(id);
        }
        return null;
    }


    public User findUser(String id){
        return userInfoDao.findUser(id);
    }

    /**
     * 一并上传用户的信息
     * @param multipartFile
     * @param userInfo
     * @return
     */
    public String uploadImage(MultipartFile multipartFile, UserInfo userInfo){

        try{
            File file=new File(filePath+userInfo.getUserNum()+".jpg");
            String path=accessPath+userInfo.getUserNum()+".jpg";
            if(null ==path){
                return "上传失败";
            }
            userInfo.setUserPhoto(path);
            addInfo(userInfo);
            multipartFile.transferTo(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "成功";
    }


    public String addInfo(UserInfo userInfo){
        User user=userInfoDao.findUser(userInfo.getUserNum());
        if(null !=user){
            userInfoDao.addInfo(userInfo);
            return "添加成功";
        }
            return "没有此用户";
    }

    /**
     * 验证用户账号密码
     * 开启事务支持
     * @param id
     * @return
     */
    @Transactional()
    public boolean delete(String id,String password){
        if(isSelf(id,password)){
            userInfoDao.deleteUser(id);
            return   userInfoDao.deleteInfo(id);
        }
        return false;
    }

    public boolean isSelf(String id,String password){
        User user = userInfoDao.findUser(id);
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    /**
     * 补充完善用户信息
     * @param id
     * @param age
     * @param sex
     * @return
     */
    public int addMore(String id,String age,String sex){
        if(null ==id){
            return -1;
        }
       return userInfoDao.addMoreInfo(id,age,sex);
    }

    /**
     * 判断用户是否存在且登录
     * 向redis服务器发送请求，存在返回true
     * @param userId
     * @return
     */
    public boolean isLogin(String userId){
        if(null != userInfoDao.findUser(userId)){
            if(null != redisUtil.get(userId)){
                return true;
            }
        }
        return false;
    }


}
