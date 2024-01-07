package Programmers;

import java.util.*;
public class 보석_쇼핑 {
	class Solution {
	    static HashMap<String, Integer> hm;
	    public int[] solution(String[] gems) {
	        int[] answer = new int[2];
	        HashSet<String> set = new HashSet<>();
	        for(String s : gems) {
	            set.add(s);
	        }
	        int kind = set.size();
	        
	        hm = new HashMap<>();
	        
	        int start = 0;
	        int end = Integer.MAX_VALUE;
	        int left = 0;
	        int right = 0;
	        for(int i = 0; i < gems.length; i++) {
	            hm.put(gems[i], hm.getOrDefault(gems[i],0) + 1);
	            right = i;
	            
	            while(hm.get(gems[left]) > 1) {
	                hm.put(gems[left], hm.get(gems[left])-1);
	                if(hm.get(gems[left]) == 0) hm.remove(gems[left]);
	                
	                left++;
	            }
	            
	            if(kind == hm.size() && (end-start) > (right-left)) {
	                end = right+1;
	                start = left+1;
	            }
	            
	        }
	        answer[0] = start;
	        answer[1] = end;
	        // 어렵다
	        
	        
	        return answer;
	    }
	}
}
