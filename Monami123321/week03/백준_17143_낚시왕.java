import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_17143_낚시왕 {


    static class Shark {
        int r, c, speed, dir, weight;

        public Shark(int r, int c, int speed, int dir, int weight) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.weight = weight;
        }
    }

    static int R, C, M, ans;

    static int[] dr = {-1, 1, 0, 0}; // 상하우좌
    static int[] dc = {0, 0, 1, -1};
    static Shark[][] map;

    static List<Shark> sharkList;

    static void fish(int c) {
        int r = 0;
        while (r < R && map[r][c] == null) {
            r++;
        }
        if (r == R) {
            return;
        }
        ans += map[r][c].weight;
        sharkList.remove(map[r][c]); // 가장 가까운 상어를 잡아서 없앰
        //System.out.println(ans);


    }

    static void initMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = null;
            }

        }
    }

    static void move() {
        // 상어 좌표 변경 먼저
        sharkList.forEach(e -> {
            if (e.dir <= 1) { // 상하로움직임
                int dist = e.speed % ((2 * R) - 2); // 원래위치, 방향까지 그대로

                while (dist > 0) {
                    e.r += dr[e.dir];
                    dist--;
                    if (e.r == 0 || e.r == R - 1) {
                        e.dir ^= 1;
                    } // 끝이면 방향 반대로
                }

            } else { // 좌우로 움직임
                int dist = e.speed % ((2 * C) - 2); // 원래위치, 방향까지 그대로

                while (dist > 0) {
                    e.c += dc[e.dir];
                    dist--;
                    if (e.c == 0 || e.c == C - 1) {
                        e.dir ^= 1;
                    } // 끝이면 방향 반대로
                }

            }
        });

        // 맵 확인해서 잡아먹기
        int loopSize = sharkList.size();
        for (int i = 0; i < loopSize; i++) {
            Shark shark = sharkList.get(i);
            if (map[shark.r][shark.c] == null) {
                map[shark.r][shark.c] = shark;
            } else {
                if (map[shark.r][shark.c].weight > shark.weight) {
                    sharkList.remove(shark);
                    i--;
                    loopSize--;
                } else {
                    sharkList.remove(map[shark.r][shark.c]);
                    map[shark.r][shark.c] = shark;
                    i--;
                    loopSize--;
                }
            }


        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sharkList = new ArrayList<>();
        //sharkList.add(new Shark(-1, -1, -1, -1, -1)); // 더미 0번인덱스

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            sharkList.add(new Shark(r, c, speed, dir, weight));
        }
        map = new Shark[R][C]; // 상어를 저장하고 있는 배열

        sharkList.forEach(e -> {
            map[e.r][e.c] = e;
        }); // 초기 상어 위치 맵에 저장
//        for (int i = 0; i < R; i++) {
//            for (int j = 0; j < C; j++) {
//                if (map[i][j] == null) {
//                    System.out.printf("0 ");
//                } else {
//                    System.out.printf("%d ", map[i][j].weight);
//                }
//
//            }
//            System.out.println();
//        }


        ans = 0; // 최종 답

        // 맨 처음 주어진 거에서 내 로직에 맞게 물고기 방향 맞춰놓고 시작
        sharkList.forEach(e -> {
            if (e.dir < 2) {
                if (e.r == 0) {
                    e.dir = 1; // 맨 위면 아래 바라보게 조정
                } else if (e.r == R - 1) {
                    e.dir = 0; // 맨 아래면 위 바라보게 조정
                }

            } else {
                if (e.c == 0) {
                    e.dir = 2; // 맨 왼쪽이면 오른쪽 바라보게 조정
                } else if (e.c == C - 1) {
                    e.dir = 3; // 맨 오른쪽이면 왼쪽 바라보게 조정
                }
            }
        }); // 물고기 방향 조정 끝


        int fisherIndex = 0; // 낚시왕이 서 있는 위치
        while (fisherIndex < C) {
            fish(fisherIndex++);
            initMap();
            move();
//            System.out.println("---------------------------");
//            for (int i = 0; i < R; i++) {
//                for (int j = 0; j < C; j++) {
//                    if (map[i][j] == null) {
//                        System.out.printf("0 ");
//                    } else {
//                        System.out.printf("%d ", map[i][j].weight);
//                    }
//
//                }
//                System.out.println();
//            }


        }
        System.out.println(ans);


    }
}
