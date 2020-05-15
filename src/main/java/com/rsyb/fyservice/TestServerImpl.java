package com.rsyb.fyservice;

import com.rsyb.fydao.TestDao;
import com.rsyb.fydomain.TestDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServerImpl implements TestServer {
    @Autowired
    private TestDao testDao;
    public List<TestDomain> finadAll() {
        System.out.println("server测试成功");
        System.out.println(testDao);
        return testDao.findAllTestDomian();
    }
}
