package bj;

import java.util.*;
public class 파이프옮기기 {
	static int N;
	static int[][] map;
	static int cnt;
	
	static void dfs(int D, int r, int c) {
		if(r==N && c==N) {
			cnt++;
			return;
		}
		if(r>N || c>N || map[r][c]==1) return;
		if(D==2) {
			if(map[r][c-1]==1 || map[r-1][c]==1) return;
		}
		
		if(D==0) {
			dfs(0,r,c+1);
			dfs(2,r+1,c+1);
		}else if(D==1) {
			dfs(1,r+1,c);
			dfs(2,r+1,c+1);
		}else {
			dfs(2,r+1,c+1);
			dfs(0,r,c+1);
			dfs(1,r+1,c);
		}
		
		}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N+1][N+1];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		cnt = 0;
		dfs(0,1,2);
		System.out.println(cnt);
		
		
	}
}
