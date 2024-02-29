package com.nowcoder.community.service;

import com.nowcoder.community.CommunityApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CommunityApplication.class)
public class LoggerTests {
//    实例化日志
    private  static final Logger logger = LoggerFactory.getLogger(LoggerTests.class);
    @Test
    public void testLogger(){
        System.out.println(logger.getName());
        String version1 = SpringBootVersion.getVersion();
        System.out.println(version1);

        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");

    }
}
