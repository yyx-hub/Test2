package education.service;


import education.dao.AdminMapper;
import education.pojo.Admin;
import education.pojo.AdminRole;
import education.pojo.Menu;
import education.pojo.RoleAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员
 * @author rlh
 */
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 登录方法
     * @param admin
     * @return
     */
    public List<Menu> login(Admin admin) {
        return adminMapper.login(admin);
    }

    /**
     * 查询管理员
     * @return
     */
    public List<Admin>  adminInfo() {
        return adminMapper.adminInfo();
    }

    /**
     * 根据ID查管理员
     * @param admin_id
     * @return
     */
   public Admin updateadmin(int admin_id) {
        return adminMapper.updateadmin(admin_id);
    }

    /**
     * 修改管理员
     * @param admin
     */
    public int updateadmins(Admin admin) {

        return  adminMapper.updateadmins(admin);
    }

    /**
     * 删除管理员
     * @param admin_id
     */
    public int deleteadmin(int admin_id) {

       return adminMapper.deleteadmin(admin_id);
    }

    /**
     * 增加管理员
     * @param adminRole
     * @return
     */
    public boolean addadmin(AdminRole adminRole) {
        boolean flag=false;
        int role_id=adminRole.getRole_level();
        int sount= adminMapper.addadmin(adminRole);
         int admin_id= adminMapper.suxadmin(adminRole);
        RoleAdmin roleAdmin=new RoleAdmin(0,role_id,admin_id);
        int count= adminMapper.addusro(roleAdmin);
        if(count>0&&sount>0){
            flag=true;
        }
        return flag;
    }

    /**
     * 批量删除
     * @param i
     * @return
     */
    public int deleteadminById(int i) {
        return  adminMapper.deleteadminById(i);
    }
}
