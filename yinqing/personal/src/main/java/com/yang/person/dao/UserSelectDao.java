package com.yang.person.dao;

import com.yang.entities.Policy;
import com.yang.entities.PolicyInfo;
import com.yang.entities.Progress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSelectDao {


    /**
     * 查询用户目前某个项目的申请进度
     * @param
     * @param policyId
     * @return
     */
    public Progress progressQuery(String userId, String policyId);

    /**
     * 查询用户所有目前正在进行的订单信息
     * @param userId
     * @return
     */
    public List<Progress> UserProgress(@Param("userId") String userId, @Param("page") int page);

    /**
     * 查询用户已经完成的订单
     * @param userId
     * @return
     */
    public List<Progress> UserPassProgress(@Param("userId")String userId,@Param("page") int page);


    public List<Progress> fitProgress(@Param("userId") String userId,@Param("page") int page);

    public void addProgress(@Param("userId") String userId,@Param("policyId") String policyId);
}
