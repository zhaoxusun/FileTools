package file;

/**
 * Created by zhaoxu on 2017/7/6.
 */
public class FileXmlUtil extends FileCommonUtil {
    public FileXmlUtil(String fileName) {
        this.fileName = fileName;
        fileContent = null;
        fileType = "xml";
    }
}
