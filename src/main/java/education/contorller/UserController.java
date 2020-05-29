package education.contorller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import education.pojo.User;
import education.service.UserService;
import education.util.SmsDemo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 用户controller层
 * @author xulibin
 */
//@SessionAttributes(value= {"codeNum"},types = {String.class})
@Controller
public class UserController {

    @Resource
    private UserService us;


    /**
     *验证手机号码
     * @return 返回查询到的记录数，json字符串
     */
    @RequestMapping(value = "userLogin")
    @ResponseBody
    public String userLogin(@RequestParam(value = "phone" ,required = false) String phone,
                            @RequestParam(value = "password" ,required = false) String password){
        System.out.println("phone-->"+phone);
        User user=new User();
        if(password!=null && phone!=null){
            user=us.userLogin(phone,password);
        }

        JSONObject json=new JSONObject();
        json.put("data",user);

        return json.toString();
    }

    @RequestMapping(value = "userRegister")
    @ResponseBody
    public String userRegister(@RequestParam(value = "phone" ,required = false) String phone){
        System.out.println("phone-->"+phone);
        User user=new User();
        String name=""+(int)((Math.random()*9+1)*100000)+"user";
        user.setUser_nickname(name);
        user.setUser_password("123456");
        user.setUser_img("/admin/userimg/xidada.jpg");
        user.setUser_name(name);
        user.setVip(0);
        user.setUser_state(0);
        user.setUser_phone(phone);

        int i = us.insertUser(user);
        System.out.println("i-->"+i);

        JSONObject json=new JSONObject();
        json.put("data",i);
        return json.toString();
    }


    /**
     * 验证手机验证码，并删除
     * @param codeNum
     * @param request
     * @return
     */
    @RequestMapping(value = "checkCode")
    @ResponseBody
    public String checkCode(@RequestParam(value = "codeNum" ,required = false) String codeNum, HttpServletRequest request){
        System.out.println("checkCode_codeNum-->"+codeNum);
        String data="";
        //清除SessionAttribute里的参数
        //sessionStatus.setComplete("codeNum");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("codeNum2");
        System.out.println("session_codeNum-->"+code);

        session.removeAttribute("codeNum2");

       // session.getAttribute("codeNum");
        if(codeNum!=null && codeNum.equals(code)){
            data="OK";
        }

        JSONObject json=new JSONObject();
        json.put("data",data);
        return json.toString();
    }


    /**
     *获取验证吗
     * @return 返回查询到的记录数，json字符串
     */
    @RequestMapping(value = "getCode")
    @ResponseBody
    public String getCode(@RequestParam(value = "phone" ,required = false) String phone, HttpServletRequest request){
        System.out.println("phone-->"+phone);
        //Map<String,String> map=new HashMap<String, String>();

        SendSmsResponse response=null;
        //通过随机数产生验证码
        String codeNum=""+(int)((Math.random()*9+1)*100000);
        System.out.println("codeNum-->"+codeNum);
        //map.put("codeNum",codeNum);
        //model.addAttribute("codeNum",codeNum);
        request.getSession().setAttribute("codeNum2",codeNum);

        String code="";
//        try {
//            response=SmsDemo.sendSms(phone,codeNum);
//            code=response.getCode();
//            System.out.println("短信接口返回的数据----------------");
//            System.out.println("Code=" + response.getCode());
//            System.out.println("Message=" + response.getMessage());
//            System.out.println("RequestId=" + response.getRequestId());
//            System.out.println("BizId=" + response.getBizId());
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }

        code="OK";
        JSONObject json=new JSONObject();
        json.put("data",code);
        return json.toString();
    }


    /**
     *验证手机号码
     * @return 返回查询到的记录数，json字符串
     */
    @RequestMapping(value = "checkPhone")
    @ResponseBody
    public String checkPhone(@RequestParam(value = "phone" ,required = false) String phone){
        //System.out.println("phone-->"+phone);

        int i=0;
        i=us.checkPhone(phone);
        //System.out.println("i-->"+i);
        JSONObject json=new JSONObject();
        json.put("data",i);
        return json.toString();
    }


    /**
     *分页查询
     * @param pageSize 每页记录数
     * @param pageIndex 页码下标
     * @param username 用户名称，组合查询
     * @return 返回查询到的记录数，json字符串
     */
    @RequestMapping(value = "userPage")
    @ResponseBody
    public String userPage(@RequestParam(value = "limit" ,required = false) String pageSize,
                           @RequestParam(value ="page",required = false) String pageIndex,
                           @RequestParam(value ="username",required = false) String username,
                           @RequestParam(value ="userphone",required = false) String userphone){
//        System.out.println("pageIndex"+pageIndex);
        //分页查询所有用户信息
        Page<Object> page = PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        List<User> userList=us.queryUserList(username,userphone);
//        for (int i=0;i<userList.size();i++){
//            System.out.println("yonghu"+userList.get(i));
//        }

        JSONObject json=new JSONObject();

        json.put("code",0);
        json.put("msg","");
        json.put("count",page.getTotal());

        json.put("userList", JSONArray.fromObject(userList));

        return json.toString();
    }

    /**
     * 根据ID删除用户
     * @param userid 用户ID
     * @return 返回的页面
     */
    @RequestMapping(value = "delUser")
    @ResponseBody
    public String delUser(@RequestParam(value = "userid" ,required = false) String userid){
//        if(userid!=null){ }
//        return "users";
        int i=us.deleteUserById(Integer.parseInt(userid));
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 添加用户信息
     * @param user 用户信息
     * @return
     */
    @RequestMapping(value = "insertUser")
    @ResponseBody
    public String insertUser(User user){
        System.out.println(""+user);
        int i=us.insertUser(user);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 根据用户ID查询用户信息，做修改的回显
     * @param user_id 用户ID
     * @param model 存放用户信息
     * @return
     */
    @RequestMapping(value = "uploadUser")
    public String uploadUser(@RequestParam("user_id") String user_id, Model model){
        //System.out.println("user_id"+user_id);
       if(user_id!=null){
           User user=us.queryUserById(Integer.parseInt(user_id));
           model.addAttribute("user",user);
           System.out.println("upload-->"+user);
       }
        return "admin/uploaduser";
    }

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return
     */
    @RequestMapping(value = "updateUser")
    @ResponseBody
    public String updateUser(User user){
        int i=us.update(user);
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);

        json.put("data",i);
        return json.toString();
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @RequestMapping(value = "delUsers")
    @ResponseBody
    public String delUsers(@RequestParam("ids") String ids){
        String[] user_ids=ids.split(",");
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        int j=0;
        for (int i=0;i<user_ids.length;i++){
            if(user_ids[i]!=null){
                us.deleteUserById(Integer.parseInt(user_ids[i]));
                j++;
                json.put("data",j);
            }
        }
        return json.toString();
    }

    /**
     * 批量导入数据
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "uploadExcel")
    @ResponseBody
    public String uploadExcel(@RequestParam MultipartFile file) throws Exception{

        //System.out.println("request-->"+request);
        //System.out.println("file-->"+file);
        String fileOldName = null;
        String fileNewName = null;

        //判断文件是否为空
        if(file.isEmpty()){
            return null;
        }
        fileOldName=file.getOriginalFilename();
        //System.out.println("fileOldName-->"+fileOldName);

        //将文件更改一个新名字
        fileNewName= UUID.randomUUID().toString()+"_"+fileOldName;
        System.out.println("fileNewName-->"+fileNewName);
        int i=0;
        try {
            //将文件上传到本地
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File("D:\\IdeaData\\project\\ssm\\src\\main\\webapp\\admin\\excels\\"+fileNewName));

            String path="D:\\IdeaData\\project\\ssm\\src\\main\\webapp\\admin\\excels\\"+fileNewName;
            //path="D:\\WPS\\data\\工作簿1.xlsx";
            List<String> list = read(path);

            for(String s: list){
                //System.out.println(s);
                String[] str=s.split(";");
                User user=new User();
                if(str[0]!=null){
                    user.setUser_id(Integer.parseInt(str[0]));
                }
                user.setUser_name(str[1]);
                user.setUser_nickname(str[2]);
                user.setUser_img(str[3]);
                if(str[4]!=null){
                    user.setUser_state(Integer.parseInt(str[4]));
                }
                user.setUser_phone(str[5]);
                user.setUser_email(str[6]);
                user.setUser_password(str[7]);
                if(str[8]!=null){
                    user.setVip(Integer.parseInt(str[8]));
                }

                //调用service层的增加用户方法
                i=us.insertUser(user);
                System.out.println("i-->"+i);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json=new JSONObject();
        json.put("code",i);
        json.put("msg","");
        json.put("count",1);
        return json.toString();
    }
    //将Excel数据封装成String类型的List
    private List<String> read(String path) {
        String s="";
        List<String> list = new ArrayList<String>();
        File file = new File(path);
        //判断文件是否存在
        if (!file.exists()) {
            System.out.println("文件不存在");
        }
        try {
            //新建一个Excel2007之前用HSSFWorkbook，其格式为 .xsl
            //新建一个Excel2007之后用XSSFWorkbook，其格式为 .xslx
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum() + 1;
            // System.out.println("rowLength-->"+rowLength);
            //工作表的列
            Row row = sheet.getRow(0);
            //System.out.println("row-->"+row);
            //总列数
            int colLength = row.getLastCellNum();
            //System.out.println("colLength-->"+colLength);
            //得到指定的单元格
            Cell cell = row.getCell(0);
            //System.out.println("cell-->"+cell);
            //得到单元格样式
            //CellStyle cellStyle = cell.getCellStyle();
            for (int i = 1; i < rowLength; i++) {
                row = sheet.getRow(i);
                for (int j = 0; j < colLength; j++) {
                    cell = row.getCell(j);
                    //System.out.println("cell2-->"+cell);
                    //Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：
                    //Cannot get a STRING value from a NUMERIC cell
                    //将所有的需要读的Cell表格设置为String格式
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        s+=cell.getStringCellValue()+";";
                    }else {
                        s+=""+";";
                    }


                }
                list.add(s);
                s="";
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        //返回这个集合
        return list;
    }

    /**
     * 导出数据到Excel
     * @return
     */
    @RequestMapping(value = "downloadExcel")
    @ResponseBody
    public String downloadExcel(@RequestParam("ids") String ids){
        System.out.println("ids-->"+ids);
        String[] userid=ids.split(",");

        String[] title ={"用户ID","用户名","用户昵称","用户头像","用户状态","电话号码","用户邮箱",
                "用户密码","vip"};

        File fi=new File("D:/excels/"+ new Date().getTime() +"_userData.xlsx");

        //创建Excel工作簿 office版本2007及以上
        XSSFWorkbook workbook=new XSSFWorkbook();
        //创建一个工作表
        XSSFSheet sheet=workbook.createSheet();
        //创建第一行
        XSSFRow row=sheet.createRow(0);

        XSSFCell cell=null;//列

        for (int i = 0; i < title.length; i++) {
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }

        for (int i=0;i<userid.length;i++){
            if(userid[i]!=null){
                User user = us.queryUserById(Integer.parseInt(userid[i]));
                //System.out.println("user-->"+user);
                //追加数据

                XSSFRow nextrow = sheet.createRow(i+1);

                XSSFCell cell2 = nextrow.createCell(0);
                cell2.setCellValue(user.getUser_id());

                cell2=nextrow.createCell(1);
                cell2.setCellValue(user.getUser_name());

                cell2=nextrow.createCell(2);
                cell2.setCellValue(user.getUser_nickname());

                cell2=nextrow.createCell(3);
                cell2.setCellValue(user.getUser_img());

                cell2=nextrow.createCell(4);
                cell2.setCellValue(user.getUser_state());

                cell2=nextrow.createCell(5);
                cell2.setCellValue(user.getUser_phone());

                cell2=nextrow.createCell(6);
                cell2.setCellValue(user.getUser_email());

                cell2=nextrow.createCell(7);
                cell2.setCellValue(user.getUser_password());

                cell2=nextrow.createCell(8);
                cell2.setCellValue(user.getVip());

                //String date= sdf.format(kompanios.get(i).getK_date().getTime());
                //cell2.setCellValue(date);
                //cell2=nextrow.createCell(9);
                // 日期格式转为字符串输出
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //String time= sdf.format(kompanios.get(i).getK_begintime().getTime());
                //cell2.setCellValue(time);
                //cell2=nextrow.createCell(10);

                //String time1= sdf.format(kompanios.get(i).getK_endtime().getTime());
                //cell2.setCellValue(time1);
                //cell2=nextrow.createCell(11);
            }
        }

        //创建JSON对象
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);
        try {
            fi.createNewFile();
            FileOutputStream stream= FileUtils.openOutputStream(fi);
            workbook.write(stream);
            stream.close();
            json.put("code",1);

            //获取Excel保存路径
            String path = fi.getAbsolutePath();
            json.put("excelPath",path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json.toString();
    }

    /**
     * 修改头像
     * @param imgData
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam(value = "imgData" ,required = false) String imgData,
                              @RequestParam(value = "userid" ,required = false) String userid) throws Exception{
        System.out.println("imgData-->"+imgData);
        System.out.println("userid-->"+userid);
        //创建JSON对象
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",1);


        String path="D:/IdeaData/project/ssm/src/main/webapp/";
        String imgName="admin/userimg/"+ new Date().getTime()+"_userimg.png";
       //相对路径咋用

        if(userid!=null && !"".equals(userid)){
            User user=us.queryUserById(Integer.parseInt(userid));
            // 将dataURL开头的非base64字符删除
            String base64 = imgData.substring(imgData.indexOf(",") + 1);
            System.out.println("base64-->"+base64);
            FileOutputStream write = new FileOutputStream(new File(path +imgName));
            byte[] decoderBytes = Base64.getDecoder().decode(base64);
            System.out.println("decoderBytes-->"+decoderBytes);

            user.setUser_img(imgName);
            int i = us.update(user);
            System.out.println("i-->"+i);

            write.write(decoderBytes);
            write.close();

            json.put("code",i);
        }
        return json.toString();
    }


}
