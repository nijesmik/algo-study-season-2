package Programmers;

public class 광고_삽입 {
	class Solution {
	    public String solution(String play_time, String adv_time, String[] logs) {
	        String answer = "";
	        int[] time = new int[360000];
	        
	        int total = timeToInt(play_time);
	        int adv = timeToInt(adv_time);
	        
	        for(int i = 0; i < logs.length; i++) {
	            String[] tmp = logs[i].split("-");
	            int s = timeToInt(tmp[0]);
	            int e = timeToInt(tmp[1]);
	            
	            for(int j = s; j < e; j++) {
	                time[j]++;
	            }
	        }
	        
	        // 누적합 생각하기 어렵다..
	        
	        long tmpMax = 0;
	        int mIdx = 0;
	        for(int i = 0; i < adv; i++) {
	            tmpMax += time[i];
	            mIdx = 0;
	        }
	        long max = tmpMax;
	        for(int i = adv; i < total; i++) {
	            tmpMax += time[i] - time[i-adv];
	            if(tmpMax > max) {
	                max = tmpMax;
	                mIdx = i - adv + 1;
	            }
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        int h = mIdx/3600;
	        int m = (mIdx - h*3600) / 60;
	        int s = (mIdx - h*3600 - m * 60);
	        
	        if((h/10)==0) sb.append(0).append(h).append(":");
	        else sb.append(h).append(":");
	        if((m/10)==0) sb.append(0).append(m).append(":");
	        else sb.append(m).append(":");
	        if((s/10)==0) sb.append(0).append(s);
	        else sb.append(s);
	        
	        answer = sb.toString();
	        return answer;
	    }
	    
	    static int timeToInt(String t) {
	        String[] tmp = t.split(":");
	        return Integer.parseInt(tmp[0])*3600 + Integer.parseInt(tmp[1])*60 + Integer.parseInt(tmp[2]);
	    }
	}
}
