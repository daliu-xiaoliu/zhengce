package com.yang.dao;

public interface AdminDao {

    public boolean deleteUser(String userId);

    public boolean updateEmail(String userId,String email);


}
