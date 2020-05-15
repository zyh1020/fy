package com.rsyb.fydomain.article;


import java.util.Date;

public class FyArticle {
    private String fyTid;
    private String fyUid;
    private String fyTitle;
    private String fyAbstract;
    private String fyContent;
    private String fySort;
    private String fyTdate;
    private Date fyGdate;

    @Override
    public String toString() {
        return "FyArticle{" +
                "fyTid='" + fyTid + '\'' +
                ", fyUid='" + fyUid + '\'' +
                ", fyTitle='" + fyTitle + '\'' +
                ", fyAbstract='" + fyAbstract + '\'' +
                ", fyContent='" + fyContent + '\'' +
                ", fySort='" + fySort + '\'' +
                ", fyTdate='" + fyTdate + '\'' +
                ", fyGdate=" + fyGdate +
                '}';
    }

    public Date getFyGdate() {
        return fyGdate;
    }

    public void setFyGdate(Date fyGdate) {
        this.fyGdate = fyGdate;
    }

    public String getFyUid() {
        return fyUid;
    }

    public void setFyUid(String fyUid) {
        this.fyUid = fyUid;
    }

    public String getFyTid() {
        return fyTid;
    }

    public void setFyTid(String fyTid) {
        this.fyTid = fyTid;
    }

    public String getFyTitle() {
        return fyTitle;
    }

    public void setFyTitle(String fyTitle) {
        this.fyTitle = fyTitle;
    }

    public String getFyAbstract() {
        return fyAbstract;
    }

    public void setFyAbstract(String fyAbstract) {
        this.fyAbstract = fyAbstract;
    }

    public String getFyContent() {
        return fyContent;
    }

    public void setFyContent(String fyContent) {
        this.fyContent = fyContent;
    }

    public String getFySort() {
        return fySort;
    }

    public void setFySort(String fySort) {
        this.fySort = fySort;
    }

    public String getFyTdate() {
        return fyTdate;
    }

    public void setFyTdate(String fyTdate) {
        this.fyTdate = fyTdate;
    }
}
