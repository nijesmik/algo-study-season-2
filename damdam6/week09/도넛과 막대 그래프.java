import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] edge : edges) {
            int outNode = edge[0];
            int inNode = edge[1];

            if (!map.containsKey(outNode)) {
                map.put(outNode, new int[]{0, 0});
            }
            map.get(outNode)[0]++;

            if (!map.containsKey(inNode)) {
                map.put(inNode, new int[]{0, 0});
            }
            map.get(inNode)[1]++;
        }

        int[] res = new int[4];
        int keyWithMaxGive = -1;
       for (Integer key : map.keySet()) {
            int[] io = map.get(key);
            int give = io[0];
            int receive = io[1];

            if (give >= 2 && receive == 0) {
                keyWithMaxGive = key;
            } else if (give == 0) {
                res[2]++;
            } else if (give >= 2 && receive >= 2) {
                res[3]++;
            }
        }

        if (keyWithMaxGive != -1) {
            res[1] = map.get(keyWithMaxGive)[0] - res[2] - res[3];
            res[0] = keyWithMaxGive;
        }

        return res;
    }

}
