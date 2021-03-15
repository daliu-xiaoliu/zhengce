package com.yang.entities;

public class Progress {

    private String userId;
    private String userName;
    private String policyId;
    private String policyName;
    private String policyState;

    public Progress(String userId, String userName, String policyId, String policyName, String policyState) {
        this.userId = userId;
        this.userName = userName;
        this.policyId = policyId;
        this.policyName = policyName;
        this.policyState = policyState;
    }

    public Progress() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPolicyState() {
        return policyState;
    }

    public void setPolicyState(String policyState) {
        this.policyState = policyState;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", policyId='" + policyId + '\'' +
                ", policyName='" + policyName + '\'' +
                ", policyState='" + policyState + '\'' +
                '}';
    }
}
