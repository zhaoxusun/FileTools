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
        return null;
    }

    public FileUtil writeFileContent(FileContent fileContent) {
        return null;
    }


}
