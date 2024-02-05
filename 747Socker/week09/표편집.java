import java.util.*;

public class Solution {
    
    public String solution(int n, int k, String[] cmd) {
        boolean[] deleted = new boolean[n]; 
        Stack<Integer> st = new Stack<>(); 
        
        for (String c : cmd) {
            if (c.charAt(0) == 'U') {
                int X = Integer.parseInt(c.substring(2));
                while (X > 0) {
                    k--;
                    if (!deleted[k]) X--; 
                }
            } else if (c.charAt(0) == 'D') {
                int X = Integer.parseInt(c.substring(2));
                while (X > 0) {
                    k++;
                    if (!deleted[k]) X--; 
                }
            } else if (c.charAt(0) == 'C') {
                deleted[k] = true; 
                st.push(k); 
                
                int next = k;
                while (next < n && deleted[next]) next++;
                if (next == n) { 
                    do {
                        k--;
                    } while (deleted[k]);
                } else {
                    k = next;
                }
            } else if (c.charAt(0) == 'Z') {
                int bg = st.pop(); 
                deleted[bg] = false; 
            }
        }
        
        StringBuilder answer = new StringBuilder();
        for (boolean isDeleted : deleted) {
            answer.append(isDeleted ? "X" : "O");
        }
        
        return answer.toString();
    }
    
}
