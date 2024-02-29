项目简介： 该论坛是一个互动交流平台，实现了注册登录、发帖评论、回复点赞、消息提醒、内容搜索和网站数据统

计的功能，并将用户头像等信息存于七牛云。

技术栈：Spring Boot + MyBatis + MySQL + Redis + Kafka + Elasticsearch

工作内容：

· 使用 Redis 存储登录 ticket 和验证码，解决分布式 session 问题；

· 使用 Redis 的 set 实现点赞，zset 实现关注，HyperLogLog 统计 UV，Bitmap 统计 DAU；

· 使用 Kafka 处理发送评论、点赞和关注等系统通知，起到解耦和异步调用的作用；

· 使用 Elasticsearch 对帖子搜索功能进行重构，通过 IK 中文分词器增加增量索引和全局索引，实现搜索关键词

高亮显示等功能；

· 对热帖排行模块，使用分布式缓存 Redis 和本地缓存 Caffeine 作为多级缓存，将 QPS 提升了 20 倍（10-200），

大大提升了网站访问速度，并使用 Quartz 定时更新热帖排行榜。
