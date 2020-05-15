package com.rsyb.fydao;
import com.rsyb.fydomain.TestDomain;
import java.util.List;

public interface TestDao {
    List<TestDomain> findAllTestDomian();
}
