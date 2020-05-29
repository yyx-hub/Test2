package education.contorller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import education.pojo.Course;
import education.pojo.Tag;
import education.service.CourseService;
import education.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Created
 * @ Description:前台课程的操作
 * @ Author: 倪绍帅
 */
@Controller
public class EduCourseController {

    @Resource
    private CourseService courseService;
    @Resource
    private TagService tagService;

    @RequestMapping("selCourseN.do")
    public String selectCourse(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "pageIndex", required = false) String pageIndex,
                               @RequestParam(value = "coursePrice", required = false) String coursePrice,
                               @RequestParam(value = "tagName", required = false) String tagName
    ) {

        System.out.println(request.getParameter("tagName"));
        //将参数传回页面，便于下次查询
        request.setAttribute("tagName",tagName);
        request.setAttribute("coursePrice",coursePrice);
        request.setAttribute("pageIndex",pageIndex);
        /**
         * 判断接收的标签名是否为all
         */
        if (tagName.equals("all")){
            tagName=null;
        }
        if (coursePrice.equals("all")){
            coursePrice="3";
        }

        /**
         * 新建标签以及课程集合，用于存储查询到的数据
         */
        List<Tag> tagList = new ArrayList<Tag>();
        List<Course> courseList = new ArrayList<Course>();

        tagList = tagService.selTagList();//查询标签数据

        Page<Object> pageHelper = PageHelper.startPage(Integer.parseInt(pageIndex), 15);//开始分页
        courseList = courseService.selCourse(tagName.trim(),Integer.parseInt(coursePrice));//组合查询课程信息

        int pages = pageHelper.getPages();

        //将查询到的数据传到页面用于展示
        request.setAttribute("tagList", tagList);
        request.setAttribute("courseList", courseList);
        request.setAttribute("pages", pages);
        return "offic/courses/index";

    }

    @RequestMapping("selCourseByIdN.do")
    public String selCourseById(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "courseId", required = false) String courseId){
        return "offic/courses/index";
    }
}
