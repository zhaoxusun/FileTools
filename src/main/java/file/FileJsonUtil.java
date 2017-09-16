package file;

import org.json.JSONArray;
import org.json.JSONObject;

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
                fileWriter = new FileWriter(new File(filePath+fileName));
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                JSONObject jsonObject ;
                JSONArray jsonArray = new JSONArray();
                ArrayList fileContentKey = fileContent.getContentBody().get(0);

                for (int i=1;i<fileContent.getContentBody().size();i++){
                    jsonObject = new JSONObject();
                    for (int j=0;j<fileContentKey.size();j++){
                        jsonObject.put(fileContentKey.get(j).toString(),fileContent.getContentBody().get(i).get(j));
                    }
                    jsonArray.put(i-1,jsonObject);
                }
                String jsonContent = jsonArray.toString();
                jsonContent = jsonContent.replaceAll(",",",\n");
                jsonContent = jsonContent.replaceAll("\\{","{\n");
                jsonContent = jsonContent.replaceAll("}","\n}");
                jsonContent = jsonContent.replaceAll("\\[","[\n");
                jsonContent = jsonContent.replaceAll("]","\n]");
                bufferedWriter.write(jsonContent);
                System.out.println("json:"+jsonArray);
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