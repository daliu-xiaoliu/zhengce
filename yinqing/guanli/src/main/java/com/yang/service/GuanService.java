package com.yang.service;


import com.yang.dao.AdminDao;
import com.yang.dao.PolicyAdDao;
import com.yang.entities.PolicyInfo;
import com.yang.entities.Progress;
import com.yang.util.GetPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class GuanService {

    @Autowired
    private PolicyAdDao policyAdDao;

    @Autowired
    private AdminDao adminDao;



    private void addPolicy(List<PolicyInfo> policyInfos){
        Iterator<PolicyInfo> infoIterator=policyInfos.iterator();
        while (infoIterator.hasNext()){
            PolicyInfo policyInfo=infoIterator.next();
            policyAdDao.addPolicy(policyInfo.getPolicy());
            policyAdDao.addPolicyInfo(policyInfo);
        }
    }

    public int  add(String name){
        List<PolicyInfo> policy=null;
        try {
             policy = GetPolicy.getPolicy(name);
            addPolicy(policy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return policy.size();
    }

    public boolean updateState(String userId,String state,String policyId){

        System.out.println(userId+"  "+policyId);
        int b = policyAdDao.updatePolicyState(userId,state, policyId);
        System.out.println(b);
        if(b==1){
            return true;
        }
        return false;

    }

    public List<Progress> findAll(int page){
        return policyAdDao.findAllProgress(page*5);
    }


}
