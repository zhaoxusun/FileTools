package file;

/**
 * Created by zhaoxu on 2017/7/7.
 */
public class Test {

    public void testFileUtil(){
        FileFactory fileFactory = new FileFactory();

        //fileFactory.transformFile("test2.xls","test.xls");
        fileFactory.transformFile("test13.txt","test.xlsx");


    }

    public static void main(String [] args){

        new Test().testFileUtil();

    }
}

