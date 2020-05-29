package education.pojo;

/**
 * 管理员权限实体类
 * @author rlh
 */

public class AdminRole {
    private int admin_id;// 管理员ID
    private String admin_name;//管理员姓名
    private String admin_pwd;//管理员密码
    private String adminphone;//管理员手机号
    private int role_level;//管理员权限

    public AdminRole() {
    }

    public AdminRole(int admin_id, String admin_name, String admin_pwd, String adminphone, int role_level) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_pwd = admin_pwd;
        this.adminphone = adminphone;
        this.role_level = role_level;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_pwd() {
        return admin_pwd;
    }

    public void setAdmin_pwd(String admin_pwd) {
        this.admin_pwd = admin_pwd;
    }

    public String getAdminphone() {
        return adminphone;
    }

    public void setAdminphone(String adminphone) {
        this.adminphone = adminphone;
    }

    public int getRole_level() {
        return role_level;
    }

    public void setRole_level(int role_level) {
        this.role_level = role_level;
    }

    @Override
    public String toString() {
        return "AdminRole{" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_pwd='" + admin_pwd + '\'' +
                ", adminphone='" + adminphone + '\'' +
                ", role_level=" + role_level +
                '}';
    }
}
