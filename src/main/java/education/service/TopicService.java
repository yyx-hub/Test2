package education.service;

import education.dao.TopicMapper;
import education.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 帖子管理类
 * @author rlh
 */

@Service
public class TopicService {
    
    @Autowired
    private TopicMapper topicMapper;

    /**
     * 查询所有帖子
     * @return
     */
    public List<Topic> topicInfo() {
        return topicMapper.topicInfo();
    }

    /**
     *查询所有板块
     * @return
     */
    public List<Block> blockInfo() {

        return topicMapper.blockInfo();
    }
/**
 * 查询所有用户
 */
    public List<User> userInfo() {
        return topicMapper.userInfo();
    }

    /**
     * 查询所有课程
     * @return
     */
    public List<Course> courseInfo() {
        return topicMapper.courseInfo();
    }

    public int addTopics(Edu_topic edu_topic) {

        return topicMapper.addTopics(edu_topic);
    }
}
