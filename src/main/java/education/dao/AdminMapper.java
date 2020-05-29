package education.dao;

import education.pojo.Admin;
import education.pojo.AdminRole;
import education.pojo.Menu;
import education.pojo.RoleAdmin;

import java.util.List;

/**
 * 定义管理员接口
 * @author rlh
 */

public interface AdminMapper {

    /**
     * 验证登录
      * @param admin 对象
     * @return id 数字
     */
   List<Menu> login(Admin admin);

    /**
     * 查询所有管理员
     * @return
     */

    List<Admin> adminInfo();

    /**
     * 根据ID查管理员
     * @param admin_id
     * @return
     */

    Admin updateadmin(int admin_id);

    /**
     * 修改管理员
     * @param admin
     */

    int updateadmins(Admin admin);

    /**
     * 删除管理员
     * @param admin_id
     */
    int deleteadmin(int admin_id);

 /**
  * 增加管理员
  * @param adminRole
  * @return
  */

  int addadmin(AdminRole adminRole);

 /**
  * 增加后查询id
  * @param adminRole
  * @return
  */

 int suxadmin(AdminRole adminRole);

 /**
  * 增加权限
  * @param roleAdmin
  */

   int addusro(RoleAdmin roleAdmin);

    /**
     * 批量删除
     * @param i
     * @return
     */

    int deleteadminById(int i);

}
