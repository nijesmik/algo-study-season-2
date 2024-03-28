package ns;
import java.util.*;

public class 이차원배열과연산 {
	
    static class Numb implements Comparable<Numb> {
        int value;
        int count;

        public Numb(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Numb o) {
            if (this.count == o.count) {
                return this.value - o.value;
            }
            return this.count - o.count;
        }
    }

    public static int[][] rOP(int[][] A) {
        int maxLen = 0;
        List<List<Numb>> temp = new ArrayList<>();

        for (int[] row : A) {
            Map<Integer, Integer> hash = new HashMap<>();
            
            for (int num : row) {
                if (num != 0) hash.put(num, hash.getOrDefault(num, 0) + 1);
            }

            List<Numb> sortedNums = new ArrayList<>();
            
            for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            	sortedNums.add(new Numb(entry.getKey(), entry.getValue()));
            }
            Collections.sort(sortedNums);

            List<Numb> newRow = new ArrayList<>();
            for (Numb num : sortedNums) {
                newRow.add(new Numb(num.value, 0));
                newRow.add(new Numb(num.count, 0));
            }

            maxLen = Math.max(maxLen, newRow.size());
            temp.add(newRow);
        }

        int[][] newA = new int[temp.size()][maxLen];
        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < temp.get(i).size(); j++) {
                newA[i][j] = temp.get(i).get(j).value;
            }
        }

        return newA;
    }

    public static int[][] cOP(int[][] A) {
        A = op(A);
        A = rOP(A);
        return op(A);
    }

    public static int[][] op(int[][] map) {
        int row = map.length;
        int col = map[0].length;
        int[][] nMap = new int[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
            	nMap[i][j] = map[j][i];
            }
        }
        return nMap;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] A = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                A[i][j] = scanner.nextInt();
            }
        }

        int time = 0;
        
        while (time <= 100) {
            if (r <= A.length && c <= A[0].length && A[r - 1][c - 1] == k) {
                System.out.println(time);
                return;
            }

            if (A.length >= A[0].length) {
                A = rOP(A);
            } else {
                A = cOP(A);
            }

            time++;
        }
        System.out.println(-1);
    }
}
