package education.service;

import education.dao.Edu_topicMapper;
import education.pojo.Edu_topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service层帖子实现类
 */

@Service
public class Edu_topicServiceImpl implements Edu_topicService{
    @Autowired
    private Edu_topicMapper edu_topicMapper;

    /**
     * 查询所有帖子
     * @return
     */
    public List<Edu_topic> allTopic() {
        return edu_topicMapper.allTopic();
    }

    /**
     * 根据ID查询一个帖子
     */
    public Edu_topic findTopicbyid(int topic_id) {
        return edu_topicMapper.findTopicbyid(topic_id);
    }

    /**
     * 修改帖子
     */
    public int updateTopic(Edu_topic edu_topic) {
        return edu_topicMapper.updateTopic(edu_topic);
    }

    /**
     * 删除帖子
     */
    public int deleteTopic(int topic_id) {
        return edu_topicMapper.deleteTopic(topic_id);
    }

    /**
     * 新增帖子
     */
    public int addTopic(Edu_topic edu_topic) {
        return edu_topicMapper.addTopic(edu_topic);
    }
}
