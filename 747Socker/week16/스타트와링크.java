import java.util.ArrayList;
import java.util.Scanner;

public class 스타트와링크 {
	static int N;
	static int R;
	static int[][] teams;
	static int start;
	static int link;
	static ArrayList<Integer> st;
	static ArrayList<Integer>	 lt;
	static boolean[] visited;
	static int mini;
	static void comb(int s, int r) {
		if(r==0) {
			calc();
		}
		for(int i=s; i<N; i++) {
			visited[i]=true;
			comb(i+1,r-1);
			visited[i]=false;
		}
		
	}
	static void calc() {
		for(int i=0; i<N; i++	) {
			for(int j=0; j<N;j ++) {
				if(visited[i] && visited[j]) {
					start += teams[i][j];
				}else if(!visited[i] && !visited[j]){
					link += teams[i][j];
				}
			}
		}
		mini = Math.min(mini, Math.abs(start-link));
		start = 0;
		link =0 ;
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		teams = new int[N][N];
		start = 0;
		link = 0;
		R = N/2;
		visited = new boolean[N];
		mini = 999999;
		for(int i=0; i<N;i++) {
			for(int j=0; j<N;j++) {
				teams[i][j] = sc.nextInt();
			}
		}
		comb(0,R);
		System.out.println(mini);
	}
}
