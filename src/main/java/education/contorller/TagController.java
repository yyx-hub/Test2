package education.contorller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import education.pojo.Tag;
import education.service.TagService;
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
public class TagController {
    @Resource
    private TagService tagService;


    /*
     * @Description:分页查询数据
     * @author nss
     * @return 返回json数据
     */
    @RequestMapping("/tagList.do")
    @ResponseBody
    public String tagList(@RequestParam(value = "limit", required = false) String pageSize,
                          @RequestParam(value = "page", required = false) String pageIndex,
                          @RequestParam(value = "tagnam",required = false) String tagName) {
        Page<Object> pageHelper = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));//开启分页查询
        List<Tag> tagList = tagService.tagList(tagName);//调用service的查询方法
       /* for (int i=0;i<tagList.size();i++){
            System.out.println(tagList.get(i));//测试获取的数据
        }*/
        // 将取得的数据封装进json并返回值前台
        JSONObject json = new JSONObject();

        json.put("code", 0);
        json.put("msg", "");
        json.put("count", pageHelper.getTotal());

        json.put("tagList", JSONArray.fromObject(tagList));

        return json.toString();
    }

    /*
     * @Description:根据id删除信息
     * @author nss
     * @param 需要删除的数据的主键id
     * @return 返回至页面
     */
    @RequestMapping("/delTag.do")
    public String delTag(@RequestParam(value = "tagId", required = false) String tagId) {
        if (tagId != null) {
            tagService.deleteByPrimaryKey(Integer.parseInt(tagId));//调用service层的删除方法
        }
        return "tag";
    }

    /*
     * @Description:修改标签信息
     * @author nss
     * @param 修改后的数据
     * @return 返回影响条数
     */
    @RequestMapping("/updateTag.do")
    @ResponseBody
    public String updateByPrimaryKey(@RequestParam("tagId") int tagid,
                                     @RequestParam("tagnam") String tagName,
                                     @RequestParam("editorValue") String tagDesc) {
        Tag tag = new Tag(tagid, tagName, tagDesc);//将接受的参数封装为对象
        int i = tagService.updateByPrimaryKey(tag);
        //新建json对象并将一系列参数封装入json发送至前台
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", 1);

        json.put("data", i);
        return json.toString();
    }


    /*
     * @Description:根据id查询数据信息
     * @author nss
     * @param  需要查询的数据的主键
     * @return 返回至页面
     */
    @RequestMapping("/uploadTag.do")
    public String selectByPrimaryKey(@RequestParam("tagId") String tagId, Model model) {
        if (tagId != null) {
            Tag tag = tagService.selectByPrimaryKey(Integer.parseInt(tagId));
            model.addAttribute("tag", tag);
        }
        return "admin/uploadTag";
    }

    /*
     * @Description:插入数据
     * @author nss
     * @param 需要插入的数据信息
     * @return 返回影响的条数
     */
    @RequestMapping("/insertTag.do")
    @ResponseBody
    public String insert(@RequestParam("tagnam") String tagName,
                         @RequestParam("editorValue") String tagDesc) {
        Tag tag = new Tag(null, tagName, tagDesc);//将接受的参数封装为 对象
        int i = tagService.insert(tag);//调用service层的插入方法
        //新建json对象并将一系列参数封装入json发送至前台
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
    @RequestMapping("/uploadToTag.do")
    @ResponseBody
    public String uploadToTag(@RequestParam MultipartFile file) throws Exception{

        if (file==null){
            return null;
        }
        List<Tag> tagList=new ArrayList<Tag>();

        if (file.getOriginalFilename().endsWith(".xlsx")){//判断excel文件版本，版本不同执行不同的操作
            XSSFWorkbook book=new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet=book.getSheetAt(0);
            /**
             * 通常第一行都是标题，所以从第二行 开始读取数据
             */
            for (int i=1;i<sheet.getLastRowNum()+1;i++){
                XSSFRow row=sheet.getRow(i);
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                Tag tag=new Tag();
                tag.setTagName(row.getCell(0).getStringCellValue());
                tag.setTagDesc(row.getCell(1).getStringCellValue());
                tagList.add(tag);
            }
        }else  if (file.getOriginalFilename().endsWith(".xls")){
            HSSFWorkbook book=new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet=book.getSheetAt(0);
            /**
             * 通常第一行都是标题，所以从第二行 开始读取数据
             */
            for (int i=1;i<sheet.getLastRowNum()+1;i++){
                HSSFRow row=sheet.getRow(i);
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                Tag tag=new Tag();
                tag.setTagName(row.getCell(0).getStringCellValue());
                tag.setTagDesc(row.getCell(1).getStringCellValue());
                tagList.add(tag);
            }
        }
        int j=0;
        for (int i=0;i<tagList.size();i++){
            //System.out.println(tagList.get(i));
            j=tagService.insert(tagList.get(i));
        }

        JSONObject json=new JSONObject();
        json.put("code",j);
        json.put("msg","");
        json.put("count",1);
        return json.toString();
    }

    /**
     * 批量删除
     * @param ids 前台传来的id集合的字符串
     * @return
     */
    @RequestMapping("/delSumTag.do")
    @ResponseBody
    public String delSumTag(@RequestParam("ids") String ids){
        String[] tagIds=ids.split(",");
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        int j=0;
        for (int i=0;i<tagIds.length;i++){
            if (tagIds[i]!=null){
                tagService.deleteByPrimaryKey(Integer.parseInt(tagIds[i]));
                j++;
                json.put("data",j);
            }
        }
        return json.toString();
    }

    /**
     * 文件导出
     * @param ids 选中的id拼接的字符串
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/downloadTagToExcel.do")
    public void downloadTagToExcel(@RequestParam("ids") String ids, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String[] tagIds=ids.split(",");
        List<Tag> tagList=new ArrayList<Tag>();//将选中的数据从数据库中读出
        for (int j=0;j<tagIds.length;j++){
            tagList.add(tagService.selectByPrimaryKey(Integer.parseInt(tagIds[j])));
        }
        for (int a=0;a<tagList.size();a++){
            System.out.println(tagList.get(a));
        }
        String[] headers ={"标签ID","标签名","标签描述"};
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet();
        //创建第一行的对象，第一行一般用来填充标题内容，从第二行开始才是数据
        XSSFRow row=sheet.createRow(0);
        //为表头添加数据
        for (int i=0;i<headers.length;i++){
            XSSFCell cell=row.createCell(i);
            XSSFRichTextString textString=new XSSFRichTextString(headers[i]);
            cell.setCellValue(textString);
        }
        //遍历集合，将集合中的数据添加到表格中
        for (int k=0;k<tagList.size();k++){
            XSSFRow nextrow = sheet.createRow(k+1);
            XSSFCell cell2=nextrow.createCell(0);
            cell2.setCellValue(tagList.get(k).getTagId());

            cell2=nextrow.createCell(1);
            cell2.setCellValue(tagList.get(k).getTagName());

            cell2=nextrow.createCell(2);
            cell2.setCellValue(tagList.get(k).getTagDesc());
        }
        response.reset(); //清除buffer缓存
        //设置表格名
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", new Date().getTime()+"_TagData.xlsx");
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        workbook.write(response.getOutputStream());

    }

    /*
     * 查询数据
     */
    @RequestMapping("/selectList.do")
    public void selectList(Tag tag,HttpServletResponse response) {
        List<Tag> selectList= tagService.selectTagName(tag);//调用service层的查询方法
        //新建json对象并将一系列参数封装入json发送至前台
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", 50);

        json.put("data", JSONArray.fromObject(selectList));

        OutUtil.print(json,response);
    }

}
