package pract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_bfs_연구소 {

	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;

		}
	}

	static int[][] map;
	static int N;
	static int M;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Point[] selected;
	static ArrayList<Point> pts;
	static Queue<Point> Q;
	static int max = Integer.MAX_VALUE;

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

	static void bfs() {
		int[][] chk = new int[N][N];
		Q = new LinkedList<Point>();
		int res = 0;
		for(int i=0; i<M;i++) {
			Q.add(selected[i]);
			chk[selected[i].x][selected[i].y]=1;
		}

		while(!Q.isEmpty()) {
			if(res>=max) return;
			int size = Q.size();
			for(int i=0; i<size; i++) {
				Point tmp = Q.poll();
				for(int d=0 ;d<4; d++) {
					int nx = tmp.x + dx[d];
					int ny = tmp.y + dy[d];
					if(nx>=0 && nx<N && ny>=0 && ny<N && chk[nx][ny]==0) {
						if(map[nx][ny]!=1) {
							chk[nx][ny]=1;
							Q.offer(new Point(nx,ny));
						}
					}
				}
			}
			res++;
		}
		if(chk2(chk)) max = Math.min(max, res-1);
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
					pts.add(new Point(i, j));
				}
			}
		}
		selected = new Point[M];
		sel(0,0);
		if(max==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(max);
	}
	static void sel(int cnt, int s) {
		if(cnt==M) {
			bfs();
			return;
		}
		for(int i=s; i<pts.size();i++) {
			selected[cnt] = pts.get(i);
			sel(cnt+1,i+1);
		}
	}
}