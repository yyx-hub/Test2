package education.contorller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import education.pojo.Course;
import education.service.CourseService;
import education.util.JsonDateValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 对课程信息进行查询的Controller层
 */
@Controller
public class CourseController {
    @Resource
    private CourseService courseService;


    /**
     * 查询课程信息
     * @param pageSize  分页查询每页最大记录数
     * @param pageIndex 分页查询当前页码
     * @return 返回json数据
     */
    @RequestMapping("/courseList.do")
    @ResponseBody
    public String courseList(@RequestParam(value = "limit", required = false) String pageSize,
                            @RequestParam(value = "page", required = false) String pageIndex,
                            @RequestParam(value = "courseName",required = false) String courseName){
        Page<Object> pageHelper = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));//开启分页查询

        List<Course> courseList= courseService.selectCourse(courseName);//调用service的查询方法
        // 将取得的数据封装进json并返回值前台
        JSONObject json = new JSONObject();

        json.put("code", 0);
        json.put("msg", "");
        json.put("count", pageHelper.getTotal());

        JsonConfig config=new JsonConfig(); config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        json.put("courseList", JSONArray.fromObject(courseList,config));

        return json.toString();
    }

    /*
     * @Description:根据id删除信息
     * @param 需要删除的数据的主键id
     * @return 返回至页面
     */
    @RequestMapping("/delCourse.do")
    public String delBlock(@RequestParam(value = "courseId", required = false) String courseId){
        if (courseId != null) {
            courseService.delCourse(Integer.parseInt(courseId));//调用service层的删除方法
        }
        return "admin/course";
    }

    /**
     * 批量删除课程信息
     * @param ids
     * @return
     */
    @RequestMapping(value = "delCourses.do")
    @ResponseBody
    public String delCourses(@RequestParam("ids") String ids){
        String[] course_ids=ids.split(",");
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        int j=0;
        for (int i=0;i<course_ids.length;i++){
            if(course_ids[i]!=null){
                courseService.delCourse(Integer.parseInt(course_ids[i]));
                j++;
                json.put("data",j);
            }
        }
        return json.toString();
    }

    /*
     * @Description:插入数据
     * @param 需要插入的数据信息
     * @return 返回影响的条数
     */
    @RequestMapping("/insertCourse.do")
    @ResponseBody
    public String insertCourse(@RequestParam("courseName") String courseName,
                               @RequestParam("tagId") Integer tagId,
                               @RequestParam("onlineDate") String onlineDate,
                               @RequestParam("courseDesc") String courseDesc,
                               @RequestParam("teacherId") Integer teacherId,

                               @RequestParam("coursePrice") Integer coursePrice,
                               @RequestParam("courseHour") Integer courseHour,
                               @RequestParam("courseEasy") Integer courseEasy) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf.parse(onlineDate);
        Course course=new Course(null,courseName,tagId,date,courseDesc,teacherId,null,coursePrice,courseHour,courseEasy);
//        System.out.println("course-->"+course);
        int i=courseService.insert(course);//调用service层的插入方法

        //新建json对象并将一系列参数封装入json发送至前台
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", 1);

        json.put("data", i);
        return json.toString();
    }

    /*
     * @Description:根据id查询数据信息 做回显使用
     * @param  需要查询的数据的主键
     * @return 返回至页面
     */
    @RequestMapping("/uploadCourse.do")
    public String selectByPrimaryKey(@RequestParam("courseId") String courseId, Model model) throws ParseException {
        if (courseId != null) {
            Course course = courseService.selectByPrimaryKey(Integer.parseInt(courseId));

            //时区日期转换
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//            String time=sdf.format(course.getOnlineDate().getTime());
//            Date online=sdf.parse(time);

            model.addAttribute("course", course);
        }
        return "admin/uploadCourse";
    }

    /*
     * @Description:修改课程信息
     * @param 修改后的数据
     * @return 返回影响条数
     */
    @RequestMapping("/updateCourse.do")
    @ResponseBody
    public String updateByPrimaryKey(@RequestParam("courseId") Integer courseId,
                                     @RequestParam("courseName") String courseName,
                                     @RequestParam("tagId") Integer tagId,
                                     @RequestParam("onlineDate") String onlineDate,
                                     @RequestParam("courseDesc") String courseDesc,
                                     @RequestParam("teacherId") Integer teacherId,
                                     @RequestParam("coursePrice") Integer coursePrice,
                                     @RequestParam("courseHour") Integer courseHour,
                                     @RequestParam("courseEasy") Integer courseEasy) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf.parse(onlineDate);

        Course course = new Course(courseId, courseName, tagId, date, courseDesc, teacherId, null, coursePrice, courseHour, courseEasy);//将接受的参数封装为对象
        int i = courseService.updateCourse(course);
        //新建json对象 并将参数封装入json发送至前台
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", 1);

        json.put("data", i);
        return json.toString();
    }

    /**
     * 导入数据
     * @param file 从前台接收的需要导入的数据
     * @return
     * @throws Exception 抛出总异常
     */
    @RequestMapping("/uploadToCourse.do")
    @ResponseBody
    public String uploadToCourse(@RequestParam MultipartFile file) throws Exception{

        if (file==null){
            return null;
        }
        List<Course> courseList=new ArrayList<Course>();

        if (file.getOriginalFilename().endsWith(".xlsx")){//判断excel文件版本，版本不同执行不同的操作
            XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet=workbook.getSheetAt(0);
            /**
             * 通常第一行都是标题，所以从第二行 开始读取数据
             */
            for (int i=1;i<=sheet.getLastRowNum();i++){
                XSSFRow row=sheet.getRow(i);
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                Course course=new Course();
                course.setCourseName(row.getCell(1).getStringCellValue());
                course.setTagId(Integer.valueOf(row.getCell(2).getStringCellValue()));
                course.setOnlineDate(Timestamp.valueOf(row.getCell(3).getStringCellValue()));
                course.setCourseDesc(row.getCell(4).getStringCellValue());
                course.setTeacherId(Integer.valueOf(row.getCell(5).getStringCellValue()));
                course.setCoursePrice(Integer.valueOf(row.getCell(6).getStringCellValue()));
                course.setCourseHour(Integer.valueOf(row.getCell(7).getStringCellValue()));
                course.setCourseEasy(Integer.valueOf(row.getCell(8).getStringCellValue()));
                courseList.add(course);
            }
        }else  if (file.getOriginalFilename().endsWith(".xls")){
            HSSFWorkbook book=new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet=book.getSheetAt(0);
            /**
             * 通常第一行都是标题，所以从第二行 开始读取数据
             */
            for (int i=1;i<sheet.getLastRowNum()+1;i++){
                HSSFRow row=sheet.getRow(i);
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                Course course=new Course();
                course.setCourseName(row.getCell(1).getStringCellValue());
                course.setTagId(Integer.valueOf(row.getCell(2).getStringCellValue()));
                course.setOnlineDate(Timestamp.valueOf(row.getCell(3).getStringCellValue()));
                course.setCourseDesc(row.getCell(4).getStringCellValue());
                course.setTeacherId(Integer.valueOf(row.getCell(5).getStringCellValue()));
                course.setCoursePrice(Integer.valueOf(row.getCell(6).getStringCellValue()));
                course.setCourseHour(Integer.valueOf(row.getCell(7).getStringCellValue()));
                course.setCourseEasy(Integer.valueOf(row.getCell(8).getStringCellValue()));
                courseList.add(course);
            }
        }
        int j=0;
        for (int i=0;i<courseList.size();i++){
            //System.out.println(tagList.get(i));
            j=courseService.insert(courseList.get(i));
        }

        JSONObject json=new JSONObject();
        json.put("code",j);
        json.put("msg","");
        json.put("count",1);
        return json.toString();
    }

    /**
     * 导出数据
     * @param ids 选中的id拼接的字符串
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/outputExcel.do")
    public void outputExcel(@RequestParam("ids") String ids, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String[] title ={"课程ID","课程名称","标签ID","上线日期","课程描述","教师ID","课程价格","课时","难易程度"};

        PrintWriter out = response.getWriter();
        File fi=new File("F:/Excel"+ new Date().getTime() +".xls");

    /*    //乱码处理
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("multipart/form-data");
        resp.addHeader("Content-Disposition", "attachment;filename=fileName"+".xls");
*/
        String[] courseIds=ids.split(",");
        List<Course> courseList=new ArrayList<Course>();//将选中的数据从数据库中读出
        for (int j=0;j<courseIds.length;j++){
            courseList.add(courseService.selectByPrimaryKey(Integer.parseInt(courseIds[j])));
        }
        for (int a=0;a<courseList.size();a++){
            System.out.println(courseList.get(a));
        }

        //创建Excel工作簿
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建一个工作表
        HSSFSheet sheet=workbook.createSheet();
        //创建第一行
        HSSFRow row=sheet.createRow(0);

        HSSFCell cell=null;

        for (int i = 0; i < title.length; i++) {
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }

        //追加数据
        for (int i = 1; i <= courseList.size(); i++) {
            HSSFRow nextrow = sheet.createRow(i);
            HSSFCell cell2 = nextrow.createCell(0);
            cell2.setCellValue(courseList.get(i-1).getCourseId());
            cell2=nextrow.createCell(1);
            cell2.setCellValue(courseList.get(i-1).getCourseName());
            cell2=nextrow.createCell(2);
            cell2.setCellValue(courseList.get(i-1).getTagId());

            // 日期格式转为字符串输出
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String OnlineDate= sdf.format(courseList.get(i-1).getOnlineDate().getTime());
            cell2=nextrow.createCell(3);
            cell2.setCellValue(OnlineDate);

            cell2=nextrow.createCell(4);
            cell2.setCellValue(courseList.get(i-1).getCourseDesc());
            cell2=nextrow.createCell(5);
            cell2.setCellValue(courseList.get(i-1).getTeacherId());
//            cell2=nextrow.createCell(6);
//            cell2.setCellValue(courseList.get(i-1).getImgAdd());
            cell2=nextrow.createCell(6);
            //判断
//            String CoursePrice=null;
//            if(courseList.get(i-1).getCoursePrice()==0){
//                CoursePrice="免费";
//            }
//            if(courseList.get(i-1).getCoursePrice()==1){
//                CoursePrice="普通会员";
//            }
//            if(courseList.get(i-1).getCoursePrice()==2){
//                CoursePrice="高级会员";
//            }
            cell2.setCellValue(courseList.get(i-1).getCoursePrice());
            cell2=nextrow.createCell(7);
            cell2.setCellValue(courseList.get(i-1).getCourseHour());
            cell2=nextrow.createCell(8);
            //判断
//            String CourseEasy=null;
//            if(courseList.get(i-1).getCourseEasy()==0){
//                CourseEasy="简单";
//            }
//            if(courseList.get(i-1).getCourseEasy()==1){
//                CourseEasy="一般";
//            }
//            if(courseList.get(i-1).getCourseEasy()==2){
//                CourseEasy="较难";
//            }
            cell2.setCellValue(courseList.get(i-1).getCourseEasy());
//            cell2=nextrow.createCell(10);
        }

        //创建JSON对象
        JSONObject json = new JSONObject();
        try {
            fi.createNewFile();
            FileOutputStream stream= FileUtils.openOutputStream(fi);
            workbook.write(stream);
            stream.close();
            json.put("exl",1);

            //获取Excel保存路径
            String path = fi.getAbsolutePath();
            json.put("path1",path);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("exl",0);
        }
        out.print(json);

    }
}
