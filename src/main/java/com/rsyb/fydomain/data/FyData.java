package com.rsyb.fydomain.data;

import java.util.Date;

public class FyData {
    private String fyDid;
    private String fyUid;
    private String fyName;
    private String fyIdCard;
    private Integer fyAge;
    private String fySex;
    private String fyProvince;
    private String fyCity;
    private String fyCounty;
    private String fyStatus;
    private Date fyTdate;

    @Override
    public String toString() {
        return "FyData{" +
                "fyDid='" + fyDid + '\'' +
                ", fyUid='" + fyUid + '\'' +
                ", fyName='" + fyName + '\'' +
                ", fyIdCard='" + fyIdCard + '\'' +
                ", fyAge=" + fyAge +
                ", fySex='" + fySex + '\'' +
                ", fyProvince='" + fyProvince + '\'' +
                ", fyCity='" + fyCity + '\'' +
                ", fyCounty='" + fyCounty + '\'' +
                ", fyStatus='" + fyStatus + '\'' +
                ", fyTdate=" + fyTdate +
                '}';
    }

    public Integer getFyAge() {
        return fyAge;
    }

    public void setFyAge(Integer fyAge) {
        this.fyAge = fyAge;
    }

    public String getFySex() {
        return fySex;
    }

    public void setFySex(String fySex) {
        this.fySex = fySex;
    }

    public String getFyDid() {
        return fyDid;
    }

    public void setFyDid(String fyDid) {
        this.fyDid = fyDid;
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

    public String getFyIdCard() {
        return fyIdCard;
    }

    public void setFyIdCard(String fyIdCard) {
        this.fyIdCard = fyIdCard;
    }

    public String getFyProvince() {
        return fyProvince;
    }

    public void setFyProvince(String fyProvince) {
        this.fyProvince = fyProvince;
    }

    public String getFyCity() {
        return fyCity;
    }

    public void setFyCity(String fyCity) {
        this.fyCity = fyCity;
    }

    public String getFyCounty() {
        return fyCounty;
    }

    public void setFyCounty(String fyCounty) {
        this.fyCounty = fyCounty;
    }

    public String getFyStatus() {
        return fyStatus;
    }

    public void setFyStatus(String fyStatus) {
        this.fyStatus = fyStatus;
    }

    public Date getFyTdate() {
        return fyTdate;
    }

    public void setFyTdate(Date fyTdate) {
        this.fyTdate = fyTdate;
    }

}
