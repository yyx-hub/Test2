package education.contorller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import education.pojo.Role;
import education.service.RoleService;
import education.util.OutUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 角色权限类
 * @author  rlh
 */
@Controller
public class RoleContorller {

    @Resource
    private RoleService roleService;

    /**
     * 分页组合查询所有角色
     * @param response
     */
    @RequestMapping("/roleInfo.do")
    public void roleInfo(@RequestParam(value = "page",required = false) int page,
                         @RequestParam(value = "limit",required = false) int limit,
            @RequestParam(value = "role_name",required = false) String role_name,
            @RequestParam(value = "role_level" ,required = false) String role_level,
            HttpServletResponse response){
        Page<Object> pogo= PageHelper.startPage(page,limit);
        List<Role> roleList=roleService.roleInfo(role_name,role_level);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",pogo.getTotal()); //总记录数
        json.put("data", JSONArray.fromObject(roleList));
        OutUtil.print(json,response);
    }

    /**
     * 无条件查询所有角色
     * @param response
     */
    @RequestMapping("/roleList.do")
    public void  roleList(HttpServletResponse response){
       List<Role> roleList= roleService.roleList();
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",10); //总记录数
        json.put("data", JSONArray.fromObject(roleList));
        OutUtil.print(json,response);
    }
    /**
     * 新增角色
     * @param role
     * @param response
     */
    @RequestMapping("/addrole.do")
    public void addrole(Role role, HttpServletResponse response){
      int count= roleService.addrole(role);
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
     * 根据ID删除角色
     * @param role_id
     */
    @RequestMapping("/deleterole.do")
    public void deleterole(@RequestParam("role_id") int role_id, HttpServletResponse response){
      int count= roleService.deleterole(role_id);
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
     * 根据ID查角色
     * @param role_id
     * @param response
     */
    @RequestMapping("/updaterole.do")
    public ModelAndView updaterole(@RequestParam("role_id") int role_id, HttpServletResponse response){
       ModelAndView mav=new ModelAndView("/admin/updaterole");
        Role role=roleService.roleshow(role_id);
        mav.addObject("role",role);
        return mav;
    }

    /**
     * 修改角色信息
     * @param role
     * @param response
     */
    @RequestMapping("/updateroles.do")
    public void updateroles(Role role, HttpServletResponse response){
      int count= roleService.updaterole(role);
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
