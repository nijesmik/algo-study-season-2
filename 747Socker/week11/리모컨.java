package jechul;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class 리모컨{
	static int N;
	static int M;
	static Set<Integer> set;
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt(); 
        M = scanner.nextInt(); 
        set = new HashSet<>();

        for (int i = 0; i < M; i++) {
            set.add(scanner.nextInt()); 
        }
        
        System.out.println(oper(N, set));
    }

    private static boolean find(int ch, Set<Integer> set) {
        String channelStr = String.valueOf(ch); 
        
        for (int i = 0; i < channelStr.length(); i++) {
            int target = channelStr.charAt(i) - '0'; 
            if (set.contains(target)) {
                return false; 
            }
        }
        return true; 
    }


    private static int oper(int ch, Set<Integer> set) {
        int minPress = Math.abs(100 - ch); 
        
        for (int channel = 0; channel <= 1000000; channel++) {
            if (find(channel, set)) {
                int press = Integer.toString(channel).length() + Math.abs(channel - ch);
                minPress = Math.min(minPress, press);
            }
        }
        return minPress;
    }
}
