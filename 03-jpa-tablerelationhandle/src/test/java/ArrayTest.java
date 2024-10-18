import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Scanner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ArrayTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入n与m的值");
        int m = sc.nextInt();
        int a[] = {15, 3, 76, 68, 84, 87, 13, 67, 47, 34, 45};
//        for (int i = 0; i < n; i++) {
//            int number = sc.nextInt();
//            a[i] = number;
//        }
        for (int i = 0; i < m; i++) {
            int temp = a[a.length - 1];
            for (int j = a.length - 1; j > 0; j--) {
                a[j] = a[j - 1];
            }
            a[0] = temp;
        }
        System.out.println(Arrays.toString(a)
        );
    }
}
