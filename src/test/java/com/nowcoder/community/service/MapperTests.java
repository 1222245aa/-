package com.nowcoder.community.service;

import com.nowcoder.community.CommunityApplication;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.MessageMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.Message;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void save(){
        User user = userMapper.selectById(101);
        System.out.println(user);

    }
    @Test
    public void testSelectPosts(){
        List<DiscussPost> dis = discussPostMapper.selectDiscussPosts(0, 0, 10,0);
        for(DiscussPost discussPost:dis){
            System.out.println(discussPost);
        }
    int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }
//因为写sql语句报错也不会提示，所以我们需要测试下
//    写sql语句最好要测试
    @Test
    public void testInsertTicket () {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000*60*10));
        loginTicketMapper.insertLoginTicket(loginTicket);

    }

    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abc", 1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);


    }

    @Test
    public void testss(){
        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(101);
        discussPost.setStatus(1);
        discussPost.setCommentCount(22);
        discussPost.setContent("hehe");
        System.out.println(discussPostMapper.insertDiscussPost(discussPost));

    }

    @Test
    public void ssss(){
        List<Message> messages = messageMapper.selectConversations(111, 0, 10);
        for (Message a : messages) {
            System.out.println(a);
        }
        int count = messageMapper.selectConversationCount(111);
        System.out.println(count);
         messages = messageMapper.selectLetters("111_112", 0, 10);
        for (Message a : messages) {
            System.out.println(a);
        }
        System.out.println(messageMapper.selectLettersCount("111_112"));
        System.out.println(messageMapper.selectLetterUnreadCount(131, "111_131"));
    }


}
