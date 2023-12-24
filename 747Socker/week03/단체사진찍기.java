
import java.util.*;

class Solution {
    static String[] datas;
    static HashMap<Character, Integer> map;
    static int[] arr;
    static boolean[] chk;
    static int answer;

    public int solution(int n, String[] data) {
        datas = data;
        map = new HashMap<>();
        arr = new int[8];
        chk = new boolean[8];
        answer = 0;

        map.put('A', 0);
        map.put('C', 1);
        map.put('F', 2);
        map.put('J', 3);
        map.put('M', 4);
        map.put('N', 5);
        map.put('R', 6);
        map.put('T', 7);

        dfs(0);
        return answer;
    }

    public static void dfs(int idx) {
        if (idx == 8 && sol()) {
            answer++;
        } else {
            for (int i = 0; i < 8; i++) {
                if (!chk[i]) {
                    chk[i] = true;
                    arr[idx] = i;
                    dfs(idx + 1);
                    chk[i] = false;
                }
            }
        }
    }

    public static boolean sol() {
        int a;
        int b;
        int res;
        char op;

        for (String s : datas) {
            a = arr[map.get(s.charAt(0))];
            b = arr[map.get(s.charAt(2))];
            op = s.charAt(3);
            res = s.charAt(4) - '0' + 1;

            if (op == '=') {
                if (Math.abs(a - b) != res)
                    return false;
            } else if (op == '>') {
                if (Math.abs(a - b) <= res)
                    return false;
            } else {
                if (Math.abs(a - b) >= res)
                    return false;
            }
        }
        return true;
    }
}
