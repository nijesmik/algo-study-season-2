import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] cars = new int[n];
        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<n;i++){
            cars[i]= Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cars);

        Map<Integer,Integer> map = new HashMap();
        for(int i=0; i<n;i++){
            map.put(cars[i], i+1);
        }

        StringBuilder sb = new StringBuilder();
        int[] cnt = new int[q];

        for(int i=0; i<q;i++){
            st = new StringTokenizer(bf.readLine());
            int mid= Integer.parseInt(st.nextToken());
            int posi = map.getOrDefault(mid, -1);
            if(posi == 1 || posi == n || posi == -1)continue;
            int small = posi-1;
            int big = n-posi;
            cnt[i]  = small * big;   
        }

        for(int i=0; i<q;i++){
            System.out.println(cnt[i]);
        }
        
    }
}