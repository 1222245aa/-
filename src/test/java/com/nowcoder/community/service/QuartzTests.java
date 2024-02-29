package com.nowcoder.community.service;

import com.nowcoder.community.CommunityApplication;
import org.junit.jupiter.api.Test;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.quartz.Scheduler;
@SpringBootTest(classes = CommunityApplication.class)
public class QuartzTests {
    @Autowired
    private Scheduler scheduler;
    @Test
    public void deleteJob(){
        try {
            scheduler.deleteJob(new JobKey("alphaJob", "alphaJobGroup") );
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
