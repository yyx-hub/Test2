package education.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 封装打印Json类
 */
public class OutUtil {
    /**
     * 打印方法
     */
    public static void print(Object o, HttpServletResponse response){
//        设置编码格式
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
        try{
            out=response.getWriter();
            out.print(o);
            out.flush();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
