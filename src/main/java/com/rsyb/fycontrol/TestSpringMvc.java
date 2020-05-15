package com.rsyb.fycontrol;

import com.rsyb.fydomain.TestDomain;
import com.rsyb.fyservice.TestServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/TestSpringMvc")
public class TestSpringMvc {
    @Autowired
    private TestServer testServer;
    @RequestMapping("/test")
    public String test(){
        System.out.println("测试成功！");
        System.out.println(testServer);
        List<TestDomain> testDomains = testServer.finadAll();

        for (TestDomain testDomain : testDomains) {
            System.out.println(testDomain);
        }
        return "jsps/testSpringMvc";
    }
}
