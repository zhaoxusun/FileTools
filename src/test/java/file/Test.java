package file;

/**
 * Created by zhaoxu on 2017/7/7.
 */
public class Test {

    public void testFileUtil(){
        FileFactory fileFactory = new FileFactory();

        fileFactory.transformFile("test2.xml","test.json");
        //fileFactory.transformFile("test.xml","test2.xml");


    }

    public static void main(String [] args){

        new Test().testFileUtil();

    }
}

