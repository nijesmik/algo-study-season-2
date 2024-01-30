package y24Jan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Baek11000 {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        ArrayList<lecture> lectures = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lectures.add(new lecture(s,e));
        }

        Collections.sort(lectures);

        int classroomCnt = 0;

        PriorityQueue<classroom> pqu = new PriorityQueue();

        for (int i = 0; i < N; i++) {
        lecture tmp = lectures.get(i);
        if(pqu.isEmpty() || pqu.peek().emptyTime > tmp.startTime){
            pqu.add(new classroom(tmp.endTime));
            classroomCnt++;
            continue;
        }
        classroom room = pqu.poll();
        room.emptyTime = tmp.endTime;
        pqu.add(room);


        }
        System.out.println(classroomCnt);

    }

    static class classroom implements Comparable<classroom> {
        int emptyTime;

        @Override
        public String toString() {
            return "classroom{" +
                    "emptyTime=" + emptyTime +
                    '}';
        }

        public classroom(int emptyTime) {
            this.emptyTime = emptyTime;
        }

        @Override
        public int compareTo(classroom o) {
            if(this.emptyTime < o.emptyTime)return -1;
            if(this.emptyTime > o.emptyTime)return 1;
            return 0;
        }
    }

   static class lecture implements Comparable<lecture>{
        int startTime;
        int endTime;


        public lecture(int start, int end) {
            this.startTime = start;
            this.endTime = end;
        }

        @Override
        public String toString() {
            return "lecture{" +
                    "start=" + startTime +
                    ", end=" + endTime +
                    '}';
        }

       @Override
       public int compareTo(lecture o) {
            if(this.startTime > o.startTime){
                return 1;
            }
            if(this.startTime < o.startTime){
                return -1;
            }
           return 0;
       }
   }
}
