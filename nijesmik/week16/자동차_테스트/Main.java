package nijesmik.week16.자동차_테스트;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int carSize = sc.nextInt();
        int querySize = sc.nextInt();

        int[] cars = new int[carSize];
        for (int i = 0; i < carSize; i++) {
            cars[i] = sc.nextInt();
        }
        Arrays.sort(cars);

        StringBuilder sb = new StringBuilder();
        while (querySize-- > 0) {
            int idx = Arrays.binarySearch(cars, sc.nextInt());
            if (idx > 0 && idx < carSize - 1 && carSize >= 3) {
                sb.append(idx * (carSize - idx - 1));
            } else {
                sb.append(0);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
