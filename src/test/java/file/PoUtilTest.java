package file;

/**
 * author: zhaoxu
 * date: 2020/4/17 下午12:28
 */
public class PoUtilTest {



    public static void main(String[] args) {
        PoUtil poUtil = new PoUtil();
        System.out.println(poUtil.poToSql(PoTest.class));
    }
}
