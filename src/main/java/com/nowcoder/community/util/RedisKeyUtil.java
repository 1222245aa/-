package com.nowcoder.community.util;

//生成redis的Key
//生成key的规则，找出某个用户还是实体，后面是所需要添加的东西
public class RedisKeyUtil {
    private static final String STlIT = ":";
//   实体的前缀
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    private static final String PREFIX_USER_LIKE = "like:user";
//    被关注者的前缀
    private static final String PREFIX_FILLOWEE = "followee";
//    粉丝的前缀
   private static final String PREFIX_FILLOWER = "follower";
//   验证码的前缀
    private static final String PREFIX_KAPTCHA = "kaptcha";
//    登录凭证
    private static final String PREFIX_TICKET = "ticket";
    //用户
    private static final String PREFIX_USER = "user";

//    UV 客流量
    private static final String PREFIX_UV = "uv";

//    DAU 日活跃用户
    private static final String PREFIX_DAU = "dau";
//    帖子分数
    private static final String PREFIX_POST = "post";

//    获得某个实体的赞
//    like：entity：entityType：entityId -> set(userId),
    public static String getEntityLikeKey(int entityType, int entityId) {
        return PREFIX_ENTITY_LIKE + STlIT + entityType + entityId;
    }
//    某个用户的赞
//    like:user:userId -> int
    public static String getUserLikeKey(int userId) {
        return PREFIX_USER_LIKE + STlIT + userId;
    }
//     某个用户关注的实体
//    followee:userId:entityType->zset(entityId,now)  zset(entityId,now)表示entityId,按照时间来排序
    public static String getFolloweeKey(int userId, int entityType) {
        return PREFIX_FILLOWEE + STlIT + userId + STlIT + entityType;
    }


//    某个实体拥有的粉丝
//    follower:entityType:entityId ->zset(userId,now)
    public static String getFollowerKey(int entityType, int entityId) {
        return PREFIX_FILLOWER + STlIT + entityType + STlIT + entityId;

    }
    //登录验证码
    public static String getKaptchaKey(String owner) {
        return PREFIX_KAPTCHA + STlIT + owner;
    }

//    登录凭证
    public static String getTicketKey(String ticket) {
        return PREFIX_TICKET + STlIT + ticket;
    }
//    用户
    public static String getUserKey(int userId) {
        return PREFIX_USER + STlIT + userId;
    }
//    每日UV
    public static String getUVKey(String date) {return PREFIX_UV + STlIT + date;}

//    区间UV
    public static String getUVKey(String startDate, String endDate) {return PREFIX_DAU + STlIT + startDate + STlIT + endDate;}

//    每日活跃用户
    public static String getDAUKey(String date) {return PREFIX_DAU + STlIT + date;}
//    区间活跃用户
    public static String getDAUKey(String startDate, String endDate) {return PREFIX_DAU + STlIT + startDate + STlIT + endDate;}
//  帖子分数，接着只需要对变化key加入即可，即用户在帖子下面品论，点赞和发帖
    public static String getPostScoreKey(){
        return PREFIX_POST + STlIT + "score";
    }
}
