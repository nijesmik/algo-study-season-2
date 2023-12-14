package bj;

import java.util.Scanner;

public class TwoDots {
	static int N;
	static int M;
	static char[][] arr;
	static boolean[][] chk;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int tX;
	static int tY;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new char[N][M];
		for(int i=0; i<N; i++) {
			String tmp = sc.next();
			for(int j=0; j<M; j++) {
				arr[i][j] = tmp.charAt(j);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				chk = new boolean[N][M];
				tX = i;
				tY = j;
				if(dfs(1,j,j)) {
					System.out.println("Yes");
					return;
				}
			}
		}
		System.out.println("No");
	}
	public static boolean dfs(int x, int y, int cnt) {
		chk[x][y]=true;
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y + dy[i];
			if(nx>=0 && ny >=0 && nx<N && ny<M && arr[nx][ny]==arr[x][y]) {
				if(chk[nx][ny]==false) {
					chk[nx][ny]=true;
					if(dfs(nx,ny,cnt+1)) return true;
				}else {
					if(cnt>=4 && tX ==nx && tY==ny) return true;
				}
			}
		}
		return false;
	}
	
}
