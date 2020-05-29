package education.contorller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import education.pojo.*;
import education.service.TopicService;
import education.util.OutUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 帖子控制层
 * @author rlh
 */
@Controller
public class TopicContorller {

    @Resource
    private TopicService topicService;


    /**
     * 查询所有帖子
     *
     * @param response
     */
    @RequestMapping("/topic.do")
    public String Topic(@RequestParam(value = "page", required = false) Integer page,
                        HttpServletResponse response, Model ml) throws Exception {
        Page<Object> pogo = null;
        if (page == null) {
            pogo = PageHelper.startPage(1, 10);
        } else {
            pogo = PageHelper.startPage(page, 10);
        }
        List<Topic> topicList = topicService.topicInfo();
        ml.addAttribute("list", topicList);
        return "offic/questions/index";
    }

    /**
     * 查询所有板块
     * @param response
     */
    @RequestMapping("/blockInfo.do")
    public void blockInfo(HttpServletResponse response){
     List<Block> blockList= topicService.blockInfo();
     JSONObject json=new JSONObject();
     json.put("data", JSONArray.fromObject(blockList));
     OutUtil.print(json,response);
    }

    /**
     * 查询所有用户
     * @param response
     */
    @RequestMapping("/userInfo.do")
    public void userInfo(HttpServletResponse response){
        List<User> userList=topicService.userInfo();
        JSONObject json=new JSONObject();
        json.put("data", JSONArray.fromObject(userList));
        OutUtil.print(json,response);

    }

    /**
     * 查询所有课程
     * @param response
     */
    @RequestMapping("/courseInfo.do")
    public void courseInfo(HttpServletResponse response){
        List<Course> courseList=topicService.courseInfo();
        JSONObject json=new JSONObject();
        json.put("data", JSONArray.fromObject(courseList));
        OutUtil.print(json,response);
    }
    @RequestMapping("/insertTopic.do")
    public void addTopic(Edu_topic edu_topic,
            HttpServletResponse response) throws ParseException{
        //得到当前时间
        Date today=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd");
        String str=sdf.format(today);
         str=str.replace("年","-");
         str=str.replace("月","-");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf2.parse(str);
     edu_topic.setTopic_time(date);
        edu_topic.setLooks(0);
        edu_topic.setReplys(0);
        System.out.println(edu_topic);
      int count= topicService.addTopics(edu_topic);
        JSONObject json=new JSONObject();
        if(count>0){
            json.put("msg",1);
        }else {
            json.put("msg",0);
        }
        OutUtil.print(json,response);
    }
}