package education.service;

import education.dao.UserMapper;
import education.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户service层
 * @author xulibin
 */
@Service(value = "us")
public class UserService {
    @Resource
    private UserMapper um;

    /**
     * 用户登入
     * @return
     */
    public User userLogin(String phone,String password){
        Map<String,String> map=new HashMap<String, String>();
        map.put("phone",phone);
        map.put("password",password);
        return um.userLogin(map);
    }

    /**
     *验证手机号码
     * @return 返回查询到的记录数，json字符串
     */
    public int checkPhone(String phone){
        return um.checkPhone(phone);
    }

    /**
     * 根据用户ID找用户
     * @param userId 用户ID
     * @return 用户对象
     */
    public User queryUserById(int userId){
        return um.queryUserById(userId);
    }

    /**
     * 查询用户信息
     * @param username  用户姓名
     * @param userphone 用户电话
     * @return
     */
    public List<User> queryUserList(String username,String userphone){
        Map<String,String> map=new HashMap();
        map.put("username",username);
        map.put("userphone",userphone);
        return um.queryUserList(map);
    }

    /**
     * 根据用户ID删除用户
     * @param userId 用户ID
     * @return 返回修改的记录数
     */
    public int deleteUserById(int userId){
        return um.deleteUserById(userId);
    }

    /**
     * 添加用户
     * @param user 用户信息
     * @return 返回修改的记录数
     */
    public int insertUser(User user){
        System.out.println("userService");
        return um.insertUser(user);
    }

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return
     */
    public int update(User user){
        return um.updateUser(user);
    }
}
