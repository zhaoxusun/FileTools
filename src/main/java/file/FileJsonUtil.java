package file;

/**
 * Created by zhaoxu on 2017/7/6.
 */
public class FileJsonUtil extends FileCommonUtil {
    public FileJsonUtil(String fileName) {
        this.fileName = fileName;
        this.fileContent = new FileContent();
        this.fileType = "Json";
    }
}
