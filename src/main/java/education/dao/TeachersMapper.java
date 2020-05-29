package education.dao;

import education.pojo.Tag;
import education.pojo.Teachers;

import java.util.List;

public interface TeachersMapper {

    /**
     * 查询所有标签ID和标签名
     */
    List<Tag> selectTeacherName(Teachers record);

    int deleteByPrimaryKey(Integer teacherId);

    int insert(Teachers record);

    int insertSelective(Teachers record);

    Teachers selectByPrimaryKey(Integer teacherId);

    int updateByPrimaryKeySelective(Teachers record);

    int updateByPrimaryKey(Teachers record);
}