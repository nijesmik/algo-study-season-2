package nijesmik.week03.단체사진_찍기;

import java.util.*;

class Solution {
    int[] friends;
    int ans;
    String[] data;
    Map<Character, Integer> map;

    public int solution(int n, String[] data) {
        friends = new int[8];
        ans = 0;
        this.data = data;
        map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('F', 2);
        map.put('J', 3);
        map.put('M', 4);
        map.put('N', 5);
        map.put('R', 6);
        map.put('T', 7);

        dfs(0, 0);
        return ans;
    }

    void test() {
        for (String condition : data) {
            int idx1 = map.get(condition.charAt(0));
            int idx2 = map.get(condition.charAt(2));
            char inq = condition.charAt(3);
            int dist = condition.charAt(4) - '0';
            if (!testOne(idx1, idx2, inq, dist)) {
                return;
            }
        }
        ans++;
    }

    boolean testOne(int idx1, int idx2, char inq, int conditionalDist) {
        int actualDist = Math.abs(friends[idx1] - friends[idx2]) - 1;
        if (inq == '=') {
            return (actualDist == conditionalDist);
        }
        if (inq == '<') {
            return (actualDist < conditionalDist);
        }
        // if (inq == '>')
        return (actualDist > conditionalDist);
    }

    void dfs(int fidx, int visited) {
        if (fidx == 8) {
            test();
            return;
        }

        for (int pos = 0; pos < 8; pos++) {
            if ((visited >> pos & 1) > 0) continue;
            friends[fidx] = pos;
            dfs(fidx + 1, visited | 1 << pos);
        }
    }
}

