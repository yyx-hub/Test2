package education.dao;

import education.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户dao层接口
 * @author xulibin
 */
public interface UserMapper {


    /**
     * 用户登入
     * @param map
     * @return
     */
    User userLogin(Map map);

    /**
     *验证手机号码
     * @return 返回查询到的记录数，json字符串
     */
    int checkPhone(@Param("phone") String phone);

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    User queryUserById(Integer userId);

    /**
     * 查询用户信息
     *@param map  存放查询的条件
     * @return
     */
    List<User> queryUserList(Map map);

    /**
     * 根据用户ID删除用户
     * @param userId 用户ID
     * @return 返回修改的记录数
     */
    int deleteUserById(Integer userId);

    /**
     * 添加用户
     * @param user 用户信息
     * @return 返回修改的记录数
     */
    int insertUser(User user);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return
     */
    int updateUser(User user);

}