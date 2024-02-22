package jechul;

import java.util.*;

class 베스트앨범 {
    class Song implements Comparable<Song> {
        int id;
        int playTime;
        Song(int id, int play) {
            this.id = id;
            this.playTime = play;
        }
        @Override
        public int compareTo(Song o) {
            if (this.playTime == o.playTime) return this.id - o.id;
            return o.playTime - this.playTime;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
    	List<Integer> ansList = new ArrayList<>();
    	
        Map<String, Integer> playCnt = new HashMap<>();
        Map<String, List<Song>> songs = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
        	playCnt.put(genres[i], playCnt.getOrDefault(genres[i], 0) + plays[i]);
            List<Song> list = songs.getOrDefault(genres[i], new ArrayList<>());
            list.add(new Song(i, plays[i]));
            songs.put(genres[i], list);
        }
        //keySet 
        List<String> orders = new ArrayList<>(playCnt.keySet());

        Collections.sort(orders, new Comparator<String>() {
            @Override
            public int compare(String g1, String g2) {
                int playCount1 = playCnt.get(g1);
                int playCount2 = playCnt.get(g2);
                return Integer.compare(playCount2, playCount1);
            }
        });

        
        for (String genre : orders) {
            List<Song> res = songs.get(genre);
            
            Collections.sort(res);
            int cnt = 0;
            for (Song song : res) {
                if (cnt > 1) break;
                ansList.add(song.id);
                cnt++;
            }
        }
        
        int[] answer = new int[ansList.size()];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = ansList.get(i);
        }
        
        return answer;
    }
}
