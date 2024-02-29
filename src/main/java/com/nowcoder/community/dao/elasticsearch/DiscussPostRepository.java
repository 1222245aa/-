package com.nowcoder.community.dao.elasticsearch;

import com.nowcoder.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
//第一个是所对应的实体类，第二个是实体类主键的类型
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost, Integer> {
}
