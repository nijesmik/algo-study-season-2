import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr; // 지도 정보
    static int[][] vt; // 방문 여부 및 섬 구분을 위한 배열
    static int N; // 지도의 크기
    static int dis; // 최소 다리 길이
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        arr = new int[N][N];
        vt = new int[N][N];

        // 지도 정보 입력 받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 번호 매기기
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1 && vt[i][j] == 0) {
                    markIsland(new pos(i, j), num++);
                }
            }
        }

        // 최소 다리 길이 구하기
        dis = Integer.MAX_VALUE;
        for (int i = 2; i < num; i++) { // 2부터 섬 번호 시작
            findShortestBridge(i);
        }

        System.out.println(dis);
    }

    // 섬에 고유 번호를 매기는 메소드
    private static void markIsland(pos start, int num) {
        ArrayDeque<pos> qu = new ArrayDeque<>();
        qu.add(start);
        vt[start.x][start.y] = num;

        while (!qu.isEmpty()) {
            pos temp = qu.poll();
            for (int k = 0; k < 4; k++) {
                int nx = temp.x + dx[k];
                int ny = temp.y + dy[k];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N && arr[nx][ny] == 1 && vt[nx][ny] == 0) {
                    vt[nx][ny] = num;
                    qu.add(new pos(nx, ny));
                }
            }
        }
    }

    // 최소 다리 길이를 찾는 메소드
    private static void findShortestBridge(int islandNum) {
        ArrayDeque<pos> qq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N]; // 방문 배열 초기화

        // 현재 섬의 모든 위치를 큐에 추가하고 방문 처리
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (vt[i][j] == islandNum) {
                    qq.add(new pos(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        while (!qq.isEmpty()) {
            pos p = qq.poll();
            if (p.dis >= dis) break; // 이미 최소 길이를 넘어선 경우 탐색 중지

            for (int k = 0; k < 4; k++) {
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (vt[nx][ny] != 0 && vt[nx][ny] != islandNum) {
                        dis = Math.min(p.dis, dis);
                        return;
                    }
                    if (!visited[nx][ny] && arr[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        qq.add(new pos(nx, ny, p.dis + 1));
                    }
                }
            }
        }
    }

    // 위치와 거리를 저장하는 pos 클래스
    public static class pos {
        int x, y, dis;

        public pos(int x, int
        y) {
            this.x = x;
            this.y = y;
            this.dis = 0; // 거리 초기값은 0
        }

        public pos(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis; // 거리 초기화
        }
    }
}
