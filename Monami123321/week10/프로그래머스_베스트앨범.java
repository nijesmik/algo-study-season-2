import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class 프로그래머스_베스트앨범 {
    static class Node {
        int score;
        List<int[]> list;

        public Node() {
            this.list = new ArrayList<>();
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        List<Node> nodes = new ArrayList<>();
        HashMap<String, Node> hm = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            Node node = hm.get(genres[i]);
            if (node == null) {
                node = new Node();
                nodes.add(node);
                hm.put(genres[i], node);
            }
            node.score += plays[i];
            node.list.add(new int[]{i, plays[i]});
        }
        Collections.sort(nodes, (a, b) -> b.score - a.score);
        nodes.forEach(node -> {
            Collections.sort(node.list, (a, b) -> b[1] - a[1]);
        });
        List<Integer> ansList = new ArrayList<>();
        nodes.forEach(node -> {
            for (int i = 0; i < 2 && i < node.list.size(); i++) {
                ansList.add(node.list.get(i)[0]);
            }
        });
        return ansList.stream().mapToInt(e -> e.intValue()).toArray();
    }
}
