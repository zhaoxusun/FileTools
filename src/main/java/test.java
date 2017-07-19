import org.testng.annotations.Test;

/**
 * Created by zhaoxu on 2017/6/30.
 */
public class test {

    @Test
    public void func_1(){
        String a = new String("1");
        String b = new String("1");
        System.out.println(a==b);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
    @Test
    public void func_2(){
        String a = "1";
        String b = "1";
        System.out.println(a==b);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
    @Test
    public void func_3(){
        String a = new String("1");
        String b = new String("1");
        System.out.println(a.equals(b));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

    }
    @Test
    public void func_4(){
        String a = new String ("1");
        String b = "1";
        System.out.println(a==b);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
    @Test
    public void fun_5(){
        String a = "1";
        String b = "2";
        
    }
}
