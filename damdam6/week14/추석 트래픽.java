import java.util.*;

class Solution {
    public int solution(String[] lines) {
       
        ArrayList<traffic> tArr = new ArrayList<>();
        
        for(int i=0;i<lines.length;i++){
            int hour = Integer.parseInt(lines[i].substring(11,13));
            int minute = Integer.parseInt(lines[i].substring(14,16));
            int second = Integer.parseInt(lines[i].substring(17,19));
            int milisecond = Integer.parseInt(lines[i].substring(20,23));
            int end = hour * 3600*1000 + minute * 60*1000+minute*1000+milisecond;
            int time = (int)(Double.parseDouble(lines[i].substring(24, lines[i].length() - 1)) * 1000);
            
            //System.out.println(time);
            tArr.add(new traffic(end-time+1,end));
           // System.out.println(end);
        }
        
 
        
        int answer = 0;
        
        
        
        return answer;
    }
    
    private static class traffic{
        int start;
        int end;
        traffic(int start, int end){
            this.start = start;
            this.end = end;
    
        }
    }
}