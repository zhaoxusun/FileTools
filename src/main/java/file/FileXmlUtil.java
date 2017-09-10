package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zhaoxu on 2017/7/6.
 */
public class FileXmlUtil extends FileCommonUtil {
    public String caseName = null;
    public FileXmlUtil(String fileName) {
        this.fileName = fileName;
        fileContent = null;
        fileType = "xml";
    }

    public FileXmlUtil() {
        super();
    }

    @Override
    public FileContent readFileContent() {
        return super.readFileContent();
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
            try {
                fileWriter = new FileWriter(filePath + fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                ArrayList keyList = fileContent.getContentBody().get(0);
                int rowCount = fileContent.getContentBody().size();
                int columnCount = fileContent.getContentBody().get(0).size();

                bufferedWriter.write(getContentHeader());
                for (int rowNum=1;rowNum<rowCount;rowNum++){
                    bufferedWriter.write("\t\t\t<row>\n");
                    for (int columnNum=0;columnNum<columnCount;columnNum++) {
                        bufferedWriter.write("\t\t\t\t<data key=\""+keyList.get(columnNum).toString()+"\" value=\""+fileContent.getContentBody().get(rowNum).get(columnNum).toString()+"\"/>\n");
                    }
                    bufferedWriter.write("\t\t\t</row>\n");
                }
                bufferedWriter.write(getContentFooter());
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {

        }
        return this;
    }

    /**
     * xml文件格式需要caseName，如果为null，默认值设置为test
     * @return
     */
    public String getCaseName(){
        if (caseName == null){
            caseName = "test";
        }
        return caseName;
    }
    //xml数据头
    public String getContentHeader() throws IOException{
        String header = "<?xml version = \"1.0\" encoding = \"UTF-8\"?>\n";
        header = header + "\t<cases>\n";
        header = header + "\t\t<case name=\""+getCaseName()+"\">\n";
        return header;
    }
    //xml数据尾
    public String getContentFooter() throws IOException{
        String footer = "\t\t</case>\n";
        footer = footer + "\t</cases>\n";
        return footer;
    }
}
