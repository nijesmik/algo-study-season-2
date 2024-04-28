import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0 ;i<N; i++){
            pq.add(sc.nextInt());
        }

        if(N==1){
            System.out.println(0);
            return;
        }

        while(pq.size()>1){
            int res = pq.poll() + pq.poll();
            ans +=res;
            pq.add(res);
        }
        System.out.println(ans);



    }
}