package com.yang.zhengce.dao;

import com.yang.entities.Policy;
import com.yang.entities.PolicyInfo;
import com.yang.entities.User;
import com.yang.entities.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PolicyInfoDao {

    /**
     * 查找政策执行地的集合
     * @param city 城市编号
     * @return
     */
    public List<PolicyInfo> findPolicyByCity(@Param("city") String city,@Param("page") int page , @Param("size") int size);

    /**
     * 通过热度查找政策集合
     * @param min 最低热度
     * @param max 最高热度
     * @return
     */
    public List<PolicyInfo> findPolicyByHot(int min,int max,@Param("page") int page, @Param("size") int size);

    /**
     * 通过政策名来实现模糊查询
     * @param name
     * @return
     */
    public List<PolicyInfo> findFuzzy(@Param("name") String name,@Param("page") int page,@Param("size") int size );


    /**
     * 通过适宜的人群来查找政策
     * @param people
     * @param size
     * @param page
     * @return
     */
    public List<PolicyInfo> findFitPeople(@Param("target") String people,@Param("page") int page, @Param("size") int size);

    /**
     * 查找到政策之后观查看详细内容
     * @param id
     * @return
     */
    public Policy findPolicy(String id);

    public List<PolicyInfo> findFuzzyCity(@Param("city") String city, @Param("page") int page,@Param("size") int size );

    public UserInfo findUser(String userId);
}
