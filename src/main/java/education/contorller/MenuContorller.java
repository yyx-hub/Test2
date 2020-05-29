package education.contorller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import education.pojo.Menu;
import education.service.MenuService;
import education.util.OutUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 菜单控制层
 * @author rlh
 */
@Controller
public class MenuContorller {

    @Resource
    private MenuService menuService;

    /**
     * 分页组合查询菜单
     * @param limit 分页数
     * @param page 起始页
     * @param menu_name 菜单名称
     * @param response
     */
    @RequestMapping("/menulist.do")
    public void menuInfo(
          @RequestParam("limit") int limit,
          @RequestParam("page") int page,
          @RequestParam(value = "menu_name",required = false) String menu_name,
          HttpServletResponse response){
     Page<Object> pogo= PageHelper.startPage(page,limit);
     List<Menu> menuList= menuService.menulist(menu_name);
     JSONObject json=new JSONObject();
     json.put("code",0);
     json.put("msg","");
     json.put("count",pogo.getTotal()); //总记录数
     json.put("data", JSONArray.fromObject(menuList));
     OutUtil.print(json,response);
  }
    /**
     * 根据ID删除菜单
     * @param menu_id
     * @param response
     * @return
     */
    @RequestMapping("/deletemenu.do")
    public void deletemenu(@RequestParam("menu_id") int menu_id, HttpServletResponse response){
      ModelAndView mav=null;
        boolean flag= menuService.deletemenu(menu_id);
        JSONObject json=new JSONObject();
        json.put("code",0);
        if(flag){
            json.put("msg",1);
        }else {
            json.put("msg",0);
        }
        json.put("count",1);
        OutUtil.print(json,response);
    }

    /**
     * 根据ID查菜单
     * @param menu_id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updatemenu.do")
    public ModelAndView updatemenu(@RequestParam("menu_id") int menu_id, HttpServletRequest request, HttpServletResponse response){
        Menu menu=menuService.menuInfo(menu_id);
        ModelAndView mav=new ModelAndView("admin/updatemenu");
        mav.addObject("menu",menu);
        return mav;
    }

    /**
     * 修改菜单
     * @param menu
     * @param response
     * @return
     */
    @RequestMapping("/updatemenus.do")
    public void updatemenus(Menu menu, HttpServletResponse response){
        int count=menuService.updatemenu(menu);
        JSONObject json=new JSONObject();
        json.put("code",0);
        if(count>0){
            json.put("msg",1);
        }else {
            json.put("msg",0);
        }
        json.put("count",1);
        OutUtil.print(json,response);
    }

    /**
     * 查询主菜单
     * @param response
     */
    @RequestMapping("/selectmenu.do")
    public void selectmenu(HttpServletResponse response){
       List<Menu> menuList=menuService.selectmenu();
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",10); //总记录数
        json.put("data", JSONArray.fromObject(menuList));
        OutUtil.print(json,response);
    }

    /**
     * 新增菜单
     * @param menu
     * @param response
     */
    @RequestMapping("/addmenu.do")
    public void  addmenu(Menu menu, HttpServletResponse response){
      int count= menuService.addmenu(menu);
        JSONObject json=new JSONObject();
        json.put("code",0);
        if(count>0){
            json.put("msg",1);
        }else {
            json.put("msg",0);
        }
        json.put("count",1);
        OutUtil.print(json,response);
    }
}
