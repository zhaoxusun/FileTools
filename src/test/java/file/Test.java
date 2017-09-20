package file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaoxu on 2017/7/7.
 */
public class Test {

    public void testFileUtil(){
        FileFactory fileFactory = new FileFactory();

        fileFactory.transformFile("test.txt","test.json");
        //fileFactory.transformFile("test.xml","test2.xml");


    }

    public static void main(String [] args){

        new Test().testFileUtil();
//        String contentByRowObject = "aa\tbb\tcc\tdd\tee";
//        String [] contentByRow = contentByRowObject.split("\\t");
//        System.out.println(contentByRow[0]);

    }
}

