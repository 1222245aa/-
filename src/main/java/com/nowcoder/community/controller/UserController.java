package com.nowcoder.community.controller;

import com.nowcoder.community.controller.annotation.LoginRequired;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.FollowService;
import com.nowcoder.community.service.LikeService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/user")
public class UserController implements CommunityConstant {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//    文件上传的路径
    @Value("${community.path.update}")
    private String updatePath;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private LikeService likeService;

    @Autowired
    private FollowService followService;

    @LoginRequired
    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String getSettingPage () {
        return "/site/setting";
    }
//MVC有专门的工具去上传图片，就是MultipartFile
    @LoginRequired
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadHeader(MultipartFile headerImage, Model model) {
        if (headerImage == null) {
            model.addAttribute("error", "您还没有选择图片");
            return "/site/setting";
        }
//        获得原始文件名
        String filename = headerImage.getOriginalFilename();
//        截取文件格式
        String suffix = filename.substring(filename.lastIndexOf("."));
        if (StringUtils.isBlank(suffix)) {
            model.addAttribute("error", "文件的格式不正确");
            return "/site/setting";
        }
//        生成随机文件名，防止存储的图片的名字都一样
        filename = CommunityUtil.generateUUID() + suffix;
//        确定存储路径
        File dest = new File(updatePath + "/" + filename);
        try {
//            用这个将rheade里面的数据写到文件中
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败 " + e.getMessage());
            throw new RuntimeException("上传文件失败，服务器放生异常", e);
        }

//        更新当前用户的头像路径（需要使用web访问路径）
        User user = hostHolder.getUser();
//        http://localhost:8080/community/user/header/xxx.png
        String headerUrl = domain + contextPath + "/user/header" + filename;
        userService.updateHeader(user.getId(), headerUrl);

        return "redirect:/index";

    }
//    获取头像
    @RequestMapping(path = "/header/{fileName}", method = RequestMethod.GET)
    public void getHeader(@PathVariable("fileName") String fileName, HttpServletResponse response) {
//        服务器存放的路径名
        fileName = updatePath + "/" + fileName;
//        获取文件的后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
//        响应图片，设置图片类型
        response.setContentType("image/" + suffix);
//        在try边加上一个小括号，表示对里面的代码自动执行finally，close函数
        try (
                OutputStream os = response.getOutputStream();
              FileInputStream fis = new FileInputStream(fileName);
              )
        {
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = fis.read()) != -1) {
                os.write(buffer, 0 , b);
            }
        } catch (IOException e) {
            logger.error("读取头像失败" + e.getMessage());
        }


    }
    // 个人主页
    @RequestMapping(path = "/profile/{userId}", method = RequestMethod.GET)
    public String getProfilePage(@PathVariable("userId") int userId, Model model) {
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new RuntimeException("该用户不存在!");
        }

        // 用户
        model.addAttribute("user", user);
        // 点赞数量
        int likeCount = likeService.findUserLikeCount(userId);
        model.addAttribute("likeCount", likeCount);
        //关注数量
        long followeeCount = followService.findFolloweeCount(userId, ENTITY_TYPE_USER);
        model.addAttribute("followeeCount", followeeCount);
        //粉丝数量
        long followerCount = followService.findFollowerCount(ENTITY_TYPE_USER, userId);
        model.addAttribute("followerCount", followerCount);
        //是否已关注
         boolean hasFollowed = false;
         if (hostHolder.getUser() != null) {
             hasFollowed = followService.hasFollowed(hostHolder.getUser().getId(), ENTITY_TYPE_USER, userId);
         }

         model.addAttribute("hasFollowed", hasFollowed);



        return "/site/profile";
    }



}
