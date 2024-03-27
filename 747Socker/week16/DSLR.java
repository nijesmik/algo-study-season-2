import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DSLR {
    static class State {
        int value;
        String path;

        public State(int value, String path) {
            this.value = value;
            this.path = path;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int A = sc.nextInt(); 
            int B = sc.nextInt(); 

            System.out.println(bfs(A, B));
        }
    }

    static String bfs(int start, int target) {
        Queue<State> queue = new LinkedList<>();
        boolean[] visited = new boolean[10000]; 

        queue.add(new State(start, ""));
        visited[start] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.value == target) {
                return current.path;
            }

            int D = (current.value * 2) % 10000;
            int S = current.value == 0 ? 9999 : current.value - 1;
            int L = (current.value % 1000) * 10 + current.value / 1000; // 왼쪽으로 회전
            int R = (current.value / 10) + (current.value % 10) * 1000; // 오른쪽으로 회전

            if (!visited[D]) {
                visited[D] = true;
                queue.add(new State(D, current.path + "D"));
            }
            if (!visited[S]) {
                visited[S] = true;
                queue.add(new State(S, current.path + "S"));
            }
            if (!visited[L]) {
                visited[L] = true;
                queue.add(new State(L, current.path + "L"));
            }
            if (!visited[R]) {
                visited[R] = true;
                queue.add(new State(R, current.path + "R"));
            }
        }

        return ""; 
    }
}
