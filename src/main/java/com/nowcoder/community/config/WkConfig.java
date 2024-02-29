package com.nowcoder.community.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
public class WkConfig {


        private static final Logger logger = LoggerFactory.getLogger(WkConfig.class);
        @Value("${wk.image.storage}")
        private String wkImageStorage;

        @PostConstruct
        public void init() {
            //    创造wk图片目录
            File file = new File(wkImageStorage);
            if (!file.exists()) {
                //创建目录
                file.mkdir();
                logger.info("创造wk图片目录:" + wkImageStorage);
            }
        }


    }