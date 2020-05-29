package education.service;


import education.dao.TagMapper;
import education.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Created
 * @ Description:对标签进行操作的service层
 * @ Author:  倪绍帅
 */
@Service
public class TagService {
    @Autowired
    private TagMapper tagMapper;

    /**
     * 查询标签信息
     * @return
     */
    public List<Tag> selTagList(){
        return tagMapper.selTagList();
    }
    /**
     * 根据姓名查询标签信息
     */
    public List<Tag> tagList(String tagName) {
        return tagMapper.selectList(tagName);
    }

    /*
     * @Description:根据id删除标签信息
     * @author nss
     * @param tagId：需要删除的数据的id
     * @return返回影响的条数
     */
    public int deleteByPrimaryKey(int tagId) {
        return tagMapper.deleteByPrimaryKey(tagId);
    }

    /**
     * 修改数据信息
     *
     * @param tag 修改后的数据
     * @return 返回影响的条数
     */
    public int updateByPrimaryKey(Tag tag) {
        return tagMapper.updateByPrimaryKey(tag);
    }

    /*
     * @Description:根据id查询数据信息
     * @author nss
     * @param 需要查询的数据的主键id
     * @return 返回查到的数据
     */
    public Tag selectByPrimaryKey(Integer tagId) {
        return tagMapper.selectByPrimaryKey(tagId);
    }

    /*
     * @Description:插入数据
     * @author nss
     * @param 需要插入的数据信息
     * @return 返回影响的条数
     */
    public int insert(Tag tag) {
        return tagMapper.insert(tag);
    }

    /**
     * 查询标签ID和标签名
     */
    public List<Tag> selectTagName(Tag tag) {
        return tagMapper.selectTagName(tag);
    }

}
