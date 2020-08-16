package file;


import org.testng.annotations.Test;

/**
 * Created by zhaoxu on 2017/7/7.
 */
public class Test1 {

    @Test
    public void testFileUtil(){
        FileFactory fileFactory = new FileFactory();
        //tool's entry
        fileFactory.transformFile("test.xls","test222.json");
    }
}

