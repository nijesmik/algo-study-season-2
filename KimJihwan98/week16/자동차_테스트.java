import java.io.*;
import java.util.*;

public class 자동차_테스트 {

    public class Main {
        static int n, q;
        static int[] cars;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            cars = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                cars[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(cars);
            HashMap<Integer, Integer> hm = new HashMap<>();
            for(int i = 0; i < n; i++) {
                hm.put(cars[i], i);
            }
            StringBuilder sb = new StringBuilder();
            while(q-->0) {
                st = new StringTokenizer(br.readLine());
                int m = Integer.parseInt(st.nextToken());
                if(!hm.containsKey(m)) {
                    sb.append("0\n");
                    continue;
                }
                int a = hm.get(m);
                int b = n-a-1;
                long ans = a*b;
                String s = String.valueOf(ans);
                sb.append(s + "\n");
            }
            String answer = String.valueOf(sb);
            System.out.println(sb);
        }
    }

}
