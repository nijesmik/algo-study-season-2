import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek1202 {

    static class Jewel implements Comparable<Jewel> {
        int weight;
        int value;

        Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel other) {
            return Integer.compare(other.value, this.value); // Max heap based on value
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Jewel> jewels = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(weight, value));
        }

        //이걸 몰라서 지피티가 풀어 줌
        // Sort jewels by weight (ascending)
        jewels.sort(Comparator.comparingInt(j -> j.weight));

        List<Integer> bags = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(bf.readLine()));
        }

        // Sort bags by capacity (ascending)
        Collections.sort(bags);

        long totalValue = 0;
        int index = 0;
        PriorityQueue<Jewel> queue = new PriorityQueue<>(); // Max heap based on jewel value

        for (int capacity : bags) {
            // Add eligible jewels to the priority queue
            while (index < N && jewels.get(index).weight <= capacity) {
                queue.add(jewels.get(index));
                index++;
            }

            // Take the jewel with the highest value that fits in the current bag
            if (!queue.isEmpty()) {
                totalValue += queue.poll().value;
            }
        }

        System.out.println(totalValue);
    }
}
