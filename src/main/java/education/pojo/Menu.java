package education.pojo;

/**
 * 菜单实体类
 * @author rlh
 */
public class Menu {
    private int menu_id;//菜单ID
    private String menu_name;//菜单名称
    private String menu_code;//菜单编码
    private int last_menu;//上级菜单
    private String menu_line;//菜单状态
    private String menu_url;//菜单Url

    public Menu() {
    }

    public Menu(int menu_id, String menu_name, String menu_code, int last_menu, String menu_line, String menu_url) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.menu_code = menu_code;
        this.last_menu = last_menu;
        this.menu_line = menu_line;
        this.menu_url = menu_url;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_code() {
        return menu_code;
    }

    public void setMenu_code(String menu_code) {
        this.menu_code = menu_code;
    }

    public int getLast_menu() {
        return last_menu;
    }

    public void setLast_menu(int last_menu) {
        this.last_menu = last_menu;
    }

    public String getMenu_line() {
        return menu_line;
    }

    public void setMenu_line(String menu_line) {
        this.menu_line = menu_line;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

}
