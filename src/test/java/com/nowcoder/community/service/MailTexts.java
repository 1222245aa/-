package com.nowcoder.community.service;

import com.nowcoder.community.CommunityApplication;
import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

@SpringBootTest(classes = CommunityApplication.class)
public class MailTexts {
    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;
    @Test
    public void testTextMail(){
        mailClient.sendMail("3364340194@qq.com","Test","Welcome");

    }
    @Test
    public void htmlTestMail(){
        Context context = new Context();
        context.setVariable("username","haodaer");
        String content = templateEngine.process("/mail/demo" , context);
        System.out.println(content);
        mailClient.sendMail("2051214284@qq.com","HTML",content);


    }

}
