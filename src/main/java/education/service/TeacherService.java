package education.service;


import education.dao.TagMapper;
import education.dao.TeachersMapper;
import education.pojo.Tag;
import education.pojo.Teachers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Created
 * @ Description:
 * @ Author:  倪绍帅
 */
@Service
public class TeacherService {
    @Autowired
    private TeachersMapper teachersMapper;

    /**
     * 查询标签ID和标签名
     */
    public List<Tag> selectTeachersName(Teachers teachers) {
        return teachersMapper.selectTeacherName(teachers);
    }

}
