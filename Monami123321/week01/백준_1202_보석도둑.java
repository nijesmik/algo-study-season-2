import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1202_보석도둑 {

    static class Jewel {
        int m, v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[n];
        int[] bag = new int[k];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m, v);


        }
        for (int i = 0; i < k; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(jewels, (a, b) -> a.m - b.m); // 무게 가벼운 순으로 정렬
        Arrays.sort(bag); // 담을 수 있는 무게 작은 순 정렬
        PriorityQueue<Jewel> pq = new PriorityQueue<>((a, b) -> b.v - a.v);
        int pointer = 0;
        long total = 0;
        for (int i = 0; i < bag.length; i++) {

            while (pointer < jewels.length && jewels[pointer].m <= bag[i]) {
                pq.add(jewels[pointer++]);
            }
            if (!pq.isEmpty()) {
                total += pq.poll().v;
            }
        }

        System.out.println(total);


    }
}
