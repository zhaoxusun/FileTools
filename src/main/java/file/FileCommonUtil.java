package file;

/**
 * Created by zhaoxu on 2017/7/6.
 */
public class FileCommonUtil extends FileUtil{

    public FileCommonUtil(String fileName){
        this.fileName = fileName;
        filePath = System.getProperty("user.dir")+"/data/";
        fileType = "CommonFile";
        fileContent = new FileContent();
    }

    public FileCommonUtil() {
        filePath = System.getProperty("user.dir")+"/data/";
        fileType = "CommonFile";
        fileContent = new FileContent();
    }


    public FileContent readFileContent() {
        System.out.println("File type is not support,please ensure Filetype is one of txt,xls,xlsx,json,xml.");
        return null;
    }

    public FileUtil writeFileContent(FileContent fileContent) {
        System.out.println("File type is not support,please ensure Filetype is one of txt,xls,xlsx,json,xml.");
        return null;
    }


}
