package file;

/**
 * Created by zhaoxu on 2017/7/6.
 */
public abstract class FileUtil {

    protected String fileName;
    protected String fileType;
    protected String filePath;
    protected FileContent fileContent;

    /**
     *
     * @return
     */
    public abstract FileContent readFileContent();

    /**
     *
     * @return
     */
    public abstract FileUtil writeFileContent(FileContent fileContent);


}
