package education.contorller;

import education.pojo.Edu_topic;
import education.service.Edu_topicService;
import education.util.JsonDateValueProcessor;
import education.util.OutUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


/**
 * 帖子业务逻辑层
 */
@Controller
public class Edu_topicController {

    @Autowired
    private Edu_topicService edu_topicService;

    /**
     * 查询所有帖子
     */
    @RequestMapping(value = "/allTopic.do")
    @ResponseBody
    public String allTopic(){
        List<Edu_topic> edu_topicList=edu_topicService.allTopic();
        JSONObject json=new JSONObject();
        //layui格式
        json.put("code",0);
        json.put("msg","");
        json.put("count",10);
        JsonConfig config=new JsonConfig();
        //时间格式转化
        config.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
        json.put("data", JSONArray.fromObject(edu_topicList,config));
        return json.toString();
    }
    /**
     * 根据id查询帖子
     */
    @RequestMapping(value = "/findTopicbyid.do")
    public String findTopicbyid(@RequestParam(value = "topic_id",required = false) int topic_id, HttpServletRequest request, Model model){
        Edu_topic edu_topic=edu_topicService.findTopicbyid(topic_id);
        model.addAttribute("Edu_topic",edu_topic);
        return "admin/updateTopic";
    }

    /**
     * 修改回显操作
     */
    @RequestMapping(value = "/updateTopicHuixian.do")
    public ModelAndView updateTopicHuixian(@RequestParam(value = "topic_id") int topic_id){
        Edu_topic edu_topic=edu_topicService.findTopicbyid(topic_id);
        JsonConfig config=new JsonConfig();
        //时间格式转化
        config.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
        JSONArray jsonArray= JSONArray.fromObject(config);
        ModelAndView mav=new ModelAndView("/admin/updateTopic");
        mav.addObject("edu_topic",edu_topic);
        return mav;
    }

    /**
     * 修改帖子
     */
    @RequestMapping(value = "/updateTopic.do")
    @ResponseBody
    public String updateTopic(Edu_topic edu_topic){
        int count=edu_topicService.updateTopic(edu_topic);
        JSONObject json=new JSONObject();
        //layui格式
        json.put("code",0);
        json.put("count",1);
        if (count>0){
            json.put("msg",1);
        }else {
            json.put("msg",0);
        }
        json.put("data",count);
        return json.toString();
    }
    /**
     * 删除帖子
     */
    @RequestMapping(value = "/deleteTopic.do")
    public void deleteTopic(@RequestParam(value = "topic_id") int topic_id, HttpServletResponse response){
        int count=edu_topicService.deleteTopic(topic_id);
        JSONObject json=new JSONObject();
        //layui格式
        json.put("code",0);
        json.put("count",1);
        if (count>0){
            json.put("msg",1);
        }else {
            json.put("msg",0);
        }
        OutUtil.print(json,response);
    }

    /**
     * 新增帖子
     */
    @RequestMapping(value = "/addTopic.do")
    @ResponseBody
    public void addTopic(Edu_topic edu_topic,HttpServletResponse response){
        int count=edu_topicService.addTopic(edu_topic);
        System.out.println("帖子是"+edu_topic);
        JSONObject json=new JSONObject();
        //layui格式
        json.put("code",0);
        json.put("count",1);
        if (count>0){
            json.put("msg",1);
        }else {
            json.put("msg",0);
        }
        OutUtil.print(json,response);
    }



}
