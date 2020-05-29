package education.dao;

import education.pojo.Block;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/*
 * @Description:对板块信息进行操作的dao层
 * @author nss
 * @date 2019/4/15 9:27
 */
public interface BlockMapper {

    /**
     * 查询所有数据信息
     * @return 返回block集合
     */
    List<Block> selectBlocks(@Param("blockName")String blockName);

    /**
     * 根据主键删除数据
     * @param blockId  需要删除的数据的主键
     * @return 返回影响的条数
     */
    int deleteByPrimaryKey(Integer blockId);

    /**
     * 插入数据
     * @param record  需要插入的数据
     * @return 返回影响的条数
     */
    int insert(Block record);

    /**
     * 插入数据
     * @param record  需要插入的数据
     * @return 返回影响的条数
     */
    int insertSelective(Block record);

    /**
     * 根据主键查询
     * @param blockId 需要查询的数据的主键
     * @return 返回block对象
     */
    Block selectByPrimaryKey(Integer blockId);

    /**
     * 修改信息
     * @param record 修改后的信息封装的对象
     * @return
     */
    int updateByPrimaryKeySelective(Block record);

    /**
     * 修改信息
     * @param record 修改后的信息封装的对象
     * @return
     */
    int updateByPrimaryKey(Block record);
}