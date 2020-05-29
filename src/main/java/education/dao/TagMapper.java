package education.dao;



import education.pojo.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/*
 * @Description:对标签进行操作的接口
 * @author nss
 * @date 2019/4/9 9:37
 */
public interface TagMapper {


    /*
     * @Description:根据id删除信息
     * @author nss
     * @param 需要删除的数据的id
     * @return 返回影响的条数
     */
    int deleteByPrimaryKey(Integer tagId);


    /*
     * @Description:插入数据
     * @author nss
     * @param 需要插入的数据信息
     * @return 返回影响的条数
     */
    int insert(Tag record);


    /*
     * @Description:根据id查询数据信息
     * @author nss
     * @param 需要查询的数据的id
     * @return 返回查询的数据
     */
    Tag selectByPrimaryKey(Integer tagId);


    /*
     * @Description:修改数据信息
     * @author nss
     * @param 修改后的数据
     * @return 返回影响的条数
     */
    int updateByPrimaryKey(Tag record);


    /*
     * @Description:查询所有表中信息
     * @author nss
     * @return 返回查询的结果
     */
    List<Tag> selectList(@Param("tagName") String tagName);

    /**
     * 查询所有标签信息
     * @return
     */
    List<Tag> selTagList();


    List<Tag> selectTagName(Tag record);
}