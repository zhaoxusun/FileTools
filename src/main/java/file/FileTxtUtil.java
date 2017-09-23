package file;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zhaoxu on 2017/7/6.
 */
public class FileTxtUtil extends FileCommonUtil {
    public FileTxtUtil(String fileName) {
        this.fileName = fileName;
        this.fileContent = new FileContent();
        this.fileType = "Txt";
    }

    public FileTxtUtil() {
        super();
    }

    @Override
    public FileContent readFileContent() {
        if (new File(filePath + fileName).exists()) {

        }else {
            System.out.println("File not exist,please ensure file is exist at frist");
            System.exit(0);
        }
        if (fileName.endsWith("txt")) {
            try {
                FileReader fileReader = new FileReader(new File(filePath+fileName));
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                try {
                    String contentByRowObject = null;
                    int contentByNum = 0;
                    while((contentByRowObject=bufferedReader.readLine())!=null){
                        ArrayList contentByRow = new ArrayList(Arrays.asList(contentByRowObject.split("\\t")));
                        //每个list添加到map中
                        fileContent.addContentBodyInfo(contentByNum,contentByRow);
                        contentByNum++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return fileContent;
    }

    @Override
    public FileCommonUtil writeFileContent(FileContent fileContent) {
        File tempFile = new File(filePath+fileName);
        if (tempFile.exists()) {
            tempFile.delete();
        }
        try {
            new File(filePath+fileName).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileName.endsWith("txt")){
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(new File(filePath+fileName));
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                int rowCount = fileContent.getContentBody().size();
                int columnCount = fileContent.getContentBody().get(0).size();
                for (int rowNum=0;rowNum<rowCount;rowNum++){
                    for (int columnNum=0;columnNum<columnCount;columnNum++) {
                        bufferedWriter.write(fileContent.getContentBody().get(rowNum).get(columnNum).toString());
                        bufferedWriter.write("\t");
                    }
                    bufferedWriter.write("\n");
                }
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return this;
    }
}
