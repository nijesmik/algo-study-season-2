package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 주사위_굴리기 {
    static int N, M, x, y, K;
    static int[][] map;
    static int[] dr = {0, 0, -1, 1}; //동서북남
    static int[] dc = {1, -1, 0, 0};
    static HashMap<Integer, Integer> dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new HashMap<>();
        dice.put(1, 0);
        dice.put(2, 0);
        dice.put(3, 0);
        dice.put(4, 0);
        dice.put(5, 0);
        dice.put(6, 0);
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            int order = Integer.parseInt(st.nextToken())-1;
            if(!(x+dr[order]>=0 && x+dr[order]<N && y+dc[order]>=0 && y+dc[order]<M)) continue;
            x += dr[order];
            y += dc[order];
            changeDice(order);
            if(map[x][y] == 0) {
                map[x][y] = dice.get(6);
            } else {
                dice.put(6,map[x][y]);
                map[x][y] = 0;
            }
            sb.append(dice.get(1)).append("\n");
        }

        String answer = sb.toString();
        System.out.println(answer);
    }

    static void changeDice(int order) {
        if(order == 0) { //동
            int tmp = dice.get(1);
            dice.put(1,dice.get(4));
            dice.put(4,dice.get(6));
            dice.put(6,dice.get(3));
            dice.put(3,tmp);
        } else if(order ==1) { //서
            int tmp = dice.get(1);
            dice.put(1,dice.get(3));
            dice.put(3,dice.get(6));
            dice.put(6,dice.get(4));
            dice.put(4,tmp);
        } else if(order == 2) { // 북
            int tmp = dice.get(1);
            dice.put(1,dice.get(5));
            dice.put(5,dice.get(6));
            dice.put(6,dice.get(2));
            dice.put(2,tmp);
        } else { //남
            int tmp = dice.get(1);
            dice.put(1,dice.get(2));
            dice.put(2,dice.get(6));
            dice.put(6,dice.get(5));
            dice.put(5,tmp);
        }
    }
}
