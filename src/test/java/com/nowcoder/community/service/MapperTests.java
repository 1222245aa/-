package com.nowcoder.community.service;

import com.nowcoder.community.CommunityApplication;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void save(){
        User user = userMapper.selectById(101);
        System.out.println(user);

    }
    @Test
    public void testSelectPosts(){
        List<DiscussPost> dis = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for(DiscussPost discussPost:dis){
            System.out.println(discussPost);
        }
    int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }

}
