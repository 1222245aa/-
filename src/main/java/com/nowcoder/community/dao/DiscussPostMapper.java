package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
//    为什么要传入userID因为，最后要开发个人主页，当userID为0时不把他拼到sql语句中，当不为0时拼到sql语句中就开始查询个人主页，写动态sql，
//    offset每页开始的行号,limit每页最多有多少条数据，为后面的分页做追呗
    List<DiscussPost> selectDiscussPosts(int userId,int offset, int limit);
//    @Param给数据起别名
//    如果只有一个参数，并且在<if>里面使用，就必须要别名
//    查询有多少条数据
    int selectDiscussPostRows(@Param("userId") int userId);


}
