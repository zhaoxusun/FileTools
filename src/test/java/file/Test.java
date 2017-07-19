package file;

/**
 * Created by zhaoxu on 2017/7/7.
 */
public class Test {

    public void testFileUtil(){
        FileFactory fileFactory = new FileFactory();
        //FileCommonUtil fileCommonUtil = fileFactory.createFile("test.xlsx");

        fileFactory.transformFile("test2.xls","test.xls");

    }

    public static void main(String [] args){
        new Test().testFileUtil();
    }
}

