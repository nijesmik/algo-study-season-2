package jechul;

import java.util.HashMap;
import java.util.Map;

class 도넛과막대그래프 {
	static int[] answer = {0, 0, 0, 0};
	
    public int[] solution(int[][] edges) {

        Map<Integer, int[]> map = new HashMap<>();
        
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            map.putIfAbsent(a, new int[2]);
            map.putIfAbsent(b, new int[2]);

            map.get(a)[0]++;
            map.get(b)[1]++;
        }

        for (int key : map.keySet()) {
            int[] Cnt = map.get(key);

            if (Cnt[0] >= 2 && Cnt[1] == 0) {
                answer[0] = key;
            }
            
            else if (Cnt[0] == 0 && Cnt[1] > 0) {
                answer[2]++;
            }
            
            else if (Cnt[0] >= 2 && Cnt[1] >= 2) {
                answer[3]++;
            }
        }

        answer[1] = (map.get(answer[0])[0] - answer[2] - answer[3]);

        return answer;
    }
}
