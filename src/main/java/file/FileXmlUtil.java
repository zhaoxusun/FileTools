package file;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by zhaoxu on 2017/7/6.
 */
public class FileXmlUtil extends FileCommonUtil {
    public FileXmlUtil(String fileName) {
        this.fileName = fileName;
        fileContent = new FileContent();
        fileType = "xml";
    }

    public FileXmlUtil() {
        super();
    }

    @Override
    public FileContent readFileContent() {
        if (new File(filePath + fileName).exists()) {

        }else {
            try {
                new File(filePath+fileName).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }if (fileName.endsWith("xml")) {
            BaseXMLData xmlData = new BaseXMLData();
            Object[] contentKey = xmlData.getDataKey( filePath + this.fileName, 0);
            ArrayList contentKeyList = new ArrayList();
            for (int i = 0; i < contentKey.length; i++) {
                contentKeyList.add(contentKey[i]);
            }
            fileContent.addContentBodyInfo(0, contentKeyList);
            fileContent.setContentName(xmlData.caseName);
            Object[][] content = new BaseXMLData().getData( filePath + fileName);
            ArrayList contentList;
            for (int row = 0; row < content.length; row++) {
                contentList = new ArrayList();
                for (int column = 0; column < content[0].length; column++) {
                    contentList.add(content[row][column]);
                }
                fileContent.addContentBodyInfo(row + 1, contentList);
            }
        }
        return fileContent;
    }
    public Object[] getContentKey(){
        return new BaseXMLData().getDataKey(this.filePath+this.fileName,0);
    }
    @Override
    public FileCommonUtil writeFileContent(FileContent fileContent) {
        if (new File(filePath + fileName).exists()) {

        }else {
            try {
                new File(filePath+fileName).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileName.endsWith("xml")) {
            FileWriter fileWriter = null;
            String contentName = fileContent.getContentName();
            if (contentName == null){
                contentName = "test";
            }
            //xml文件header
            String header = "<?xml version = \"1.0\" encoding = \"UTF-8\"?>\n";
            header = header + "\t<cases>\n";
            header = header + "\t\t<case name=\""+contentName+"\">\n";
            //xml文件footer
            String footer = "\t\t</case>\n";
            footer = footer + "\t</cases>\n";

            try {
                fileWriter = new FileWriter(filePath + fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                ArrayList keyList = fileContent.getContentBody().get(0);
                int rowCount = fileContent.getContentBody().size();
                int columnCount = fileContent.getContentBody().get(0).size();
                bufferedWriter.write(header);
                for (int rowNum=1;rowNum<rowCount;rowNum++){
                    bufferedWriter.write("\t\t\t<row>\n");
                    for (int columnNum=0;columnNum<columnCount;columnNum++) {
                        bufferedWriter.write("\t\t\t\t<data key=\""+keyList.get(columnNum).toString()+"\" value=\""+fileContent.getContentBody().get(rowNum).get(columnNum).toString()+"\"/>\n");
                    }
                    bufferedWriter.write("\t\t\t</row>\n");
                }
                bufferedWriter.write(footer);
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {

        }
        return this;
    }
}
