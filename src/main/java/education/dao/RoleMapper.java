package education.dao;

import education.pojo.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色权限接口
 */
public interface RoleMapper {
    /**
     * 分组查询所有角色
     * @return
     */
    public List<Role> roleInfo(Map map);

    /**
     * 增加角色
     * @param role
     * @return
     */
    int addrole(Role role);

    /**
     * 无条件查询角色
     * @return
     */
    List<Role> roleList();

    /**
     * 根据ID删除角色
     * @param role_id
     * @return
     */

    int deleterole(int role_id);

    /**
     * 根据ID查角色
     * @param role_id
     * @return
     */

    Role roleshow(int role_id);

    /**
     * 修改信息
     * @param role 角色
     * @return
     */

    int updaterole(Role role);
}
