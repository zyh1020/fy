package com.rsyb.fydomain.user;

import com.rsyb.fydomain.user2.Fy2User;

public class FyUser {
    private String fyUid;
    private String fyName;
    private String fyPassword;
    private String fyPhone;
    private String fyCode;
    private Fy2User fy2User;

    @Override
    public String toString() {
        return "FyUser{" +
                "fyUid='" + fyUid + '\'' +
                ", fyName='" + fyName + '\'' +
                ", fyPassword='" + fyPassword + '\'' +
                ", fyPhone='" + fyPhone + '\'' +
                ", fyCode='" + fyCode + '\'' +
                ", fy2User=" + fy2User +
                '}';
    }

    public Fy2User getFy2User() {
        return fy2User;
    }
    public void setFy2User(Fy2User fy2User) {
        this.fy2User = fy2User;
    }


    public String getFyPhone() {
        return fyPhone;
    }

    public void setFyPhone(String fyPhone) {
        this.fyPhone = fyPhone;
    }

    public String getFyUid() {
        return fyUid;
    }

    public void setFyUid(String fyUid) {
        this.fyUid = fyUid;
    }

    public String getFyName() {
        return fyName;
    }

    public void setFyName(String fyName) {
        this.fyName = fyName;
    }

    public String getFyPassword() {
        return fyPassword;
    }

    public void setFyPassword(String fyPassword) {
        this.fyPassword = fyPassword;
    }

    public String getFyCode() {
        return fyCode;
    }

    public void setFyCode(String fyCode) {
        this.fyCode = fyCode;
    }

}
