package education.service;

import education.dao.MenuMapper;
import education.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单类
 * @author rlh
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据ID删除菜单
     * @param menu_id
     * @return
     */
    public Boolean deletemenu(int menu_id) {
        boolean flag=false;
        int count= menuMapper.deleterolemenu(menu_id);
        int couns= menuMapper.deletemenu(menu_id);
        if(count>0&&couns>0){
          flag=true;
       }
      return flag;
    }

    /**
     * 根据ID查询菜单
     * @param menu_id
     * @return
     */
    public Menu menuInfo(int menu_id) {
        return menuMapper.menuInfo(menu_id);
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    public int updatemenu(Menu menu) {
        return menuMapper.updatemenu(menu);
    }

    /**
     * 查询主菜单
     * @return
     */
    public List<Menu> selectmenu() {
        return menuMapper.selectmenu(11110);
    }

     /**
     * 分页组合查询
     * @param menu_name
     * @return
     */
    public List<Menu> menulist(String menu_name) {
        
        return  menuMapper.menulist(menu_name);
    }

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    public int addmenu(Menu menu) {
        return menuMapper.addmenu(menu);
    }
}
