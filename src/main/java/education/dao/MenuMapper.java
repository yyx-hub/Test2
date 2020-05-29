package education.dao;

import education.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单类接口
 * @author rlh
 */
public interface MenuMapper {

    /**
     * 根据ID删除角色菜单表中数据
     * @param menu_id
     * @return
     */
    int deleterolemenu(int menu_id);
    /**
     * 根据ID删除菜单
     * @param menu_id
     */
    int deletemenu(int menu_id);


    /**
     * 根据ID查菜单
     * @param menu_id
     * @return
     */
    Menu menuInfo(int menu_id);

    /**
     * 根据回来修改菜单
     * @param menu
     * @return
     */
    int updatemenu(Menu menu);

    /**
     * 查询主菜单
     * @param i
     * @return
     */
    List<Menu> selectmenu(int i);

    /**
     * 分页组合查询
     * @param menu_name 菜单名
     * @return
     */
    List<Menu> menulist(@Param("menu_name") String menu_name);

    /**
     * 新增菜单
     * @param menu
     * @return
     */

    int addmenu(Menu menu);

}
