package com.yang.person.dao;

import com.yang.entities.RelateUser;
import com.yang.entities.User;
import com.yang.entities.UserInfo;
import org.apache.ibatis.annotations.Param;


public interface UserInfoDao {

    /**
     *通过ID寻找用户查找其信息
     * @return
     */
    public UserInfo findById(String id);


    public User findUser(String id);
    /**
     * 一次性查找用户的所有信息保存到新的bean中
     * @param id
     * @return
     */
    public RelateUser findRelateById(String id);

    /**
     * 通过id补充完善用户的信息
     * @param
     */
    public void addInfo(@Param("userinfo") UserInfo info);


    /**
     * 通过id删除用户信息，需要先删除简短信息
     * @param id
     */
    public boolean deleteInfo(String id);


    public boolean deleteUser(String id);

    public int addMoreInfo(String id,String age,String sex);


}
