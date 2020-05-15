package com.rsyb.fyservice.data;

import com.rsyb.fydao.data.DataDao;
import com.rsyb.fydomain.data.FyConditionData;
import com.rsyb.fydomain.data.FyData;
import com.rsyb.fydomain.data.analysis.ContionSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DataServerImpl implements DataServer {
    @Autowired
    private DataDao dataDao;

    public String addOneData(FyData fyData) {
        dataDao.insertOneData(fyData);
        return null;
    }

    public String updateOneData(FyData fyData) {
        dataDao.updateOneData(fyData);
        return null;
    }
    /* 条件查询 */
    public List<FyData> conditionFIndDatas(FyConditionData fyConditionData) {
        return dataDao.conditionSelectDatas(fyConditionData);
    }

    public String deleteOneData(String fyUid, String fyDid) {
        dataDao.deleteOneData(fyUid,fyDid);
        return null ;
    }

    public FyData findOneData(String fyDid) {
        return dataDao.selectOneData(fyDid);
    }

    public Integer conuntFySort(ContionSort contionSort) {
        return dataDao.countSortFyData(contionSort);
    }

    public Integer testDate(Date fyTdate) {
        return dataDao.testDate(fyTdate);
    }

    public Integer conuntDate(ContionSort contionSort) {
        return dataDao.conuntDate(contionSort);
    }


}
