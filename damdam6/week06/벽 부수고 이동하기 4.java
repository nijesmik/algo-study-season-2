import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] board, group;
    static Map<Integer, Integer> groupSize = new HashMap<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        group = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = bf.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        int groupId = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0 && group[i][j] == 0) {
                    bfs(i, j, groupId);
                    groupId++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    sb.append((getAdjacentGroupCount(i, j) + 1) % 10);
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int x, int y, int groupId) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        group[x][y] = groupId;
        int size = 1;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nx = pos[0] + dx[k];
                int ny = pos[1] + dy[k];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && board[nx][ny] == 0 && group[nx][ny] == 0) {
                    queue.add(new int[]{nx, ny});
                    group[nx][ny] = groupId;
                    size++;
                }
            }
        }
        groupSize.put(groupId, size);
    }

    static int getAdjacentGroupCount(int x, int y) {
        Set<Integer> uniqueGroups = new HashSet<>();
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && board[nx][ny] == 0) {
                uniqueGroups.add(group[nx][ny]);
            }
        }

        int sum = 0;
        for (int id : uniqueGroups) {
            sum += groupSize.get(id);
        }
        return sum;
    }
}
