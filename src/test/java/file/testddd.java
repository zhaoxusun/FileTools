package file;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhaoxu on 2017/7/18.
 */
public class testddd {


    public static void main(String[] args) {
        HashMap<Integer,ArrayList> map = new HashMap<Integer, ArrayList>();


        ArrayList list = new ArrayList();
        for (int n=0;n<1;n++) {
            for (int i = 0; i < 3; i++) {
                list.add(i);
            }
            map.put(n,list);
            System.out.println(map.get(0));
            list.clear();
            System.out.println(map.get(0));
        }
    }
}
