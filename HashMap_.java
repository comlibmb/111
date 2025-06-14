package class01;

import java.util.HashMap;
import sun.misc.VM;
/**
 * @author pacai
 * @version 1.0
 */
public class HashMap_ {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        String s1 = "hello";
        String s2 = "world";
        System.out.println(VM.isBooted());

        hashMap.put(s1, s2);
    }
}
