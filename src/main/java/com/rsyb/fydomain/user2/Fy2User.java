package com.rsyb.fydomain.user2;

public class Fy2User {

    private String fy2Uid;
    private String fyUidy; // 外键
    private String fy2Name;
    private String fy2Sex;
    private String fy2Age;
    private String fy2Img;
    private String fy2Qm;

    @Override
    public String toString() {
        return "Fy2User{" +
                "fy2Uid='" + fy2Uid + '\'' +
                ", fyUidy='" + fyUidy + '\'' +
                ", fy2Name='" + fy2Name + '\'' +
                ", fy2Sex='" + fy2Sex + '\'' +
                ", fy2Age='" + fy2Age + '\'' +
                ", fy2Img='" + fy2Img + '\'' +
                ", fy2Qm='" + fy2Qm + '\'' +
                '}';
    }

    public String getFyUidy() {
        return fyUidy;
    }

    public void setFyUidy(String fyUidy) {
        this.fyUidy = fyUidy;
    }

    public String getFy2Uid() {
        return fy2Uid;
    }

    public void setFy2Uid(String fy2Uid) {
        this.fy2Uid = fy2Uid;
    }

    public String getFy2Name() {
        return fy2Name;
    }

    public void setFy2Name(String fy2Name) {
        this.fy2Name = fy2Name;
    }

    public String getFy2Sex() {
        return fy2Sex;
    }

    public void setFy2Sex(String fy2Sex) {
        this.fy2Sex = fy2Sex;
    }

    public String getFy2Age() {
        return fy2Age;
    }

    public void setFy2Age(String fy2Age) {
        this.fy2Age = fy2Age;
    }

    public String getFy2Img() {
        return fy2Img;
    }

    public void setFy2Img(String fy2Img) {
        this.fy2Img = fy2Img;
    }

    public String getFy2Qm() {
        return fy2Qm;
    }

    public void setFy2Qm(String fy2Qm) {
        this.fy2Qm = fy2Qm;
    }
}
