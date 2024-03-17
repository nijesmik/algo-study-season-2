package nijesmik.week12.표현_가능한_이진트리;

import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int N = numbers.length;
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = test(Long.toBinaryString(numbers[i]));
        }
        return answer;
    }

    int test(String num) {
        int len = 1;
        while (len < num.length()) {
            len = len * 2 + 1;
        }
        StringBuilder sb = new StringBuilder();
        while (num.length() < len) {
            sb.append('0');
            len--;
        }
        sb.append(num);

        Stack<String> stack = new Stack<>();
        stack.add(sb.toString());
        while (!stack.isEmpty()) {
            String cur = stack.pop();
            if (cur.length() == 1 || Long.parseLong(cur, 2) == 0) {
                continue;
            }
            int mid = cur.length() / 2;
            if (cur.charAt(mid) == '0') {
                return 0;
            }
            stack.add(cur.substring(0, mid));
            stack.add(cur.substring(mid + 1));
        }
        return 1;
    }
}
