package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
//    这里新建一个userservice是因为userid不可能真的显示一个英文，可以带着usesercice一起显示名称，也方面后期redis的缓存
    public User findUserById(int id){
        return userMapper.selectById(id);

    }
}
