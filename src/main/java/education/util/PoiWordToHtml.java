package education.util;


import fr.opensagres.poi.xwpf.converter.core.BasicURIResolver;
import fr.opensagres.poi.xwpf.converter.core.FileImageExtractor;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

//import org.apache.poi.xwpf.converter.core.BasicURIResolver;
//import org.apache.poi.xwpf.converter.core.FileImageExtractor;
//import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
//import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;

/**
 * 在线预览，将Word转换成HTML
 * @author xulibin
 */
public class PoiWordToHtml {


    /**
     * doc转换html
     * @param fileName 文件名称，绝对路径下的
     * @param path1 绝对路径下的存储路径
     * @return 绝对路径下的存储路径
     * @throws IOException
     */
    public static void docToHtml(String fileName,String path1) throws Throwable {
        final String path =fileName.substring(0,fileName.lastIndexOf("/"));                     //"D:\\wordToHtml\\";
        final String file = fileName.substring(fileName.lastIndexOf("/"));                                         //"Test.doc";
        InputStream input = new FileInputStream(path + file);

        //看不同的版本，doc转Html
        HWPFDocument wordDocument = new HWPFDocument(input);
        //看不同的版本，docx转Html
        //XWPFDocument wordDocument=new XWPFDocument(input);

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);

        wordToHtmlConverter.setPicturesManager(new PicturesManager(){
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                return suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        List pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                try {
                    pic.writeImageContent(new FileOutputStream(path
                            + pic.suggestFullFileName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();
        String content = new String(outStream.toByteArray());
        FileUtils.writeStringToFile(new File(path, "Test.html"), content, "utf-8");
    }


    /**
     * docx转换html
     * @param fileName 文件名称，绝对路径下的
     * @param path 绝对路径下的存储路径
     * @return 绝对路径下的存储路径
     * @throws IOException
     */
    public static String docxToHtml(String fileName,String path) throws IOException {
        XWPFDocument docxDocument = new XWPFDocument(new FileInputStream(fileName));
        // 配置
        XHTMLOptions options = XHTMLOptions.create();
        // 设置图片存储路径
        //path =  "D:\\IdeaData\\project\\ssm\\src\\main\\webapp\\admin\\onlinefiles";      // System.getProperty("java.io.tmpdir");
        String firstImagePathStr = path + "/" + String.valueOf(System.currentTimeMillis());
        options.setExtractor(new FileImageExtractor(new File(firstImagePathStr)));
        options.URIResolver(new BasicURIResolver(firstImagePathStr));
        // 转换html
        ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(docxDocument, htmlStream, options);
        String htmlStr = htmlStream.toString();
        // 将image文件转换为base64并替换到html字符串里
        String middleImageDirStr = "/word/media";
        String imageDirStr = firstImagePathStr + middleImageDirStr;
        File imageDir = new File(imageDirStr);
        String[] imageList = imageDir.list();
        if (imageList != null) {
            for (int i = 0; i < imageList.length; i++) {
                String oneImagePathStr = imageDirStr + "/" + imageList[i];
                File oneImageFile = new File(oneImagePathStr);
                String imageBase64Str = new String(Base64.encodeBase64(FileUtils.readFileToByteArray(oneImageFile)), "UTF-8");
                htmlStr = htmlStr.replace(oneImagePathStr, "data:image/png;base64," + imageBase64Str);
            }
        }
        //删除图片路径
        File firstImagePath = new File(firstImagePathStr);
        FileUtils.deleteDirectory(firstImagePath);
        System.out.println("htmlStr-->"+htmlStr);
        FileUtils.writeStringToFile(new File(path, "Test.jsp"), htmlStr, "utf-8");
        return htmlStr;
    }

    public static void main(String[] args) {
        String fileName="D:\\wordToHtml\\Test.docx";
        String path =  "D:\\IdeaData\\project\\ssm\\src\\main\\webapp\\admin\\onlinefiles";
        String htmlStr="";
        try {
             htmlStr = docxToHtml(fileName,path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static void main(String arg[]) throws Exception {
//        WordToHtml test = new WordToHtml();
//        System.out.println(test.docxToHtml("F://舆情分析.docx").toString());
//
//    }

}