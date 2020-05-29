package education.dao;

import education.pojo.Edu_topic;

import java.util.List;

/**
 * 帖子类接口
 */
public interface Edu_topicMapper {

    /**
     * 查询所有帖子
     * @return
     */
    List<Edu_topic> allTopic();
    /**
     * 根据ID查询一个帖子
     */
    Edu_topic findTopicbyid(int topic_id);

    /**
     * 修改帖子
     */
    int updateTopic(Edu_topic edu_topic);

    /**
     * 删除帖子
     */
    int deleteTopic(int topic_id);

    /**
     * 新增帖子
     */
    int addTopic(Edu_topic edu_topic);

}
