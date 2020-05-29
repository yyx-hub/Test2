package education.contorller;

import education.util.PoiWordToHtml;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 文件控制层
 * @author xulibin
 */
@Controller
public class FileController {

    @RequestMapping("onlineFile")
    public String onlineFile(){
        String fileName="D:\\IdeaData\\project\\ssm\\src\\main\\webapp\\admin\\onlinefiles\\Test.docx";
        String path =  "D:\\IdeaData\\project\\ssm\\src\\main\\webapp\\admin\\onlinefiles";
        //String path =fileName.substring(0,fileName.lastIndexOf("/"));                     //"D:\\wordToHtml\\";
        //String file = fileName.substring(fileName.lastIndexOf("/"));
        //System.out.println("path-->"+path);
        //System.out.println("file-->"+file);

        String reader = this.getClass().getClassLoader().getResource("/").getPath();
        System.out.println("reader-->"+reader);

        if(fileName!=null && fileName.endsWith(".docx")){
            try {
                PoiWordToHtml.docxToHtml(fileName,path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "admin/onlineFiles/Test";
    }
}
