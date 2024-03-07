package Date2403;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baek15685 {


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n;
        n = Integer.parseInt(st.nextToken());


        int[][] dragon = new int[n][4];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 4; j++) {
                dragon[i][j] = Integer.parseInt(st.nextToken());
            }

        }
        // 100x100이므로 -> 0~100까지 숫자 다 사용해야됨
        int[][] bd = new int[101][101];
        // x,y, d-방향, g-세대
        //세대 시작은 0
//        0: x좌표가 증가하는 방향 (→)
//        1: y좌표가 감소하는 방향 (↑)
//        2: x좌표가 감소하는 방향 (←)
//        3: y좌표가 증가하는 방향 (↓)

//        4
//        3 3 0 1
//        4 2 1 3
//        4 2 2 1
//        2 7 3 4

        for (int i = 0; i < n; i++) {

            // x,y반대라 바꿔서 받음
            int beginX = dragon[i][1];
            int beginY = dragon[i][0];

            int beginDir = dragon[i][2];
            int gener = dragon[i][3];

            Deque<dragonDot> stk = new ArrayDeque<>();
            //  시작하는 애
            stk.add(new dragonDot(beginX, beginY, beginDir, 0));

            endPos end = getEndPos(stk.peekFirst());
            // 스타트와 첫end찍어두기

            bd[beginX][beginY] = 1;
            bd[end.endX][end.endY] = 1;

            Deque<dragonDot> newStk = new ArrayDeque<>();

            int genNow = 0;
            while (!stk.isEmpty() && stk.peekFirst().gener < gener) {


                dragonDot tmp = stk.pop();

                dragonDot newDot = new dragonDot(end.endX, end.endY, (tmp.dir + 1) % 4, genNow + 1);


                // stack에 들어가는 순간에 end 트래킹
                newStk.addFirst(newDot);

                newStk.offerLast(tmp);
                end = getEndPos(newDot);
                bd[end.endX][end.endY] = 1;

                if (stk.isEmpty()) {
                    genNow++;
                    stk = new ArrayDeque<>(newStk);
                    //System.out.println("stk : " + stk.toString());
                    newStk.clear();
                    continue;
                }

            }
            

        }
        int cnt = 0;
        for (int j = 0; j < 100; j++) {
            for (int k = 0; k < 100; k++) {
                if (bd[j][k] == 1
                        && bd[j][k + 1] == 1
                        && bd[j + 1][k] == 1
                        && bd[j + 1][k + 1] == 1) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);


    }


    static private endPos getEndPos(dragonDot dd) {
        endPos answer = new endPos();
        answer.endX = dd.startX;
        answer.endY = dd.startY;
        switch (dd.dir) {
            case 0:
                answer.endY += 1;
                break;
            case 1:
                answer.endX -= 1;
                break;
            case 2:
                answer.endY -= 1;
                break;
            case 3:
                answer.endX += 1;
                break;
        }

        return answer;
    }

    static class endPos {
        int endX;
        int endY;

        public endPos() {

        }

        public endPos(int endX, int endY) {
            this.endX = endX;
            this.endY = endY;
        }
    }

    static class dragonDot {
        int startX;
        int startY;
        int dir;
        int gener;

        public dragonDot(int startX, int startY, int dir, int gener) {
            this.startX = startX;
            this.startY = startY;
            this.dir = dir;
            this.gener = gener;
        }

        @Override
        public String toString() {
            return "dragonDot{" +
                    "startX=" + startX +
                    ", startY=" + startY +
                    ", dir=" + dir +
                    ", gener=" + gener +
                    '}';
        }
    }

}
