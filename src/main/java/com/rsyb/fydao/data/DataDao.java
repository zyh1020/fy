package com.rsyb.fydao.data;

import com.rsyb.fydomain.data.FyConditionData;
import com.rsyb.fydomain.data.FyData;
import com.rsyb.fydomain.data.analysis.ContionSort;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DataDao {
    public void insertOneData(FyData fyData);
    public void updateOneData(FyData fyData);
    public List<FyData> conditionSelectDatas(FyConditionData fyConditionData);
    public void deleteOneData(String fyUid,String fyDid); //删除
    public FyData selectOneData(String fyDid);//查询
    public Integer countSortFyData(ContionSort contionSort);
    public Integer testDate(Date fyTdate);
    public Integer conuntDate(ContionSort contionSort);
}
