package education.dao;

import education.pojo.Care;

import java.util.List;

/**
 * 关注信息dao层接口
 * @author  xulibin
 */
public interface CareMapper {

    /**
     * 查询所有关注信息
     * @return
     */
    List<Care> careList();
    /**
     * 根据ID删除关注信息
     * @param careId
     * @return
     */
    int deleteByID(Integer careId);

    /**
     * 添加关注信息
     * @param record
     * @return
     */
    int insert(Care record);

    /**
     * 添加选择的关注信息
     * @param record
     * @return
     */
    int insertSelective(Care record);

    /**
     * 根据ID查询关注信息
     * @param careId
     * @return
     */
    Care selectByID(Integer careId);

    /**
     * 根据ID修改关注信息
     * @param record
     * @return
     */
    int updateByIDSelective(Care record);

    /**
     * 根据ID修改关注信息
     * @param record
     * @return
     */
    int updateByID(Care record);
}