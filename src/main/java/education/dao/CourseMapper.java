package education.dao;

import education.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 课程表对应的Dao层
 */
public interface CourseMapper {

    /**
     * 查询所有数据信息
     * @return 返回course集合
     */
    List<Course> selectCourse(@Param("courseName")String courseName);

    /**
     * 根据主键删除课程信息
     * @param courseId
     * @return
     */
    int deleteByPrimaryKey(Integer courseId);

    /**
     * 添加课程信息
     * @param record
     * @return
     */
    int insert(Course record);

    /**
     * 添加课程信息
     * @param record
     * @return
     */
    int insertSelective(Course record);

    /**
     * 根据主键ID查询课程信息
     * @param courseId
     * @return
     */
    Course selectByPrimaryKey(Integer courseId);

    /**
     * 修改课程信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Course record);

    /**
     * 根据主键ID修改课程信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Course record);

    /**
     * 无条件查询所有课程信息
     * @return
     */
    List<Course> selCourse(Map map);


}