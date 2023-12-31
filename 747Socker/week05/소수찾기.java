package bj;

import java.util.HashSet;
import java.util.Set;
class 소수찾기 {
    public int solution(String numbers) {
        Set<Integer> pSet = new HashSet<>();
        char[] numChars = numbers.toCharArray();
        boolean[] visited = new boolean[numChars.length];

        numb(numChars, "", visited, pSet);

        return pSet.size();
    }

    private static void numb(char[] numChars, String cur, boolean[] visited, Set<Integer> pSet) {
        if (!cur.isEmpty()) {
            int num = Integer.parseInt(cur);
            if (isPrime(num)) {
                pSet.add(num);
            }
        }

        for (int i = 0; i < numChars.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                numb(numChars, cur + numChars[i], visited, pSet);
                visited[i] = false;
            }
        }
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
