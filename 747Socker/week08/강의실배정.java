package inf;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Lecture implements Comparable<Lecture> {
    int start;
    int end;

    public Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Lecture other) {
        return this.start - other.start;
    }
}

public class 강의실배정 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Lecture[] lectures = new Lecture[N];
        
        for (int i = 0; i < N; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            lectures[i] = new Lecture(start, end);
        }
        Arrays.sort(lectures);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (Lecture lecture : lectures) {
            if (!pq.isEmpty() && pq.peek() <= lecture.start) {
            	pq.poll();
            }
            pq.offer(lecture.end);
        }

        System.out.println(pq.size());
    }
}
