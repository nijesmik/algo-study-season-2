package jechul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수_이어_쓰기2 {
	static int N;
	static long k;
	static long len;
	
	static int getLen(int n) {
		if(n==0) return 1;
		int L = 0;
		while(n>0) {
			L++;
			n/=10;
		}
		return L;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());
        len = 0;
        String nN = "";

        int i = 1;

        while (i <= N) {
            long nLen = len + getLen(i);
            if (nLen >= k) {
                nN = Integer.toString(i);
                System.out.println(nN.charAt((int) (k - 1 - len)));
                return;
            }
            len = nLen;
            i++;
        }
        System.out.println(-1);
    }
}
