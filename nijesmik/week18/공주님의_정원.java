import java.util.*;

public class 공주님의_정원 {
    static PriorityQueue<Flower> flowers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        flowers = new PriorityQueue<>((a, b) -> (a.start - b.start));
        while (n-- > 0) {
            flowers.add(new Flower(sc.nextInt() * 100 + sc.nextInt(), sc.nextInt() * 100 + sc.nextInt()));
        }
        System.out.println(answer());
    }

    static int answer() {
        int ans = 0, end = 301;
        while (end <= 1130) {
            PriorityQueue<Flower> selected = new PriorityQueue<>((a, b) -> (b.end - a.end));
            while (!flowers.isEmpty() && flowers.peek().start <= end) {
                selected.add(flowers.poll());
            }
            if (selected.isEmpty()) {
                return 0;
            }
            end = selected.peek().end;
            ans++;
        }
        return ans;
    }

    static class Flower {
        int start, end;

        Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}