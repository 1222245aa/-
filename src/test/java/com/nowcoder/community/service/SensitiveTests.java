package com.nowcoder.community.service;

import com.nowcoder.community.CommunityApplication;
import com.nowcoder.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CommunityApplication.class)
public class SensitiveTests {
    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Test
    public void Test (){
        String name = "我要赌博，我要开票";
        System.out.println(sensitiveFilter.filter(name));

    }
}
