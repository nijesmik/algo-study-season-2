import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_12904_A와B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] target = br.readLine().toCharArray();
        char[] arr = br.readLine().toCharArray();
        int len = arr.length;
        int head = 0;
        int tail = len - 1;
        int order = 0;
        while (len > target.length) {
            if (arr[tail] == 'A') {
                if (order == 0) {
                    tail--;
                } else {
                    tail++;
                }
                len--;
            } else {
                if (order == 0) {
                    tail--;
                } else {
                    tail++;
                }
                int tmp = head;
                head = tail;
                tail = tmp;
                len--;
                order ^= 1;
            }
        }
        if (order == 0) {
            for (int i = 0; i < target.length; i++) {
                if (target[i] != arr[head + i]) {
                    System.out.println(0);
                    return;
                }
            }
        } else {
            for (int i = 0; i < target.length; i++) {
                if (target[i] != arr[head - i]) {
                    System.out.println(0);
                    return;
                }
            }
        }
        System.out.println(1);
        br.close();
    }
}
