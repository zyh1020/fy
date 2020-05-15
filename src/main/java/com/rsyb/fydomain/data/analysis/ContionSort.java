package com.rsyb.fydomain.data.analysis;

import java.util.Date;

public class ContionSort {

    private String fyCity;
    private String fySort;
    private Date fyTdate;
    private Integer fyNumber;

    @Override
    public String toString() {
        return "ContionSort{" +
                "fyCity='" + fyCity + '\'' +
                ", fySort='" + fySort + '\'' +
                ", fyTdate=" + fyTdate +
                ", fyNumber=" + fyNumber +
                '}';
    }

    public Integer getFyNumber() {
        return fyNumber;
    }

    public void setFyNumber(Integer fyNumber) {
        this.fyNumber = fyNumber;
    }

    public String getFyCity() {
        return fyCity;
    }

    public void setFyCity(String fyCity) {
        this.fyCity = fyCity;
    }

    public String getFySort() {
        return fySort;
    }

    public void setFySort(String fySort) {
        this.fySort = fySort;
    }

    public Date getFyTdate() {
        return fyTdate;
    }

    public void setFyTdate(Date fyTdate) {
        this.fyTdate = fyTdate;
    }
}
