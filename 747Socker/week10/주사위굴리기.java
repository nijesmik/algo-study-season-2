package bj;

import java.util.Arrays;
import java.util.Scanner;

public class 주사위굴리기 {
	static int N, M, x, y,K;
	static int[][] map;
	static Dice dice; 
	static class Dice{
		int r;
		int c;
		
		int top;
		int bottom;
		int front;
		int back;
		int left;
		int right;
		
		public Dice(int r, int c, int top, int bottom, int front, int back, int left, int right) {
			this.r=r;
			this.c=c;
			this.top=top;
			this.bottom=bottom;
			this.front=front;
			this.back=back;
			this.left=left;
			this.right=right;
		}
		
	}
	
	static boolean chk(int x, int y) {
		if(x>=0 && x<N && y>=0 && y<M) {
			return true;
		}else return false;
	}
	
	static void roll(Dice dice, int dir) {
		if(dir == 3) {
			int nx = dice.r-1;
			int ny = dice.c;
			if(chk(nx,ny)) {
				dice.r= nx;
				dice.c= ny;
				int tmp = dice.bottom;
				dice.bottom = dice.front;
				dice.front = dice.top;
				dice.top = dice.back;
				dice.back = tmp;
				if(map[nx][ny]>0) {
					dice.bottom = map[nx][ny];
					map[nx][ny]=0;
					System.out.println(dice.top);
				}else {
					map[nx][ny]=dice.bottom;
					System.out.println(dice.top);
				}
			}else {
				return;
			}
		}else if(dir==1) {
			int nx = dice.r;
			int ny = dice.c+1;
			if(chk(nx,ny)) {
				dice.r=nx;
				dice.c=ny;
				int tmp = dice.bottom;
				dice.bottom = dice.right;
				dice.right = dice.top;
				dice.top = dice.left;
				dice.left = tmp;
				if(map[nx][ny]>0) {
					dice.bottom = map[nx][ny];
					map[nx][ny]=0;
					System.out.println(dice.top);
				}else {
					map[nx][ny]=dice.bottom;
					System.out.println(dice.top);
				}
			}else {
				return;
			}
		}else if(dir==2) {
			int nx = dice.r;
			int ny = dice.c-1;
			if(chk(nx,ny)) {
				dice.r=nx;
				dice.c=ny;
				int tmp = dice.bottom;
				dice.bottom = dice.left;
				dice.left = dice.top;
				dice.top = dice.right;
				dice.right = tmp;
				if(map[nx][ny]>0) {
					dice.bottom = map[nx][ny];
					map[nx][ny]=0;
					System.out.println(dice.top);
				}else {
					map[nx][ny]=dice.bottom;
					System.out.println(dice.top);
				}
			}else {
				return;
			}
		}else if(dir==4) {
			int nx = dice.r+1;
			int ny = dice.c;
			if(chk(nx,ny)) {
				dice.r= nx;
				dice.c= ny;
			int tmp = dice.bottom;
			dice.bottom = dice.back;
			dice.back = dice.top;
			dice.top = dice.front;
			dice.front=tmp;
			if(map[nx][ny]>0) {
				dice.bottom = map[nx][ny];
				map[nx][ny]=0;
				System.out.println(dice.top);
			}else {
				map[nx][ny]=dice.bottom;
				System.out.println(dice.top);
			}
		}else return;
	}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		x = sc.nextInt();
		y = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		dice = new Dice(x,y,0,0,0,0,0,0);
		int[] dirs = new int[K];
		for(int i=0; i<K; i++) {
			dirs[i]=sc.nextInt();
		}
		for(int d:dirs) {
			roll(dice,d);
		}
		
	}
}

