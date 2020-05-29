package education.pojo;

/**
 * 角色实体类
 * @author rlh
 */
public class Role {
    private int role_id;//角色 ID
    private String role_name;//角色名称
    private String role_code;//角色编码
    private String role_line;//角色状态
    private int role_level;//角色等级

    public Role() {
    }

    public Role(int role_id, String role_name, String role_code, String role_line, int role_level) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.role_code = role_code;
        this.role_line = role_line;
        this.role_level = role_level;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_code() {
        return role_code;
    }

    public void setRole_code(String role_code) {
        this.role_code = role_code;
    }

    public String getRole_line() {
        return role_line;
    }

    public void setRole_line(String role_line) {
        this.role_line = role_line;
    }

    public int getRole_level() {
        return role_level;
    }

    public void setRole_level(int role_level) {
        this.role_level = role_level;
    }
}
