package education.contorller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import education.pojo.Block;
import education.service.BlockService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ Created
 * @ Description: 对板块信息进行操作的controller层
 * @ Author: 倪绍帅
 */
@Controller
public class BlockController {
    @Resource
    private BlockService blockService;


    /**
     * 查询板块信息
     * @param pageSize  分页查询每页最大记录数
     * @param pageIndex 分页查询当前页码
     * @return 返回json数据
     */
    @RequestMapping("/blockList.do")
    @ResponseBody
    public String blockList(@RequestParam(value = "limit", required = false) String pageSize,
                            @RequestParam(value = "page", required = false) String pageIndex,
                            @RequestParam(value = "blockName",required = false) String blockName){
        Page<Object> pageHelper = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));//开启分页查询

        List<Block> blockList= blockService.selectBlocks(blockName);//调用service的查询方法
        // 将取得的数据封装进json并返回值前台
        JSONObject json = new JSONObject();

        json.put("code", 0);
        json.put("msg", "");
        json.put("count", pageHelper.getTotal());

        json.put("blockList", JSONArray.fromObject(blockList));

        return json.toString();
    }

    /*
     * @Description:根据id删除信息
     * @author nss
     * @param 需要删除的数据的主键id
     * @return 返回至页面
     */
    @RequestMapping("/delBlock.do")
    public String delBlock(@RequestParam(value = "blockId", required = false) String blockId){
        if (blockId != null) {
            blockService.delBlock(Integer.parseInt(blockId));//调用service层的删除方法
        }
        return "block";
    }

    /*
     * @Description:插入数据
     * @author nss
     * @param 需要插入的数据信息
     * @return 返回影响的条数
     */
    @RequestMapping("/insertBlock.do")
    @ResponseBody
    public String insert(@RequestParam("blockName") String blockName,
                         @RequestParam("editorValue") String blockDesc){
        Block block=new Block(null,blockName,blockDesc,null,null);//将接受的参数封装为 对象
        int i=blockService.insert(block);//调用service层的插入方法

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
     * @author nss
     * @param  需要查询的数据的主键
     * @return 返回至页面
     */
    @RequestMapping("/uploadBlock.do")
    public String selectByPrimaryKey(@RequestParam("blockId") String blockId, Model model){
        if (blockId != null) {
            Block block = blockService.selectByPrimaryKey(Integer.parseInt(blockId));
            model.addAttribute("block", block);
        }
        return "admin/uploadBlock";
    }

    /*
     * @Description:修改标签信息
     * @author nss
     * @param 修改后的数据
     * @return 返回影响条数
     */
    @RequestMapping("/updateBlock.do")
    @ResponseBody
    public String updateByPrimaryKey(@RequestParam("blockId") int blockId,
                                     @RequestParam("blockName") String blockName,
                                     @RequestParam("editorValue") String blockDesc,
                                     @RequestParam("blockOrder") int blockOrder,
                                     @RequestParam("blockVisibility") int blockVisibility) {
        Block block = new Block(blockId,blockName,blockDesc,blockOrder,blockVisibility);//将接受的参数封装为对象
        int i = blockService.updateBlock(block);
        //新建json对象 并将参数封装入json发送至前台
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", 1);

        json.put("data", i);
        return json.toString();
    }
    /**
     * 批量删除
     * @param ids 前台传来的id集合的字符串
     * @return
     */
    @RequestMapping("/delSumBlock.do")
    @ResponseBody
    public String delSumTag(@RequestParam("ids") String ids){
        String[] blockIds=ids.split(",");
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        int j=0;
        for (int i=0;i<blockIds.length;i++){
            if (blockIds[i]!=null){
                blockService.delBlock(Integer.parseInt(blockIds[i]));
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
    @RequestMapping("/downloadBlockToExcel.do")
    public void downloadTagToExcel(@RequestParam("ids") String ids, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String[] blockIds=ids.split(",");
        List<Block> blockList=new ArrayList<Block>();//将选中的数据从数据库中读出
        for (int j=0;j<blockIds.length;j++){
            blockList.add(blockService.selectByPrimaryKey(Integer.parseInt(blockIds[j])));
        }
        for (int a=0;a<blockList.size();a++){
            System.out.println(blockList.get(a));
        }
        String[] headers ={"板块id","板块名","板块描述","板块排序","板块显示属性"};
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
        for (int k=0;k<blockList.size();k++){
            XSSFRow nextrow = sheet.createRow(k+1);
            XSSFCell cell2=nextrow.createCell(0);
            cell2.setCellValue(blockList.get(k).getBlockId());

            cell2=nextrow.createCell(1);
            cell2.setCellValue(blockList.get(k).getBlockName());

            cell2=nextrow.createCell(2);
            cell2.setCellValue(blockList.get(k).getBlockDesc());

            cell2=nextrow.createCell(3);
            cell2.setCellValue(blockList.get(k).getBlockOrder());

            cell2=nextrow.createCell(4);
            cell2.setCellValue(blockList.get(k).getBlockVisibility());

        }
        response.reset(); //清除buffer缓存
        //设置表格名
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", new Date().getTime()+"_BlockData.xlsx");
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        workbook.write(response.getOutputStream());

    }
}
