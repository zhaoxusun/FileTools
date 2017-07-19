package testTopLbs.testCase;


import com.alibaba.fastjson.JSON;
import com.travel.top.lbs.client.request.TopLbsRequest;
import com.travel.top.lbs.client.response.TopLbsResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testTopLbs.ThriftAction;
import util.FileUtil;

import java.util.List;

/**
* Created by AutoCode
*/
public class Testfilter{
    @DataProvider(name = "filterData")
    public static Object[][] filterData() throws Exception {
       Object[][] ob;
       String casePath = System.getProperty("user.dir") + "/src/main/java/testTopLbs/data/";
       String caseTargt = "filterrequest.json";
       List<String> caseFile=FileUtil.findJsonData(casePath,caseTargt);
       ob=new Object[caseFile.size()][];
       for (int i=0;i<caseFile.size();i++){
            String jsonString=FileUtil.txt2String(caseFile.get(i));
            TopLbsRequest request=new TopLbsRequest();
            try {
                 request=JSON.parseObject(jsonString,TopLbsRequest.class);
            }catch (Exception e){
                e.printStackTrace();
            }
            ob[i]=new Object[]{request};
       }
       return ob;
   }

       @Test(dataProvider = "filterData")
       public void testfilter(TopLbsRequest request){
           System.out.println("=====request=====");
           System.out.println(request.toString());
           TopLbsResponse toplbsresponse=new TopLbsResponse();
           try {
                System.out.println("===== 已运行=====");
                toplbsresponse =new ThriftAction().filter(request);
           }catch (Exception te){
               te.printStackTrace();
           }
           System.out.println("=====response=====");
           System.out.println(toplbsresponse.toString());
   }
}

