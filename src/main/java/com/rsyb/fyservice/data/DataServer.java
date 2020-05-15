package com.rsyb.fyservice.data;

import com.rsyb.fydomain.data.FyConditionData;
import com.rsyb.fydomain.data.FyData;
import com.rsyb.fydomain.data.analysis.ContionSort;

import java.util.Date;
import java.util.List;

public interface DataServer {
    public String addOneData(FyData fyData);
    public String updateOneData(FyData fyData);
    public List<FyData> conditionFIndDatas(FyConditionData fyConditionData);
    public String deleteOneData(String fyUid,String fyDid);
    public FyData findOneData(String fyDid);
    public Integer conuntFySort(ContionSort contionSort);
    public Integer testDate(Date fyTdate);
    public Integer conuntDate(ContionSort contionSort);
}
