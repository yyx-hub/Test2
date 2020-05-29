package education.contorller;


import education.pojo.Admin;
import education.pojo.AdminRole;
import education.pojo.Menu;
import education.service.AdminService;
import education.util.OutUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员类
 */
@Controller
public class AdminContorller {
    List<Menu> menuList=new ArrayList<Menu>();

    @Resource
    public AdminService adminService;

    /**
     * 登录方法
     *
     * @param name
     * @param pwd
     * @return 菜单
     */
    @RequestMapping("/login.do")
    public ModelAndView login(@RequestParam("name") String name, @RequestParam("pwd") String pwd, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav=null;
        Admin admin = new Admin();
        admin.setAdmin_name(name);
        admin.setAdmin_pwd(pwd);
        menuList = adminService.login(admin);
        if (menuList.size() == 0) {
             mav=new ModelAndView("/admin/login");
             mav.addObject("menuList",menuList);
        } else {
            mav=new ModelAndView("/admin/major");
            mav.addObject("menuList",menuList);

        }
        return mav;
        }

    /**
     * 查询菜单
     * @param response
     */
       @RequestMapping("/menuInfo.do")
        public void menuInfo(HttpServletResponse response){
        JSONObject json=new JSONObject();
            json.put("code",0);
            json.put("msg","");
            json.put("count",10); //总记录数
        json.put("data", JSONArray.fromObject(menuList));
        OutUtil.print(json,response);
        }

    /**
     * 查询管理员
     * @param response
     */
    @RequestMapping("/adminInfo.do")
        public void adminInfo(HttpServletResponse response){
        List<Admin> adminList= adminService.adminInfo();
        JSONObject json=new JSONObject();
            json.put("code",0);
            json.put("msg","");
            json.put("count",10); //总记录数
        json.put("data", JSONArray.fromObject(adminList));
        OutUtil.print(json,response);
        }

    /**
     * 修改回显
     * @param admin_id
     * @param response
     * @return
     */
        @RequestMapping("/updateadmin.do")
        public ModelAndView updateadmin(@RequestParam("admin_id") int admin_id, HttpServletResponse response){
           Admin admin= adminService.updateadmin(admin_id);
          ModelAndView mav=new ModelAndView("/admin/updateadmin");
          mav.addObject("admin",admin);
           return mav;
        }

    /**
     * 修改数据
     * @param admin
     * @return
     */
        @RequestMapping("/updateadmins.do")
        @ResponseBody
        public String updateadmins(Admin admin){
         int count= adminService.updateadmins(admin);
            JSONObject json=new JSONObject();
            if(count>0){
                json.put("msg",1);
            }else {
                json.put("msg",0);
            }
            json.put("data",count);
            return json.toString();
        }

    /**
     * 删除管理员
     * @param admin_id
     * @param response
     * @return
     */
        @RequestMapping("/deleteadmin.do")
        public void deleteadmin(@RequestParam("admin_id") int admin_id, HttpServletResponse response){
           int count= adminService.deleteadmin(admin_id);
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
        @RequestMapping("/addadmin.do")
        public void addadmin(AdminRole adminRole, HttpServletRequest request, HttpServletResponse response){
           boolean flag=adminService.addadmin(adminRole);
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
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/deladmins.do")
    public String delUsers(@RequestParam("ids") String ids){
        String[] admin_ids=ids.split(",");
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        int j=0;
        for (int i=0;i<admin_ids.length;i++){
            if(admin_ids[i]!=null){
                adminService.deleteadminById(Integer.parseInt(admin_ids[i]));
                j++;
                json.put("data",j);
            }
        }
        return json.toString();
    }
    }
