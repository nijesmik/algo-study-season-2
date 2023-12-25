package jechul;
import java.util.*;

public class 당구연습 {
	   static ArrayList<Integer> solution(int m, int n, int startX, int startY, int[][] balls) {
	        ArrayList<Integer> answer = new ArrayList<>();

	        for (int[] ball : balls) {
	            int ans = Integer.MAX_VALUE;

	            if (startX != ball[0] || ball[1] > startY) {
	                ans = Math.min(ans, (startX - ball[0]) * (startX - ball[0]) + (startY + ball[1]) * (startY + ball[1]));
	            }

	            if (startY != ball[1] || ball[0] > startX) {
	                ans = Math.min(ans, (startX + ball[0]) * (startX + ball[0]) + (startY - ball[1]) * (startY - ball[1]));
	            }

	            if (startX != ball[0] || ball[1] < startY) {
	                ans = Math.min(ans, (startX - ball[0]) * (startX - ball[0]) + (2 * n - ball[1] - startY) * (2 * n - ball[1] - startY));
	            }

	            if (startY != ball[1] || ball[0] < startX) {
	                ans = Math.min(ans, (2 * m - ball[0] - startX) * (2 * m - ball[0] - startX) + (startY - ball[1]) * (startY - ball[1]));
	            }

	            answer.add(ans);
	        }

	        return answer;
	    }
	}
