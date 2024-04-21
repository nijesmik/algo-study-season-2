
import java.util.*;
import java.io.*;

public class Main {

    static String shorten;
    static String[] ipArr;

    static String result;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        shorten = bf.readLine(); 

        if (shorten.contains("::")) {
            shorten = shorten.replace("::", ":xxxx:");
        }

        ipArr = shorten.split(":");


        ArrayDeque<String> resultList = new ArrayDeque<>();

        ArrayDeque<String> shortenList = new ArrayDeque<>(Arrays.asList(ipArr));

        for (String str : shortenList) {
            if (str.isEmpty()) continue;

            while (str.length() < 4) {
                str = "0" + str;
            }
            resultList.add(str);
        }


        String[] ans = new String[8];

        int zeroLen = 8 - resultList.size() + 1;
        int idx = 0;
        for (String current : resultList) {
            if (current.equals("xxxx")) {
                while (zeroLen-- > 0) {
                    ans[idx++] = "0000";
                }
            } else {
                ans[idx++] = current;
            }
        }

        StringBuilder sb = new StringBuilder(ans[0]);
        for (int i = 1; i < ans.length; i++) {
            sb.append(":").append(ans[i]);
        }

        System.out.println(sb);
    }
}
