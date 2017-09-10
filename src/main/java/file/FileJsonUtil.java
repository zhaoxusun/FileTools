package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zhaoxu on 2017/7/6.
 */
public class FileJsonUtil extends FileCommonUtil {
    public FileJsonUtil(String fileName) {
        this.fileName = fileName;
        this.fileContent = new FileContent();
        this.fileType = "Json";
    }
    public FileJsonUtil() {
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
        if (fileName.endsWith("json")) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(filePath + fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArrayList keyList = fileContent.getContentBodyKey();
            for (Object key : keyList) {

            }
        }else {

        }
        return this;
    }
}
