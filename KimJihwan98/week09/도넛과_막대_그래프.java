package Programmers;

import java.util.*;
public class 도넛과_막대_그래프 {
	class Solution {
	    public int[] solution(int[][] edges) {
	        int[] answer = new int[4];
	        Map<Integer, int[]> hm = new HashMap<>();
	        for(int[] edge : edges) {
	            int a = edge[0];
	            int b = edge[1];
	            if(!hm.containsKey(a)) hm.put(a, new int[] {0,0}); //여기서 0번째는 나가는 1번째는 들어오는
	            if(!hm.containsKey(b)) hm.put(b, new int[] {0,0});
	            hm.get(a)[0]++;
	            hm.get(b)[1]++;
	        }
	        
	        for(Integer key : hm.keySet()) {
	            if(hm.get(key)[0]>=2 && hm.get(key)[1] == 0) answer[0] = key;
	            else if(hm.get(key)[0] == 2 && hm.get(key)[1] >= 2) answer[3]++;
	            else if(hm.get(key)[0] == 0) answer[2]++;
	        }
	        answer[1] = hm.get(answer[0])[0] - answer[2] - answer[3];
	        
	        return answer;
	    }
	}
}
