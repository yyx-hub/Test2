package education.service;

import education.dao.BlockMapper;
import education.pojo.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Created
 * @ Description:对板块信息进行操作的service层
 * @ Author: 倪绍帅
 */
@Service
public class BlockService {
    @Autowired
    private BlockMapper blockMapper;

    /**
     * 查询所有板块数据信息
     * @return 返回block集合
     */
    public List<Block> selectBlocks(String blockName){
        return blockMapper.selectBlocks(blockName);
    }

    /**
     * 根据主键删除数据
     * @param blockId  需要删除的数据的主键
     * @return 返回影响的条数
     */
    public int delBlock(Integer blockId){
        return blockMapper.deleteByPrimaryKey(blockId);
    }

    /**
     * 插入数据
     * @param block 需要插入的数据
     * @return 返回影响的条数
     */
    public int insert(Block block){
        return blockMapper.insertSelective(block);
    }

    /**
     * 根据主键查询
     * @param blockId 需要查询的数据的主键
     * @return 返回block对象
     */
    public Block selectByPrimaryKey(Integer blockId){
        return blockMapper.selectByPrimaryKey(blockId);
    }


    /**
     * 修改信息
     * @param block 修改后的信息封装的对象
     * @return
     */
    public int updateBlock(Block block){
        return blockMapper.updateByPrimaryKeySelective(block);
    }
}
