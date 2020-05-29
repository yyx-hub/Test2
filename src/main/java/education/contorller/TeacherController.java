package education.contorller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import education.pojo.Tag;
import education.pojo.Teachers;
import education.service.TagService;
import education.service.TeacherService;
import education.util.OutUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ Created
 * @ Description:对tag进行操作的类
 * @ Author:倪绍帅
 */
@Controller
public class TeacherController {
    @Resource
    private TeacherService teacherService;

    /*
     * 查询数据
     */
    @RequestMapping("/selectTList.do")
    public void selectTList(Teachers teachers, HttpServletResponse response) {
        List<Tag> selectTList= teacherService.selectTeachersName(teachers);//调用service层的查询方法
        //新建json对象并将一系列参数封装入json发送至前台
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", 50);

        json.put("data", JSONArray.fromObject(selectTList));

        OutUtil.print(json,response);
    }

}
