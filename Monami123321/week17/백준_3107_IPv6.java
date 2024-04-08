import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_3107_IPv6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        int cnt = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (arr[i] == ':') {
                ++cnt;
            }
        }
        int emit = (8 - cnt) * 4;

        char[] res = new char[32];
        int prev = 0;
        int index = 0;
        int resCnt = 0;
        while (index <= len) {
            if (index == len || arr[index] == ':') {
                if (index - prev == 4) {
                    for (int i = 0; i < 4; i++) {
                        res[resCnt++] = arr[prev + i];
                    }
                    index++;
                    prev = index;
                } else if (index == prev && index != 0 && index != len) {
                    for (int i = 0; i < emit; i++) {
                        res[resCnt++] = '0';
                    }
                    index++;
                    prev = index;
                } else {
                    for (int i = 0; i < 4 - (index - prev); i++) {
                        res[resCnt++] = '0';
                    }
                    for (int i = 0; i < index - prev; i++) {
                        res[resCnt++] = arr[prev + i];
                    }
                    index++;
                    prev = index;
                }
            } else {
                index++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resCnt; i++) {
            sb.append(res[i]);
            if (i % 4 == 3) {
                sb.append(':');
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.print(sb);
        br.close();
    }
}
