package com.rsyb.fydomain.data.analysis;

import java.util.List;

public class AnanlysisLine {
    private String name;
    private List<Integer> data;
    private String type = "line";
    private String stack = "总量";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public AnanlysisLine(){}
    public AnanlysisLine(String name,List<Integer> data){
            this.name = name;
            this.data = data;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
