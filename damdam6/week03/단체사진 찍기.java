import java.util.*;

public class Solution {
    static int answer;
    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static boolean[] visited = new boolean[8];
    static Map<Character, Integer> positions;
    static String[] data;

    public static int solution(int n, String[] data) {
        Solution.data = data;
        positions = new HashMap<>();
        answer = 0;
        permute(0);
        return answer;
    }

    private static void permute(int depth) {
        if (depth == 8) {
            if (check()) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                positions.put(friends[i], depth);
                permute(depth + 1);
                visited[i] = false;
            }
        }
    }

    private static boolean check() {
        for (String condition : data) {
            int pos1 = positions.get(condition.charAt(0));
            int pos2 = positions.get(condition.charAt(2));
            char op = condition.charAt(3);
            int dist = condition.charAt(4) - '0';

            if (op == '=' && Math.abs(pos1 - pos2) - 1 != dist) {
                return false;
            } else if (op == '<' && Math.abs(pos1 - pos2) - 1 >= dist) {
                return false;
            } else if (op == '>' && Math.abs(pos1 - pos2) - 1 <= dist) {
                return false;
            }
        }
        return true;
    }

}
