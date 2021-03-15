package com.yang.dao;

import com.yang.entities.Policy;
import com.yang.entities.PolicyInfo;
import com.yang.entities.Progress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PolicyAdDao {

    public void addPolicy(@Param("policy") Policy policy);

    public void addPolicyInfo(@Param("policyInfo") PolicyInfo policyInfo);

    public List<Progress> findAllProgress(int page);

    public int  updatePolicyState(@Param("userId") String userId,@Param("state") String state,@Param("policyId") String policyId);
}
