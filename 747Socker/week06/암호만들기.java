package w06_1_02;

import java.util.Arrays;
import java.util.Scanner;

public class 암호만들기 {
	static int L,C;
	static char[] arr;
	static char[] code;
	
	static void make(int x, int idx) {
		if(idx==L) {
			if(isValid()) {
				System.out.println(code);
			}
			return;
		}
		
		for(int i=x; i<C; i++) {
			code[idx] = arr[i];
			make(i+1,idx+1);
		
	}
}
	
	static boolean isValid() {
		int mo = 0;
		int ja = 0;
		for(char x: code) {
			if(x=='a'|| x=='e'|| x=='i'|| x=='o'||x=='u') {
				mo++;
			}else {
				ja++;
			}
			
		}
		if(mo>=1 && ja>=2) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		 L = sc.nextInt();
		 C = sc.nextInt();
		
		arr = new char[C];
		code = new char[L];
		
		for(int x=0; x<C; x++) {
			arr[x] = sc.next().charAt(0);
		}
		
		Arrays.sort(arr);
		
		make(0,0);
	}
}
