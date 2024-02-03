package nijesmik.week09.표_편집;

import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmds) {
        Stack<Integer> stack = new Stack();
        PriorityQueue<Integer> less = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> greater = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < k; i++) {
            less.add(i);
        }
        for (int i = k + 1; i < n; i++) {
            greater.add(i);
        }

        for (String cmd : cmds) {
            char c = cmd.charAt(0);
            if (c == 'U') {
                int u = Integer.parseInt(cmd.split(" ")[1]);
                while (u-- > 0) {
                    greater.add(k);
                    k = less.poll();
                }
            } else if (c == 'D') {
                int d = Integer.parseInt(cmd.split(" ")[1]);
                while (d-- > 0) {
                    less.add(k);
                    k = greater.poll();
                }
            } else if (c == 'Z') {
                int restore = stack.pop();
                if (k < restore) {
                    greater.add(restore);
                } else {
                    less.add(restore);
                }
            } else if (c == 'C') {
                stack.push(k);
                if (greater.isEmpty()) {
                    k = less.poll();
                } else {
                    k = greater.poll();
                }
            }
        }

        boolean[] deleted = new boolean[n];
        for (int idx : stack) {
            deleted[idx] = true;
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
