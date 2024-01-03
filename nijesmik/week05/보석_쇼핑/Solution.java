package nijesmik.week05.보석_쇼핑;

import java.util.*;

class Solution {
    int N, answerLength, answerStart;

    public int[] solution(String[] gems) {
        answerLength = N = gems.length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(gems[i]);
        }
        int st = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int en = 0; en < N; en++) {
            int lastCount = map.getOrDefault(gems[en], 0);
            map.put(gems[en], lastCount+1);
            while (map.size() == set.size()) {
                deriveAnswer(st, en);
                int firstCount = map.get(gems[st]) - 1;
                map.remove(gems[st]);
                if (firstCount > 0) {
                    map.put(gems[st], firstCount);
                }
                st++;
            }
        }
        answerStart++;
        return new int[]{answerStart, answerStart + answerLength};
    }

    void deriveAnswer(int st, int en) {
        int len = en - st;
        if (answerLength == len) {
            answerStart = Math.min(st, answerStart);
        } else if (answerLength > len) {
            answerLength = len;
            answerStart = st;
        }
    }
}
