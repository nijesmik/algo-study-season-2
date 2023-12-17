package bj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 연구소3 {
	
	static class Point{
		int x;
		int y;
		int time;
		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
			
		}
	}
	
	static int[][] map;
	static int N;
	static int M;
	static int zeros;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Point[] selected;
	static ArrayList<Point> pts;
	static Queue<Point> Q;
	static int min = Integer.MAX_VALUE;
	
	static boolean chk2(int[][] chk) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]!=1 && chk[i][j]==0) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void bfs(int zeros) {
		int[][] chk = new int[N][N];
		Q = new LinkedList<Point>();
		for(int i=0; i<M;i++) {
			Q.add(selected[i]);
			chk[selected[i].x][selected[i].y]=1;
		}
		
		 while(!Q.isEmpty()) {
             Point n = Q.poll();
             for (int i = 0; i < 4; i++) {
                 int nx = n.x + dx[i];
                 int ny = n.y + dy[i];
                 if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                     continue;
                 if (map[nx][ny] == 1)
                     continue;
                 if (chk[nx][ny]==1) {
                     continue;
                 }
                 if (map[nx][ny] == 0)
                     zeros--;
                 Q.add(new Point(nx, ny, n.time+1));
                 chk[nx][ny] = 1;
                 if (zeros == 0) {
                     min = Math.min(min, n.time+1);
                     return;
                 }
             }
     }

 }

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		pts = new ArrayList<Point>();
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j]=sc.nextInt();
				if(map[i][j]==2) {
					pts.add(new Point(i, j,0));
				}
				if(map[i][j]==0) {
					zeros++;
				}
			}
		}
		if(zeros==0) {
			System.out.println(0);
			return;
		}
		selected = new Point[M];
		sel(0,0);
		if(min==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
	static void sel(int cnt, int s) {
		if(cnt==M) {
			bfs(zeros);
			return;
		}
		for(int i=s; i<pts.size();i++) {
			selected[cnt] = pts.get(i);
			sel(cnt+1,i+1);
		}
	}
}