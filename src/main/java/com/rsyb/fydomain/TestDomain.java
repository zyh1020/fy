package com.rsyb.fydomain;

public class TestDomain {
    private String testName;
    private Integer testAge;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getTestAge() {
        return testAge;
    }

    public void setTestAge(Integer testAge) {
        this.testAge = testAge;
    }

    @Override
    public String toString() {
        return "TestDomain{" +
                "testName='" + testName + '\'' +
                ", testAge=" + testAge +
                '}';
    }
}
