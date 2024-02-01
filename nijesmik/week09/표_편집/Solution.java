package nijesmik.week09.표_편집;

import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmds) {
        boolean[] deleted = new boolean[n];
        Stack<Integer> stack = new Stack();
        for (String cmd : cmds) {
            char c = cmd.charAt(0);
            if (c == 'U') {
                int u = Integer.parseInt(cmd.split(" ")[1]);
                int cnt = 0;
                while (cnt < u) {
                    if (!deleted[--k]) {
                        cnt++;
                    }
                }
            } else if (c == 'D') {
                int d = Integer.parseInt(cmd.split(" ")[1]);
                int cnt = 0;
                while (cnt < d) {
                    if (!deleted[++k]) {
                        cnt++;
                    }
                }
            } else if (c == 'Z') {
                int idx = stack.pop();
                deleted[idx] = false;
            } else if (c == 'C') {
                deleted[k] = true;
                stack.push(k);
                int idx = k;
                while (idx < n && deleted[idx]) {
                    idx++;
                }
                if (idx == n) {
                    idx = k;
                    while (deleted[idx]) {
                        idx--;
                    }
                }
                k = idx;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (boolean del : deleted) {
            if (del) {
                sb.append('X');
            } else {
                sb.append('O');
            }
        }
        return sb.toString();
    }
}
