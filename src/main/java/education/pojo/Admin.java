package education.pojo;

/**
 * 管理员类
 * @auther rlh
 */

public class Admin {
    private int admin_id;// 管理员ID
    private String admin_name;//管理员姓名
    private String admin_pwd;//管理员密码
    private String adminphone;//管理员手机号

    public Admin() {
    }

    public Admin(int admin_id, String admin_name, String admin_pwd, String adminphone) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_pwd = admin_pwd;
        this.adminphone = adminphone;
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

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_pwd='" + admin_pwd + '\'' +
                ", adminphone='" + adminphone + '\'' +
                '}';
    }
}
