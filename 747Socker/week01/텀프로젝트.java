package bj;
import java.util.*;

public class 텀프로젝트 {
	static int[] arr;
	static boolean[] vst;
	static boolean[] chk;
	static int cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=0; tc<T; tc++) {
			int N = sc.nextInt();
			arr = new int[N+1];
			chk = new boolean[N+1];
			vst = new boolean[N+1];
			cnt = 0;
			
			for(int i=1; i<=N;i++) {
				arr[i]=sc.nextInt();
			}
			
			for(int i=1; i<=N; i++) {
				if(!chk[i]) {
					dfs(i);
				}
			}
			System.out.println(N-cnt);
		}
	}
	public static void dfs(int n) {
		if(vst[n]) {
			chk[n]=true;
			cnt++;
		}else {
			vst[n]=true;
		}
		if(!chk[arr[n]]) {
			dfs(arr[n]);
		}
		
		vst[n]=false;
		chk[n]=true;
		
	}
}
