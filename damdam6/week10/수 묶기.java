package y24Feb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baek1744 {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        List<Integer> plus = new ArrayList<>();
        List<Integer> minus = new ArrayList<>();
        int zero = 0;
        int oneCount = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(bf.readLine());
            if (num > 1) {
                plus.add(num);
            } else if (num == 1) {
                oneCount++;
            } else if (num == 0) {
                zero++;
            } else {
                minus.add(num);
            }
        }

        Collections.sort(plus, Collections.reverseOrder());
        Collections.sort(minus);

        int sum = 0;

        int plusLength = plus.size();
        for (int i = 0; i < plusLength; i += 2) {
            if (i + 1 < plusLength) {
                sum += plus.get(i) * plus.get(i + 1);
            } else {
                sum += plus.get(i);
            }
        }

        int minusLength = minus.size();
        for (int i = 0; i < minusLength; i += 2) {
            if (i + 1 < minusLength) {
                sum += minus.get(i) * minus.get(i + 1);
            } else {
                if (zero == 0) { // 0이 없을 때만 마지막 음수를 더함
                    sum += minus.get(i);
                }
            }
        }

        sum += oneCount; //1은 곱하는게 손해

        System.out.println(sum);
    }
}
