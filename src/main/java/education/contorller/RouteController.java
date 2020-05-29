package education.contorller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import education.pojo.Route;
import education.service.RouteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 路径controller层
 * @author xulibin
 */
@Controller
public class RouteController {

    @Resource
    private RouteService rs;


    /**
     *查询所有路径信息
     * @return 返回查询到的记录数，json字符串
     */
    @RequestMapping(value = "routeList")
    @ResponseBody
    public String routeList(){
        List<Route> routeList=rs.routeList();
//        for (int i=0;i<routeList.size();i++){
//            System.out.println("yonghu"+routeList.get(i));
//        }

        JSONObject json=new JSONObject();

        json.put("code",0);
        json.put("msg","");
        json.put("count",routeList.size());

        json.put("routeList", JSONArray.fromObject(routeList));

        return json.toString();
    }

    /**
     *分页查询
     * @param pageSize 每页记录数
     * @param pageIndex 页码下标
     * @return 返回查询到的记录数，json字符串
     */
    @RequestMapping(value = "routePage")
    @ResponseBody
    public String routePage(@RequestParam(value = "limit" ,required = false) String pageSize,
                           @RequestParam(value ="page",required = false) String pageIndex,
                           @RequestParam(value ="routename",required = false) String routename,
                           @RequestParam(value ="routedesc",required = false) String routedesc){
        System.out.println("pageIndex-->"+pageIndex);
        //分页查询所有用户信息
        Page<Object> page = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        List<Route> routeList=rs.routePage(routename,routedesc);
//        for (int i=0;i<routeList.size();i++){
//            System.out.println("yonghu"+routeList.get(i));
//        }

        JSONObject json=new JSONObject();

        json.put("code",0);
        json.put("msg","");
        json.put("count",page.getTotal());

        json.put("routeList", JSONArray.fromObject(routeList));

        return json.toString();
    }

    /**
     * 根据ID删除用户
     * @param routeId 用户ID
     * @return 返回的页面
     */
    @RequestMapping(value = "delRoute")
    @ResponseBody
    public String delRoute(@RequestParam(value = "routeId" ,required = false) String routeId){
        int i=rs.deleteRouteById(Integer.parseInt(routeId));
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 添加路径信息
     * @param route 路径信息
     * @return
     */
    @RequestMapping(value = "insertRoute")
    @ResponseBody
    public String insertRoute(Route route){
        System.out.println("route-->"+route);
        int i=rs.insert(route);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 根据路径ID查询路径信息，做修改的回显
     * @param routeId 路径ID
     * @param model 存放路径信息
     * @return
     */
    @RequestMapping(value = "uploadRoute")
    public String uploadRoute(@RequestParam("routeId") String routeId, Model model){
        //System.out.println("routeId-->"+routeId);
        if(routeId!=null){
            Route route = rs.selectRouteById(Integer.parseInt(routeId));
            model.addAttribute("route",route);
            System.out.println("route-->"+route);
        }
        return "admin/uploadroute";
    }

    /**
     * 修改路径信息
     * @param route 路径信息
     * @return
     */
    @RequestMapping(value = "updateRoute")
    @ResponseBody
    public String updateRoute(Route route){
        int i=rs.updateRouteById(route);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 批量删除路径
     * @param ids
     * @return
     */
    @RequestMapping(value = "delRoutes")
    @ResponseBody
    public String delRoutes(@RequestParam("ids") String ids){
        String[] routeIds=ids.split(",");
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        int j=0;
        for (int i=0;i<routeIds.length;i++){
            if(routeIds[i]!=null){
                rs.deleteRouteById(Integer.parseInt(routeIds[i]));
                j++;
                json.put("data",j);
            }
        }
        return json.toString();
    }

}
