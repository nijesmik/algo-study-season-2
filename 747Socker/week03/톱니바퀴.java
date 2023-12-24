package bj;

import java.util.Arrays;
import java.util.Scanner;

public class 톱니바퀴 {
	static int[][] inpArr = new int[4][8];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 4; i++) {
			String tmp = sc.next();
			for (int j = 0; j < 8; j++) {
				inpArr[i][j] = tmp.charAt(j) - '0';
			}
		}
		int K = sc.nextInt();
		for(int i=0; i<K;i++) {
			int N = sc.nextInt()-1;
			int D = sc.nextInt();
			op(N,D);
		}
		int ans = 0;
		for(int i=0; i<4; i++) {
			ans += Math.pow(2, i)*inpArr[i][0];
		}
		System.out.println(ans);
	}
	static void rotate(int N, int D) {
		if(D==1) {
			int temp = inpArr[N][7];
			for(int i=7; i>0; i--) {
				inpArr[N][i] = inpArr[N][i-1];
			}
			inpArr[N][0]=temp;
		}else {
			int temp = inpArr[N][0];
			for(int i=0; i<7; i++) {
				inpArr[N][i]=inpArr[N][i+1];
			}
			inpArr[N][7] = temp;
		}
	}
	static void left(int N, int D) {
		if(N<0) return;
		if(inpArr[N][2]==inpArr[N+1][6]) return;
		left(N-1,-D);
		rotate(N,D);
	}
	static void right(int N, int D) {
		if(N>3) return;
		if(inpArr[N][6]==inpArr[N-1][2]) return;
		right(N+1, -D);
		rotate(N,D);
	}
	
	static void op(int N, int D) {
		left(N-1,-D);
		right(N+1,-D);
		rotate(N,D);
	}
}
