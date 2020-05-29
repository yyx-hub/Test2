package education.pojo;

/**
 * 权限管理
 * @author  rlh
 */
public class RoleAdmin {
    private int usro_id;//权限编号
    private int role_id;//角色编号
    private int admin_id;//管理员编号

    public RoleAdmin() {
    }

    public RoleAdmin(int usro_id, int role_id, int admin_id) {
        this.usro_id = usro_id;
        this.role_id = role_id;
        this.admin_id = admin_id;
    }

    public int getUsro_id() {
        return usro_id;
    }

    public void setUsro_id(int usro_id) {
        this.usro_id = usro_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
}
