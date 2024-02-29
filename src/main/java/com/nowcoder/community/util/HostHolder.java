package com.nowcoder.community.util;

import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Component;

/*
* 持有用户信息，用来代替session
* 获得当前用户，ThreadLocal来避免多线程竞争
* */

@Component
public class HostHolder {
   private ThreadLocal<User> users = new ThreadLocal<>();

   public void setUser (User user) {
       users.set(user);
   }
   public User getUser () {
       return users.get();
   }

   public void clear () {
     users.remove();
   }

}
