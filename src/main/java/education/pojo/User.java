package education.pojo;

/**
 * 用户实体类
 * @author xulibin
 * 2019/4/8
 */
public class User {
    private Integer user_id;

    private String user_nickname;

    private String user_name;

    private String user_img;
    //用户状态，0在上学，1在上班11
    private Integer user_state;

    private String user_phone;

    private String user_email;

    private String user_password;
    //0否1shi
    private Integer vip;

    public User() {
    }

    public User(Integer user_id, String user_nickname, String user_name, String user_img, Integer user_state, String user_phone, String user_email, String user_password, Integer vip) {
        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.user_name = user_name;
        this.user_img = user_img;
        this.user_state = user_state;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.user_password = user_password;
        this.vip = vip;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public Integer getUser_state() {
        return user_state;
    }

    public void setUser_state(Integer user_state) {
        this.user_state = user_state;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_img='" + user_img + '\'' +
                ", user_state=" + user_state +
                ", user_phone='" + user_phone + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_password='" + user_password + '\'' +
                ", vip=" + vip +
                '}';
    }
}