package Programmers;

import java.util.*;
public class 징검다리_건너기 {
	class Solution {
	    public int solution(int[] stones, int k) {
	        // int answer = Integer.MAX_VALUE;
	        // for(int i = 0; i <= stones.length-k; i++) {
	        //     int tmpMax = stones[i];
	        //     for(int j = i; j < i+k; j++) {
	        //         if(tmpMax < stones[j]) tmpMax = stones[j];
	        //         if(tmpMax >= answer) break;
	        //     }
	        //     answer = Math.min(answer, tmpMax);
	        // }
	        // return answer;
	        
	        int answer = 0;
	        int min = 1; 
	        int max = 200000000;
		      
	        while (min <= max) {
	            int mid = (min + max) / 2;
	            
	            if (condition(stones, k, mid)) {
	                min = mid + 1;
	                answer = Math.max(answer, mid);
	            } else {
	                max = mid - 1;
	            }
	        }
	        
	        return answer;
	    }
	    
	    static boolean condition(int[] stones, int k, int num) {
	      	int cnt = 0;
	        
	        for (int stone : stones) {
	            if (stone - num < 0) cnt++;
	            else cnt = 0;
	            
	            if (cnt == k) return false;
	        }
	        
	        return true;
	    }
	}
}
