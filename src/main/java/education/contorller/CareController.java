package education.contorller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import education.pojo.Care;
import education.service.CareService;
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
 * 关注表controller层
 * @author xulibin
 */
@Controller
public class CareController {

    @Resource
    private CareService cs;


    /**
     * 查询关注表数据
     * @return
     */
    @RequestMapping(value = "carePage")
    @ResponseBody
    public String carePage(@RequestParam(value = "limit" ,required = false) String pageSize,
                           @RequestParam(value ="page",required = false) String pageIndex){

        System.out.println("pageIndex-->"+pageIndex);
//        System.out.println("username-->"+username);
//        System.out.println("courseName-->"+courseName);
        //分页查询所有关注信息
        Page<Object> page = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        List<Care> careList=cs.careList();

//       for (int i=0;i<careList.size();i++){
//           System.out.println("关注-->"+careList.get(i));
//        }

        JSONObject json=new JSONObject();

        json.put("code",0);
        json.put("msg","");
        json.put("count",page.getTotal());

        json.put("careList", JSONArray.fromObject(careList));

        return json.toString();
    }

    /**
     * 根据ID删除关注
     * @param careId 关注表ID
     * @return 返回的页面
     */
    @RequestMapping(value = "delCare")
    @ResponseBody
    public String delCare(@RequestParam(value = "careId" ,required = false) String careId){
        System.out.println("careId-->"+careId);
        int i=cs.deleteByID(Integer.parseInt(careId));
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 批量删除关注信息
     * @param ids
     * @return
     */
    @RequestMapping(value = "delCares")
    @ResponseBody
    public String delCares(@RequestParam("ids") String ids){
        String[] care_ids=ids.split(",");
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        int j=0;
        for (int i=0;i<care_ids.length;i++){
            if(care_ids[i]!=null){
                cs.deleteByID(Integer.parseInt(care_ids[i]));
                j++;
                json.put("data",j);
            }
        }
        return json.toString();
    }

    /**
     * 添加关注表信息
     * @param care 关注表信息
     * @return
     */
    @RequestMapping(value = "insertCare")
    @ResponseBody
    public String insertCare(Care care){
        System.out.println("care-->"+care);
        int i=cs.insert(care);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 根据关注ID查询关注信息，做修改的回显
     * @param careId 关注ID
     * @param model 存放关注信息
     * @return
     */
    @RequestMapping(value = "uploadCare")
    public String uploadCare(@RequestParam("careId") String careId, Model model){
        System.out.println("careId-->"+careId);
        if(careId!=null){
            Care care=cs.selectByID(Integer.parseInt(careId));
            model.addAttribute("care",care);
            System.out.println("care-->"+care);
        }
        return "admin/uploadcare";
    }

    /**
     * 修改关注信息
     * @param care 关注信息
     * @return
     */
    @RequestMapping(value = "updateCare")
    @ResponseBody
    public String updateCare(Care care){
        System.out.println("updateCare-->"+care);
        int i=cs.updateByID(care);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }
}
