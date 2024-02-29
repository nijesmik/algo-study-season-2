package jechul;

import java.util.*;
class 자물쇠와열쇠 {
    
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			
		}
	}
    
    static private boolean check(int[][] key, int[][] lock, ArrayList<Point> zeros) {
        
        return false;
    }
    
    static private void move() {
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                
            }
        }
    }
    static int N;
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        N = key.length;
        int M = lock[0].length;
        ArrayList<Point> zeros = new ArrayList<>();
        for(int i=0; i<M; i++) {
        	for(int j=0; j<N; j++	) {
        		if(lock[i][j]==0) zeros.add(new Point(i,j));
        	}
        }
       
        int[][] temp = new int[key.length][key[0].length];
        
        for(int k=0; k<4; k++){
            for(int i=0; i<key.length;i++){
                for(int j=0; j<key[0].length;j++){
                     if (k == 0) { 
                        temp[i][j] = key[N - j - 1][i];
                    } else if (k == 1) { 
                        temp[i][j] = key[N - i - 1][N - j - 1];
                    } else if (k == 2) { 
                        temp[i][j] = key[j][N - i - 1];
                    } else if (k == 3) { 
                        temp[i][j] = key[i][j];
                    }
                }
            }
        }
            
        
        return answer;
    }
}