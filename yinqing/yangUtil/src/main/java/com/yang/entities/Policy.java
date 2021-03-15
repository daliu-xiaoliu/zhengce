package com.yang.entities;

import java.io.Serializable;

//政策信息
public class Policy implements Serializable {

    private String num;
    private String policyId;
    private String policyName;
    private String policyCity;
    private String policyTarget;//目标人群
    private int policyHot;//政策热度

    public Policy() {
    }

    public Policy(String policyId, String policyName, String policyCity, String policyTarget, int policyHot) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.policyCity = policyCity;
        this.policyTarget = policyTarget;
        this.policyHot = policyHot;
    }

    public Policy(String num, String policyId, String policyName, String policyCity, String policyTarget, int policyHot) {
        this.num = num;
        this.policyId = policyId;
        this.policyName = policyName;
        this.policyCity = policyCity;
        this.policyTarget = policyTarget;
        this.policyHot = policyHot;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyCity() {
        return policyCity;
    }

    public void setPolicyCity(String policyCity) {
        this.policyCity = policyCity;
    }

    public String getPolicyTarget() {
        return policyTarget;
    }

    public void setPolicyTarget(String policyTarget) {
        this.policyTarget = policyTarget;
    }

    public int getPolicyHot() {
        return policyHot;
    }

    public void setPolicyHot(int policyHot) {
        this.policyHot = policyHot;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "policyId='" + policyId + '\'' +
                ", policyName='" + policyName + '\'' +
                ", policyCity='" + policyCity + '\'' +
                ", policyTarget='" + policyTarget + '\'' +
                ", policyHot=" + policyHot +
                '}';
    }
}
