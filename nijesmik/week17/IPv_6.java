package nijesmik.week17;
import java.util.*;

public class IPv_6 {
    static StringBuilder sb;

    public static void main(String[] args) {
        StringBuilder front = new StringBuilder();
        StringBuilder rear = new StringBuilder();
        sb = front;

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        int N = input.length();
        int length = 0;

        for (int i = 0; i < N; i++) {
            if (input.charAt(i) == ':') {
                if (length > 0) {
                    sb.append(':');
                }
                sb = rear;
                continue;
            }
            if (i > 0) {
                sb.append(':');
            }
            int start = i;
            while (i < N && input.charAt(i) != ':') {
                i++;
            }
            append(input.substring(start, i));
            length++;
        }
        sb = front;
        appendZero(8 - length);
        System.out.print(front);
        System.out.print(rear);
    }

    static void appendZero(int cnt) {
        if (cnt == 0) {
            return;
        }
        while (cnt-- > 1) {
            sb.append("0000:");
        }
        sb.append("0000");
    }

    static void append(String address) {
        int cnt = 4 - address.length();
        while (cnt-- > 0) {
            sb.append(0);
        }
        sb.append(address);
    }
}