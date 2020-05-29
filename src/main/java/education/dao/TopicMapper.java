package education.dao;

import education.pojo.*;

import java.util.List;

public interface TopicMapper {
    /**
     * 查询所有帖子
     * @return
     */
    List<Topic> topicInfo();

    /**
     * 查询所有板块
     * @return
     */
    List<Block> blockInfo();

    /**
     * 查询所有用户
     * @return
     */

    List<User> userInfo();
/**
 *查询所有课程
 */
    List<Course> courseInfo();

    /**
     * 增加帖子
     * @param edu_topic
     * @return
     */

    int addTopics(Edu_topic edu_topic);
}
