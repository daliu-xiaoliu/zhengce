package com.yang.entities;

import java.io.Serializable;

public class PolicyInfo implements Serializable {

    private Policy policy;
    private String policyRoute;
    private String policySimpleInfo;

    public PolicyInfo() {
    }

    public PolicyInfo(Policy policy, String policyRoute, String policySimpleInfo) {
        this.policy = policy;
        this.policyRoute = policyRoute;
        this.policySimpleInfo = policySimpleInfo;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public String getPolicyRoute() {
        return policyRoute;
    }

    public void setPolicyRoute(String policyRoute) {
        this.policyRoute = policyRoute;
    }

    public String getPolicySimpleInfo() {
        return policySimpleInfo;
    }

    public void setPolicySimpleInfo(String policySimpleInfo) {
        this.policySimpleInfo = policySimpleInfo;
    }

    @Override
    public String toString() {
        return "PolicyInfo{" +
                "policy=" + policy +
                ", policyRoute='" + policyRoute + '\'' +
                ", policySimpleInfo='" + policySimpleInfo + '\'' +
                '}';
    }
}
