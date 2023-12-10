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
	static int[][] chk;
	static int[] pm;
	static int[] ch;
	static int[] arr;
	static int nn;
	static ArrayList<Point> pts;
	static ArrayList<Point> qs;
	static Queue<Point> Q;
	static int cnt;
	static int max = Integer.MIN_VALUE;
	static int[][] cMap;
	
	static void permu(int L) {
		if(L==3) {
			wall(pm);
		}else {
			for(int i=0; i<nn;i++) {
				if(ch[i]==0) {
					ch[i]=1;
					pm[L]=arr[i];
					permu(L+1);
					ch[i]=0;
				}
			}
		}
	}
	
	static void wall(int[] aa) {
		for(int i=0; i<3; i++) {
			Point tmp = pts.get(aa[i]-1);
			cMap[tmp.x][tmp.y]=1;
		}
		bfs();
		// re
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				cMap[i][j]=map[i][j];
			}
		}
	}
	static void chk2() {
		cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(cMap[i][j]==0) {
					cnt++;
				}
			}
		}
		max = Math.max(max, cnt);
	}
	
	static void bfs() {
		chk = new int[N][M];
		Q = new LinkedList<Point>();
		for(int i=0; i<qs.size();i++) {
			Q.add(qs.get(i));
			chk[qs.get(i).x][qs.get(i).y]=1;
		}
		
		while(!Q.isEmpty()) {
			Point tmp = Q.poll();
			for(int d=0 ;d<4; d++) {
				int nx = tmp.x + dx[d];
				int ny = tmp.y + dy[d];
				if(nx>=0 && nx<N && ny>=0 && ny<M && cMap[nx][ny]==0 && chk[nx][ny]==0) {
					cMap[nx][ny]=2;
					chk[nx][ny]=1;
					Q.offer(new Point(nx,ny));
				}
			}
		}
		chk2();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		pts = new ArrayList<Point>();
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		
		cMap = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				cMap[i][j] = map[i][j];
			}
		}
		
		qs = new ArrayList<Point>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					pts.add(new Point(i,j));
				}else if(map[i][j]==2) {
					qs.add(new Point(i,j));
				}
			}
		}
		
		nn = pts.size();
		ch = new int[nn];
		pm = new int[3];
		arr=new int[nn];
		Arrays.setAll(arr, num->num+1);
		permu(0);
		System.out.println(max);
	}
}