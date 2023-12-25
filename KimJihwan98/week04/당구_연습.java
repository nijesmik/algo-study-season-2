package Programmers;

import java.util.*;
public class 당구_연습 {
	class Solution {
	    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
	        int num = balls.length;
	        int[] answer = new int[num];
	        int[] dir = new int[4]; // 한공을 상하좌우로 대칭했을때 거리
	        
	        for(int i = 0; i < num; i++) {
	            int targetX = balls[i][0];
	            int targetY = balls[i][1];
	            
	            int moveX1 = -startX;
	            int moveX2 = 2*m-startX;
	            int moveY1 = -startY;
	            int moveY2 = 2*n-startY;
	            
	            dir[0] = getDistSquare(startX, moveY2, targetX, targetY); // 상 반전
	            dir[1] = getDistSquare(startX, moveY1, targetX, targetY); // 하 반전
	            dir[2] = getDistSquare(moveX1, startY, targetX, targetY); // 좌 반전
	            dir[3] = getDistSquare(moveX2, startY, targetX, targetY); // 우 반전
	            
	            if(startX == targetX) {
	                if(startY > targetY) dir[1] = Integer.MAX_VALUE; // 하 반전 불가
	                else dir[0] = Integer.MAX_VALUE; // 상 반전 불가
	            } else if(startY == targetY) {
	                if(startX > targetX) dir[2] = Integer.MAX_VALUE; // 좌 반전 불가
	                else dir[3] = Integer.MAX_VALUE; //우 반전 불가
	            }
	            
	            answer[i] = dir[0];
	            for(int j = 1; j < 4; j++) {
	                answer[i] =  Math.min(answer[i], dir[j]);
	            }
	        }
	        
	        return answer;
	    }
	    static int getDistSquare(int x1, int y1, int x2, int y2) {
	        return (int) Math.pow(x1-x2, 2) + (int) Math.pow(y1-y2, 2);
	    }
	}
}
