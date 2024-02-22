package 백준;

import java.util.*;

public class 베스트앨범 {
    class Solution {
        static HashMap<String, ArrayList<Song>> genrePlay;
        static int N;
        public int[] solution(String[] genres, int[] plays) {
            N = genres.length;
            genrePlay = new HashMap<>();
            for(int i = 0; i < N; i++) {
                if(!genrePlay.containsKey(genres[i])) {
                    genrePlay.put(genres[i], new ArrayList<>());
                    genrePlay.get(genres[i]).add(new Song(-1,genres[i],0));
                }
                genrePlay.get(genres[i]).get(0).playNum += plays[i];
                genrePlay.get(genres[i]).add(new Song(i, plays[i]));
            }


            int answerNum = 0;
            int idx = 0;

            PriorityQueue<Song> pq = new PriorityQueue<>();
            for(String s: genrePlay.keySet() ) {
                pq.add(genrePlay.get(s).get(0));
                if(genrePlay.get(s).size() > 2) answerNum+=2;
                else answerNum += 1;
            }

            int[] answer = new int[answerNum];

            while(!pq.isEmpty()) {
                String genre = pq.poll().genre;
                genrePlay.get(genre).remove(0);
                Collections.sort(genrePlay.get(genre));
                for(int i = 0; i < 2; i++) {
                    if(genrePlay.get(genre).size() < i+1) break;
                    answer[idx++] = genrePlay.get(genre).get(i).idx;
                }
            }

            return answer;
        }
    }
    class Song implements Comparable<Song> {
        int idx;
        int playNum;
        String genre;
        public Song(int idx, int playNum) {
            this.idx = idx;
            this.playNum = playNum;
        }
        public Song(int idx, String genre, int playNum) {
            this.idx = idx;
            this.genre = genre;
            this.playNum = playNum;
        }

        @Override
        public int compareTo(Song s) {
            if(this.playNum == s.playNum) {
                return this.idx - s.idx;
            }
            return s.playNum - this.playNum;
        }
    }
}
