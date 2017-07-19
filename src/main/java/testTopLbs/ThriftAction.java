package testTopLbs;

import com.travel.top.lbs.client.request.TopLbsRequest;
import com.travel.top.lbs.client.response.TopLbsResponse;
import com.travel.top.lbs.client.service.TopLbsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
* Created by autoframe
 */
public class ThriftAction{
private static TopLbsService toplbsservice;

static {
ApplicationContext context=new ClassPathXmlApplicationContext("toplbsservice-thrift.xml");
toplbsservice=(TopLbsService)context.getBean("toplbsservice-client");
 }

//待测接口
 public TopLbsResponse filter(TopLbsRequest toplbsrequest) throws org.apache.thrift.TException{
return toplbsservice.filter(toplbsrequest);
  }
}

