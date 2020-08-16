package file;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;

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
        if (new File(filePath + fileName).exists()) {

        }else {
            System.out.println("File not exist,please ensure file is exist at frist");
            System.exit(0);
        }
        if (fileName.endsWith("json")) {
            try {
                FileReader fileReader = new FileReader(new File(filePath+fileName));
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                try {
                    String jsonContent = "";
                    String jsonContentTemp = null;
                    while ((jsonContentTemp = bufferedReader.readLine())!=null) {
                        jsonContent = jsonContent + jsonContentTemp.replaceAll("\t","");
                    }
                    //--------------------------------jsonContent为json串－－－－－－－－－－－－－－－－－
                    ArrayList arrayKeyList ;
                    ArrayList arrayList ;
                    JSONArray jsonArray = JSONArray.parseArray(jsonContent);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    arrayKeyList = new ArrayList();
                    Set<String> jsonObjectKeySet = jsonObject.keySet();
                    for (String string:jsonObjectKeySet){
                        arrayKeyList.add(string);
                    }
                    fileContent.addContentBodyInfo(0,arrayKeyList);

                    for (int i=0;i<jsonArray.size();i++){
                        arrayList = new ArrayList();
                        jsonObject = jsonArray.getJSONObject(i);
                        Iterator iterator = jsonObjectKeySet.iterator();
                        while (iterator.hasNext()){
                            arrayList.add(jsonObject.get(iterator.next().toString()));
                        }
                        fileContent.addContentBodyInfo(i+1,arrayList);
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
                    jsonArray.add(i-1,jsonObject);
                }
                String jsonContent = jsonArray.toString();
                jsonContent = jsonContent.replaceAll("\\{","{\n\t");
                jsonContent = jsonContent.replaceAll("}","\n\t}");
                jsonContent = jsonContent.replaceAll("\\[","[\n");
                jsonContent = jsonContent.replaceAll("]","\n]");
                jsonContent = jsonContent.replaceAll(",",",\n\t");
                jsonContent = jsonContent.replaceFirst("\\{","\t{");
                bufferedWriter.write(jsonContent);
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
