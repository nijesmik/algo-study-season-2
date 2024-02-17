import java.util.*;

public class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreCountMap = new HashMap<>();
        List<Song> songs = new ArrayList<>();

        for (int i = 0; i < genres.length; i++) {
            //  getOrDefault  = V getOrDefault(Object key, V defaultValue)
            // 값이 없을 경우 defaut 주는 메서드
            genreCountMap.put(genres[i], genreCountMap.getOrDefault(genres[i], 0) + plays[i]);
            songs.add(new Song(i, genres[i], plays[i]));
        }

        //  keySet()  : 맵에 저장된 모든 키를 Set 반환
        List<String> sortedGenres = new ArrayList<>(genreCountMap.keySet());
        //  람다식 : compareTo기준으로 정렬 - 반환값은 Integer임
        //  리스트 정렬을 외부 Map을 기준으로 가능하게 함. 
        sortedGenres.sort((a, b) -> genreCountMap.get(b).compareTo(genreCountMap.get(a)));

        List<Integer> answerList = new ArrayList<>();
        for (String genre : sortedGenres) {
            // 해당 장르의 노래들을 재생 횟수와 고유 번호에 따라 정렬
            List<Song> filteredSongs = new ArrayList<>();
            for (Song song : songs) {
                if (song.genre.equals(genre)) {
                    filteredSongs.add(song);
                }
            }

            // Collections.sort 조건 거는 법
            Collections.sort(filteredSongs, (a, b) -> {
                if (a.playCount == b.playCount) {
                    return a.id - b.id;
                }
                return b.playCount - a.playCount;
            });

            // 최대 두 개만 선택 가능
            for (int i = 0; i < Math.min(filteredSongs.size(), 2); i++) {
                answerList.add(filteredSongs.get(i).id);
            }
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    class Song {
        int id;
        String genre;
        int playCount;

        public Song(int id, String genre, int playCount) {
            this.id = id;
            this.genre = genre;
            this.playCount = playCount;
        }
    }

}
