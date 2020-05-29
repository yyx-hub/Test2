package education.service;

import education.dao.CareMapper;
import education.pojo.Care;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 关注信息service层
 * @author xulibin
 */
@Service(value = "cs")
public class CareService {

    @Resource
    private CareMapper cm;

    /**
     * 查询所有关注信息
     * @return
     */
    public List<Care> careList(){
        return cm.careList();
    }
    /**
     * 根据ID删除关注信息
     * @param careId
     * @return
     */
    public int deleteByID(Integer careId){
        return cm.deleteByID(careId);
    }

    /**
     * 添加关注信息
     * @param record
     * @return
     */
    public int insert(Care record){
        return cm.insert(record);
    }

    /**
     * 添加选择的关注信息
     * @param record
     * @return
     */
    public int insertSelective(Care record){
        return cm.insertSelective(record);
    }

    /**
     * 根据ID查询关注信息
     * @param careId
     * @return
     */
    public  Care selectByID(Integer careId){
        return cm.selectByID(careId);
    }

    /**
     * 根据ID修改关注信息
     * @param record
     * @return
     */
    public int updateByIDSelective(Care record){
        return cm.updateByIDSelective(record);
    }

    /**
     * 根据ID修改关注信息
     * @param record
     * @return
     */
    public int updateByID(Care record){
        return cm.updateByID(record);
    }
}
