package file;

/**
 * Created by zhaoxu on 2017/7/7.
 */
public class FileFactory {


    public FileCommonUtil createFile(String fileName){
        if (fileName.endsWith("xml")){
            return new FileXmlUtil(fileName);
        }else if(fileName.endsWith("xls")){
            return new FileExcelUtil(fileName);
        }else if(fileName.endsWith("xlsx")){
            return new FileExcelUtil(fileName);
        }else if(fileName.endsWith("json")){
            return new FileJsonUtil();
        }else if(fileName.endsWith("txt")){
            return new FileTxtUtil();
        }else{
            return new FileCommonUtil();
        }
    }
    public void transformFile(String fromFileName,String toFileName){
        if (toFileName.endsWith("xml")){
            new FileXmlUtil(toFileName).writeFileContent(createFile(fromFileName).readFileContent());
        }else if (toFileName.endsWith("xlsx")){
            new FileExcelUtil(toFileName).writeFileContent(createFile(fromFileName).readFileContent());
        }else if (toFileName.endsWith("xls")){
            new FileExcelUtil(toFileName).writeFileContent(createFile(fromFileName).readFileContent());
        }
    }
}
