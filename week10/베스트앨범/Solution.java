package week10.베스트앨범;

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            Genre genre = map.getOrDefault(genres[i], new Genre());
            genre.total += plays[i];
            genre.pq.add(new Song(plays[i], i));
            map.put(genres[i], genre);
        }
        PriorityQueue<Genre> pq = new PriorityQueue<>(map.values());
        List<Integer> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            Genre genre = pq.poll();
            int cnt = 2;
            while (!genre.pq.isEmpty() && cnt-- > 0) {
                Song song = genre.pq.poll();
                ans.add(song.idx);
            }
        }
        return ans.stream().mapToInt(a -> a).toArray();
    }

    class Genre implements Comparable<Genre> {
        int total;
        PriorityQueue<Song> pq = new PriorityQueue<>();

        @Override
        public int compareTo(Genre o) {
            return o.total - this.total;
        }
    }

    class Song implements Comparable<Song> {
        int plays;
        int idx;

        Song(int plays, int idx) {
            this.plays = plays;
            this.idx = idx;
        }

        @Override
        public int compareTo(Song o) {
            if (this.plays == o.plays) {
                return this.idx - o.idx;
            }
            return o.plays - this.plays;
        }
    }
}