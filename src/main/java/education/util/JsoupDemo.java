package education.util;

import education.pojo.Course;
import education.service.CourseService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.sql.Date;

/**
 * @ Created
 * @ Description:爬虫
 * @ Author: 倪绍帅
 */
@Controller
public class JsoupDemo {

    @Resource
    private CourseService courseService;

    public  void jsoupDemo() throws Exception {
        for (int i = 1; i < 15; i++) {

            //设置请求路径
            String url = "https://www.shiyanlou.com/courses/?category=%E5%90%8E%E7%AB%AF%E5%BC%80%E5%8F%91&course_type=all&fee=all&tag=all&page="+i;
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 5.1; zh-CN) AppleWebKit/535.12 (KHTML, like Gecko) Chrome/22.0.1229.79 Safari/535.12")
                    .timeout(60000).get();//设置请求头解析html
            System.out.println(doc.title());//获取网页标题

            Elements eles = doc.select(" .content-box .container .row [class*=course-items-container] .row [class$=course]");//查找所有课程信息
            for (Element e : eles) {
                //将所有训练营的课程剔除
                if (e.select(" [class^=course-bootcamp]").text().equals("") && !e.select(" [class^=course-bootcamp]").text().equals("训练营")) {

                    String cName = e.select(" .course-name").first().text();//获取课程名
                    String cDesc = e.select(" .course-desc").first().text();//获取课程描述
                    String cImg = e.select(" .course-img img").first().attr("abs:src");//获取课程图片
                    String cPrice = e.select(" [class^=course-money]").text();
                    if (cPrice.equals("会员")) {
                        cPrice = "1";
                    } else {
                        cPrice = "0";
                    }

                    String cUrl = e.select(" .sign-box i").first().attr("abs:data-follow-url");//获取课程路径
                    cUrl = cUrl.substring(0, cUrl.lastIndexOf("/"));//截取课程绝对路径


                    //解析获取的课程路径
                    Document cDoc = Jsoup.connect(cUrl)
                            .userAgent("Mozilla/5.0 (Windows NT 5.1; zh-CN) AppleWebKit/535.12 (KHTML, like Gecko) Chrome/22.0.1229.79 Safari/535.12")
                            .timeout(60000).get();//设置请求头解析html


                    Element cEleString = cDoc.getElementById("labs");//解析获得的课时
                    Elements cKeCheng = cEleString.select(" .lab-item ");
                    int cHour = cKeCheng.size();

                    Course course=new Course(null,cName,null,new Date(new java.util.Date().getTime()),cDesc,null,cImg,Integer.parseInt(cPrice),cHour,null);
                    int count=courseService.insert(course);
                    System.out.println("课程表中添加了"+count+"条数据");

                }
            }
        }
    }
}
