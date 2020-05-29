package education.service;

import education.dao.RoleMapper;
import education.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色权限类
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 分页组合查询所有角色
     * @return
     */

    public List<Role> roleInfo(String role_name, String role_level){
        Map<String ,String> map=new HashMap<String, String>();
        map.put("role_name",role_name);
        map.put("role_level",role_level);
        return roleMapper.roleInfo(map);
    }

    /**
     * 新增角色
     * @param role 角色
     * @return
     */
    public int addrole(Role role) {
        return roleMapper.addrole(role);
    }

    /**
     * 无条件查询所有角色
     * @return
     */
    public List<Role> roleList() {
        return roleMapper.roleList();
    }

    /**
     * 根据ID删除角色
     * @param role_id
     * @return
     */
    public int deleterole(int role_id) {
        return roleMapper.deleterole(role_id);
    }

    /**
     * 根据ID查角色
     * @param role_id
     * @return
     */
    public Role roleshow(int role_id) {
        return roleMapper.roleshow(role_id);
    }

    /**
     *修改角色信息
     * @param role
     * @return
     */
    public int updaterole(Role role) {
        return roleMapper.updaterole(role);
    }
}
