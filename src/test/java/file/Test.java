package file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaoxu on 2017/7/7.
 */
public class Test {

    @org.testng.annotations.Test
    public void testFileUtil(){
        FileFactory fileFactory = new FileFactory();
        //tool's entry
        fileFactory.transformFile("test.xls","test4.xlsx");
    }
}

